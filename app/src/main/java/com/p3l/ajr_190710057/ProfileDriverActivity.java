package com.p3l.ajr_190710057;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.p3l.ajr_190710057.api.CustomerApi;
import com.p3l.ajr_190710057.api.DriverApi;
import com.p3l.ajr_190710057.api.RiwayatCustomerApi;
import com.p3l.ajr_190710057.api.RiwayatDriverApi;
import com.p3l.ajr_190710057.models.Driver;
import com.p3l.ajr_190710057.preferences.DriverPreferences;
import com.p3l.ajr_190710057.responses.AverageResponse;
import com.p3l.ajr_190710057.responses.CustomerResponse;
import com.p3l.ajr_190710057.responses.DriverResponse;
import com.p3l.ajr_190710057.responses.RiwayatCustomerResponse;
import com.p3l.ajr_190710057.responses.RiwayatDriverResponse;
import com.p3l.ajr_190710057.responses.UpdateDriverResponse;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ProfileDriverActivity extends AppCompatActivity {
    private DriverPreferences driverPreferences;
    private Driver driver;
    private Context context;
    private MaterialButton btnEditProfile, btnEditStatus;
    private ImageButton btnBack;
    private TextView tvIdDriver, tvNama, tvTglLahir, tvAlamat, tvJenisKelamin,
            tvNoTelepon, tvTarif, tvKemampuanBahasa, tvStatusKetersediaan, tvRerataRating;
    private ImageView ivProfil;
    private RatingBar rtRerata;
    private RequestQueue queue;
    private MaterialCardView cvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_driver);

        tvIdDriver = findViewById(R.id.tv_id_driver_p);
        tvNama = findViewById(R.id.tv_nama_driver_p);
        tvNoTelepon = findViewById(R.id.tv_no_telepon_driver_p);
        tvTglLahir = findViewById(R.id.tv_tgl_lahir_driver_p);
        tvAlamat = findViewById(R.id.tv_alamat_driver_p);
        tvJenisKelamin = findViewById(R.id.tv_jenis_kelamin_driver_p);
        tvNoTelepon = findViewById(R.id.tv_no_telepon_driver_p);
        tvTarif = findViewById(R.id.tv_tarif_driver_p);
        tvKemampuanBahasa = findViewById(R.id.tv_kemampuan_bahasa_asing_p);
        tvStatusKetersediaan = findViewById(R.id.tv_status_ketersediaan_p);
        rtRerata = findViewById(R.id.rt_rerata_rating_driver);
        tvRerataRating = findViewById(R.id.tv_rerata_rating_driver);
        ivProfil = findViewById(R.id.iv_foto_profil_driver);

        btnEditProfile = findViewById(R.id.btnEditProfileDriver);
        btnEditStatus = findViewById(R.id.btnEditStatusDriver);

        btnBack = findViewById(R.id.btnBackFromProfileDriver);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileDriverActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
        cvStatus = findViewById(R.id.cv_status_ketersediaan);

        driverPreferences = new DriverPreferences(this);
        driver = driverPreferences.getDriverLogin();

        String urlImage = "http://192.168.100.7:8000/storage/foto_driver/"+driver.getFotoDriver();
        Glide.with(ProfileDriverActivity.this)
                .load(urlImage)
                .apply(new RequestOptions().override(800, 600))
                .into(ivProfil);
        tvIdDriver.setText(driver.getIdDriver());
        tvNama.setText(driver.getNamaDriver());
        tvNoTelepon.setText(driver.getNoTeleponDriver());
        tvTglLahir.setText(driver.getTglLahirDriver());
        tvAlamat.setText(driver.getAlamatDriver());
        tvJenisKelamin.setText(driver.getJenisKelaminDriver());
        tvNoTelepon.setText(driver.getNoTeleponDriver());
        tvTarif.setText(String.valueOf("Rp "+driver.getTarifDriverHarian()));

        if(driver.getKemampuanBahasaAsing()==0){
            tvKemampuanBahasa.setText("Tidak Bisa");
        }else{
            tvKemampuanBahasa.setText("Bisa");
        }

        if(driver.getStatusKetersediaan()==0) {
            tvStatusKetersediaan.setText("Tidak Tersedia");
            cvStatus.setBackgroundColor(Color.parseColor("#FF6347"));
        }else{
            tvStatusKetersediaan.setText("Tersedia");
            cvStatus.setBackgroundColor(Color.parseColor("#20B2AA"));
        }
        //Tampil rerata Driver
        StringRequest stringRequest = new StringRequest(GET, DriverApi.GET_AVERAGE+driverPreferences.getDriverLogin().getIdDriver()+"/average", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                AverageResponse averageResponse = gson.fromJson(response, AverageResponse.class);

                rtRerata.setRating(averageResponse.getAverageDriver().getRerataRating());
                String rating =String.format("%.1f",averageResponse.getAverageDriver().getRerataRating());
                tvRerataRating.setText(rating);
//
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(ProfileDriverActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch (Exception e) {
                    Toast.makeText(ProfileDriverActivity.this, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Authorization", "Bearer "+
                        driverPreferences.getDriverLogin().getAccessToken());
                return headers;
            }

        };
        VolleySingleton.getInstance(ProfileDriverActivity.this).addToRequestQueue(stringRequest);

        //Edit Profile Driver
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileDriverActivity.this);
                View newLayout = LayoutInflater.from(builder.getContext())
                        .inflate(R.layout.edit_profile_driver, null);

                TextInputEditText editTglLahir, editNama, editAlamat, editJenisKelamin, editNoTelepon;
                MaterialButton btnSave, btnClose;

                editNama = newLayout.findViewById(R.id.editNamaDriver);
                editTglLahir = newLayout.findViewById(R.id.editTglLahirDriver);
                editJenisKelamin = newLayout.findViewById(R.id.editJenisKelaminDriver);
                editAlamat = newLayout.findViewById(R.id.editAlamatDriver);
                editNoTelepon = newLayout.findViewById(R.id.editNoTeleponDriver);
                btnSave = newLayout.findViewById(R.id.btnSaveProfileDriver);
                btnClose = newLayout.findViewById(R.id.btnCloseProfileDriver);
                editNama.setText(driverPreferences.getDriverLogin().getNamaDriver());
                editTglLahir.setText(driverPreferences.getDriverLogin().getTglLahirDriver());
                editJenisKelamin.setText(driverPreferences.getDriverLogin().getJenisKelaminDriver());
                editAlamat.setText(driverPreferences.getDriverLogin().getAlamatDriver());
                editNoTelepon.setText(driverPreferences.getDriverLogin().getNoTeleponDriver());

                Calendar calendar = Calendar.getInstance();
                final int year= calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);

                editTglLahir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(ProfileDriverActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month = month +1;
                                if(month<10){
                                    String formattedDate = year+"-0"+month+"-"+day;
                                    editTglLahir.setText(formattedDate);
                                }else{
                                    String formattedDate = year+"-"+month+"-"+day;
                                    editTglLahir.setText(formattedDate);
                                }


                            }
                        }, year, month,day);
                        datePickerDialog.show();
                    }
                });

                builder.setView(newLayout);
                AlertDialog popup = builder.create();
                popup.show();

                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popup.dismiss();
                    }
                });

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Driver newDriver = new Driver(
                                driver.getIdDriver(),
                                editNama.getText().toString(),
                                editAlamat.getText().toString(),
                                editTglLahir.getText().toString(),
                                editJenisKelamin.getText().toString(),
                                editNoTelepon.getText().toString(),
                                driver.getEmail(),
                                driver.getPassword(),
                                driver.getFotoDriver(),
                                driver.getTarifDriverHarian(),
                                driver.getStatusKetersediaan(),
                                driver.getKemampuanBahasaAsing(),
                                driver.getAccessToken()
                        );

                        StringRequest stringRequest = new StringRequest(POST,
                                DriverApi.UPDATE_URL+driver.getIdDriver()+"/update", new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Gson gson = new Gson();

                                DriverResponse driverResponse = gson.fromJson(response, DriverResponse.class);
                                Log.d("API RESPONSE", response);
                                driverPreferences.setLogin(
                                        driver.getIdDriver(),
                                        driverResponse.getDriver().getNamaDriver(),
                                        driverResponse.getDriver().getAlamatDriver(),
                                        driverResponse.getDriver().getTglLahirDriver(),
                                        driverResponse.getDriver().getJenisKelaminDriver(),
                                        driverResponse.getDriver().getNoTeleponDriver(),
                                        driverResponse.getDriver().getEmail(),
                                        driverResponse.getDriver().getPassword(),
                                        driverResponse.getDriver().getFotoDriver(),
                                        driverResponse.getDriver().getTarifDriverHarian(),
                                        driverResponse.getDriver().getStatusKetersediaan(),
                                        driverResponse.getDriver().getKemampuanBahasaAsing(),
                                        driver.getAccessToken()
                                );
                                Toast.makeText(ProfileDriverActivity.this, driverResponse.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                popup.dismiss();
                                Intent returnIntent = new Intent(ProfileDriverActivity.this, ProfileDriverActivity.class);
                                startActivity(returnIntent);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                try {
                                    String responseBody = new String(error.networkResponse.data,
                                            StandardCharsets.UTF_8);
                                    JSONObject errors = new JSONObject(responseBody);

                                    Toast.makeText(ProfileDriverActivity.this, errors.getString("message"),
                                            Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    Toast.makeText(ProfileDriverActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }) {
                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                HashMap<String, String> headers = new HashMap<String, String>();
                                headers.put("Accept", "application/json");
                                headers.put("Authorization", "Bearer "+
                                        driverPreferences.getDriverLogin().getAccessToken());
                                return headers;
                            }
                            @Override
                            public byte[] getBody() throws AuthFailureError {
                                Gson gson = new Gson();
                                String requestBody = gson.toJson(newDriver);
                                return requestBody.getBytes(StandardCharsets.UTF_8);
                            }
                            @Override
                            public String getBodyContentType() {
                                return "application/json";
                            }
                        };
                        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

                    }
                });
            }
        });

        //Edit Status Driver
        btnEditStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileDriverActivity.this);
                View newLayout = LayoutInflater.from(builder.getContext())
                        .inflate(R.layout.edit_status_ketersediaan_driver, null);
                MaterialCardView cvStatus;
                TextView tvStatusKonfirmasi;
                MaterialButton btnKonfirmasi, btnBatal;
                cvStatus = newLayout.findViewById(R.id.cv_status_ketersediaan_konfirmasi);
                tvStatusKonfirmasi = newLayout.findViewById(R.id.tv_status_ketersediaan_konfirmasi);
                btnKonfirmasi = newLayout.findViewById(R.id.btnKonfirmasiStatus);
                btnBatal = newLayout.findViewById(R.id.btnBatalStatus);
                Log.d("status",String.valueOf(driver.getStatusKetersediaan()));
                if(driver.getStatusKetersediaan()==0) {
                    tvStatusKonfirmasi.setText("Tersedia");
                    cvStatus.setBackgroundColor(Color.parseColor("#20B2AA"));
                }else if(driver.getStatusKetersediaan()==1){
                    tvStatusKonfirmasi.setText("Tidak Tersedia");
                    cvStatus.setBackgroundColor(Color.parseColor("#FF6347"));
                }

                builder.setView(newLayout);
                AlertDialog popup = builder.create();
                popup.show();

                btnBatal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popup.dismiss();
                    }
                });

                btnKonfirmasi.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if(driver.getStatusKetersediaan()==0){
                            Driver newDriver = new Driver(
                                    driver.getIdDriver(),
                                    driver.getNamaDriver(),
                                    driver.getAlamatDriver(),
                                    driver.getTglLahirDriver(),
                                    driver.getJenisKelaminDriver(),
                                    driver.getNoTeleponDriver(),
                                    driver.getEmail(),
                                    driver.getPassword(),
                                    driver.getFotoDriver(),
                                    driver.getTarifDriverHarian(),
                                    1,
                                    driver.getKemampuanBahasaAsing(),
                                    driver.getAccessToken()
                            );
                            StringRequest stringRequest = new StringRequest(POST,
                                    DriverApi.UPDATE_STATUS+driver.getIdDriver()+"/update-status", new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Gson gson = new Gson();

                                    DriverResponse driverResponse = gson.fromJson(response, DriverResponse.class);
                                    Log.d("API RESPONSE", response);
                                    driverPreferences.setLogin(
                                            driver.getIdDriver(),
                                            driver.getNamaDriver(),
                                            driver.getAlamatDriver(),
                                            driver.getTglLahirDriver(),
                                            driver.getJenisKelaminDriver(),
                                            driver.getNoTeleponDriver(),
                                            driver.getEmail(),
                                            driver.getPassword(),
                                            driver.getFotoDriver(),
                                            driver.getTarifDriverHarian(),
                                            driverResponse.getDriver().getStatusKetersediaan(),
                                            driver.getKemampuanBahasaAsing(),
                                            driver.getAccessToken()
                                    );
                                    Toast.makeText(ProfileDriverActivity.this, driverResponse.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                    popup.dismiss();
                                    Intent returnIntent = new Intent(ProfileDriverActivity.this, ProfileDriverActivity.class);
                                    startActivity(returnIntent);
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    try {
                                        String responseBody = new String(error.networkResponse.data,
                                                StandardCharsets.UTF_8);
                                        JSONObject errors = new JSONObject(responseBody);

                                        Toast.makeText(ProfileDriverActivity.this, errors.getString("message"),
                                                Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                        Toast.makeText(ProfileDriverActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }) {
                                @Override
                                public Map<String, String> getHeaders() throws AuthFailureError {
                                    HashMap<String, String> headers = new HashMap<String, String>();
                                    headers.put("Accept", "application/json");
                                    headers.put("Authorization", "Bearer "+
                                            driverPreferences.getDriverLogin().getAccessToken());
                                    return headers;
                                }
                                @Override
                                public byte[] getBody() throws AuthFailureError {
                                    Gson gson = new Gson();
                                    String requestBody = gson.toJson(newDriver);
                                    return requestBody.getBytes(StandardCharsets.UTF_8);
                                }
                                @Override
                                public String getBodyContentType() {
                                    return "application/json";
                                }
                            };
                            VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
                        }else{
                            Driver newDriver = new Driver(
                                    driver.getIdDriver(),
                                    driver.getNamaDriver(),
                                    driver.getAlamatDriver(),
                                    driver.getTglLahirDriver(),
                                    driver.getJenisKelaminDriver(),
                                    driver.getNoTeleponDriver(),
                                    driver.getEmail(),
                                    driver.getPassword(),
                                    driver.getFotoDriver(),
                                    driver.getTarifDriverHarian(),
                                    0,
                                    driver.getKemampuanBahasaAsing(),
                                    driver.getAccessToken()
                            );
                            StringRequest stringRequest = new StringRequest(POST,
                                    DriverApi.UPDATE_STATUS+driver.getIdDriver()+"/update-status", new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Gson gson = new Gson();

                                    DriverResponse driverResponse = gson.fromJson(response, DriverResponse.class);
                                    Log.d("API RESPONSE", response);
                                    driverPreferences.setLogin(
                                            driver.getIdDriver(),
                                            driver.getNamaDriver(),
                                            driver.getAlamatDriver(),
                                            driver.getTglLahirDriver(),
                                            driver.getJenisKelaminDriver(),
                                            driver.getNoTeleponDriver(),
                                            driver.getEmail(),
                                            driver.getPassword(),
                                            driver.getFotoDriver(),
                                            driver.getTarifDriverHarian(),
                                            driverResponse.getDriver().getStatusKetersediaan(),
                                            driver.getKemampuanBahasaAsing(),
                                            driver.getAccessToken()
                                    );
                                    Toast.makeText(ProfileDriverActivity.this, driverResponse.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                    popup.dismiss();
                                    Intent returnIntent = new Intent(ProfileDriverActivity.this, ProfileDriverActivity.class);
                                    startActivity(returnIntent);
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    try {
                                        String responseBody = new String(error.networkResponse.data,
                                                StandardCharsets.UTF_8);
                                        JSONObject errors = new JSONObject(responseBody);

                                        Toast.makeText(ProfileDriverActivity.this, errors.getString("message"),
                                                Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                        Toast.makeText(ProfileDriverActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }) {
                                @Override
                                public Map<String, String> getHeaders() throws AuthFailureError {
                                    HashMap<String, String> headers = new HashMap<String, String>();
                                    headers.put("Accept", "application/json");
                                    headers.put("Authorization", "Bearer "+
                                            driverPreferences.getDriverLogin().getAccessToken());
                                    return headers;
                                }
                                @Override
                                public byte[] getBody() throws AuthFailureError {
                                    Gson gson = new Gson();
                                    String requestBody = gson.toJson(newDriver);
                                    return requestBody.getBytes(StandardCharsets.UTF_8);
                                }
                                @Override
                                public String getBodyContentType() {
                                    return "application/json";
                                }
                            };
                            VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
                        }

                    }
                });


            }
        });
    }
}