package com.p3l.ajr_190710057;

import static com.android.volley.Request.Method.POST;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.p3l.ajr_190710057.api.CustomerApi;
import com.p3l.ajr_190710057.models.Customer;
import com.p3l.ajr_190710057.preferences.CustomerPreferences;
import com.p3l.ajr_190710057.responses.CustomerResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ProfileCustomerActivity extends AppCompatActivity {
    private CustomerPreferences customerPreferences;
    private Customer customer;
    private Context context;
    private MaterialButton btnEditProfile;
    private ImageButton btnBack;
    private TextView tvNama, tvNo, tvAlamat, tvTglLahir, tvJenisKelamin, tvNoTelepon, tvEmail, tvTipeSewa, tvNomorKartu, tvNomorSIM;
    private ImageView ivKartu, ivSim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_customer);
        context = this;
        tvNama = findViewById(R.id.tv_nama_customer_p);
        tvNo = findViewById(R.id.tv_id_customer_p);
        tvAlamat = findViewById(R.id.tv_alamat_customer_p);
        tvTglLahir = findViewById(R.id.tv_tgl_lahir_customer_p);
        tvJenisKelamin = findViewById(R.id.tv_jenis_kelamin_customer_p);
        tvEmail = findViewById(R.id.tv_email_customer_p);
        tvTipeSewa = findViewById(R.id.tv_tipe_sewa_customer_p);
        tvNomorKartu = findViewById(R.id.tv_no_kartu_identitas_customer_p);
        tvNoTelepon = findViewById(R.id.tv_no_telepon_customer_p);
        tvNomorSIM = findViewById(R.id.tv_no_sim_customer_p);
        ivKartu = findViewById(R.id.iv_kartu_identitas);
        ivSim = findViewById(R.id.iv_sim_customer);
        btnBack = findViewById(R.id.btnBackFromProfileCustomer);
        btnEditProfile = findViewById(R.id.btnEditProfileCustomer);

        customerPreferences = new CustomerPreferences(this);
        customer = customerPreferences.getCustomerLogin();

        tvNo.setText(customer.getIdCustomer());
        tvNama.setText(customer.getNamaCustomer());
        tvAlamat.setText(customer.getAlamatCustomer());
        tvTglLahir.setText(customer.getTglLahirCustomer());
        tvJenisKelamin.setText(customer.getJenisKelaminCustomer());
        tvEmail.setText(customer.getEmail());
        tvNomorSIM.setText(customer.getNoSimCustomer());
        tvNomorKartu.setText(customer.getNoKartuIdentitasCustomer());
        tvNoTelepon.setText(customer.getNoTeleponCustomer());
        if(customer.getTipeSewaCustomer()==1){
            tvTipeSewa.setText("Diperbolehkan Sewa Mobil tanpa Driver");
        }else if(customer.getTipeSewaCustomer()==null){
            tvTipeSewa.setText("Belum Diverifikasi");
        }else{
            tvTipeSewa.setText("Hanya Diperbolehkan Sewa Mobil dengan Driver");
        }
        String urlImage = "http://192.168.100.7:8000/storage/kartu_identitas_customer/"+customer.getKartuIdentitasCustomer();
        Glide.with(ProfileCustomerActivity.this)
                .load(urlImage)
                .apply(new RequestOptions().override(1280, 720))
                .into(ivKartu);
        if(customer.getSimCustomer()==null){
            tvNomorSIM.setVisibility(View.GONE);
            ivSim.setVisibility(View.GONE);
        }else{
            String urlImage1 = "http://192.168.100.7:8000/storage/sim_customer/"+customer.getSimCustomer();
            Glide.with(ProfileCustomerActivity.this)
                    .load(urlImage1)
                    .apply(new RequestOptions().override(1280, 720))
                    .into(ivSim);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileCustomerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileCustomerActivity.this);
                View newLayout  = LayoutInflater.from(builder.getContext())
                        .inflate(R.layout.edit_profile_customer, null);
                 TextInputEditText editTglLahir, editNama, editAlamat, editJenisKelamin, editNoTelepon;
                 MaterialButton btnSave, btnClose;
                TextInputLayout tfNama;
                editNama = newLayout.findViewById(R.id.editNamaCustomer);
                editTglLahir = newLayout.findViewById(R.id.editTglLahirCustomer);
                editJenisKelamin = newLayout.findViewById(R.id.editJenisKelaminCustomer);
                editAlamat = newLayout.findViewById(R.id.editAlamatCustomer);
                editNoTelepon = newLayout.findViewById(R.id.editNoTeleponCustomer);
                btnSave = newLayout.findViewById(R.id.btnSaveProfileCustomer);
                btnClose = newLayout.findViewById(R.id.btnCloseProfileCustomer);
                editNama.setText(customerPreferences.getCustomerLogin().getNamaCustomer());
                editTglLahir.setText(customerPreferences.getCustomerLogin().getTglLahirCustomer());
                editJenisKelamin.setText(customerPreferences.getCustomerLogin().getJenisKelaminCustomer());
                editAlamat.setText(customerPreferences.getCustomerLogin().getAlamatCustomer());
                editNoTelepon.setText(customerPreferences.getCustomerLogin().getNoTeleponCustomer());

                Calendar calendar = Calendar.getInstance();
                final int year= calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);

                editTglLahir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(ProfileCustomerActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileCustomerActivity.this);
                        View newLayout  = LayoutInflater.from(builder.getContext())
                                .inflate(R.layout.alert_konfirmasi_edit_customer, null);
                        MaterialButton btnKonfirmasi, btnBatal;
                        builder.setView(newLayout);
                        AlertDialog popup = builder.create();
                        popup.show();
                        btnKonfirmasi = newLayout.findViewById(R.id.btnKonfirmasiEditCustomer);
                        btnBatal = newLayout.findViewById(R.id.btnBatalEditCustomer);
                        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Customer customer1 = new Customer(
                                        customerPreferences.getCustomerLogin().getIdCustomer(),
                                        editNama.getText().toString(),
                                        editAlamat.getText().toString(),
                                        editTglLahir.getText().toString(),
                                        editJenisKelamin.getText().toString(),
                                        editNoTelepon.getText().toString(),
                                        customerPreferences.getCustomerLogin().getNoKartuIdentitasCustomer(),
                                        customerPreferences.getCustomerLogin().getKartuIdentitasCustomer(),
                                        customerPreferences.getCustomerLogin().getNoSimCustomer(),
                                        customerPreferences.getCustomerLogin().getSimCustomer(),
                                        customerPreferences.getCustomerLogin().getTipeSewaCustomer(),
                                        customerPreferences.getCustomerLogin().getEmail(),
                                        customerPreferences.getCustomerLogin().getPassword(),
                                        customerPreferences.getCustomerLogin().getAccess_token()
                                );
                                StringRequest stringRequest = new StringRequest(POST,
                                        CustomerApi.UPDATE_URL+customer.getIdCustomer(), new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Gson gson = new Gson();

                                        CustomerResponse customerResponse = gson.fromJson(response, CustomerResponse.class);
                                        Log.d("API RESPONSE", response);
                                        customerPreferences.setLogin(
                                                customer.getIdCustomer(),
                                                customerResponse.getCustomer().getNamaCustomer(),
                                                customerResponse.getCustomer().getAlamatCustomer(),
                                                customerResponse.getCustomer().getTglLahirCustomer(),
                                                customerResponse.getCustomer().getJenisKelaminCustomer(),
                                                customerResponse.getCustomer().getNoTeleponCustomer(),
                                                customerResponse.getCustomer().getNoKartuIdentitasCustomer(),
                                                customerResponse.getCustomer().getKartuIdentitasCustomer(),
                                                customerResponse.getCustomer().getNoSimCustomer(),
                                                customerResponse.getCustomer().getSimCustomer(),
                                                customerResponse.getCustomer().getEmail(),
                                                customerResponse.getCustomer().getPassword(),
                                                customerResponse.getCustomer().getTipeSewaCustomer(),
                                                customer.getAccess_token()
                                        );
                                        Toast.makeText(ProfileCustomerActivity.this, customerResponse.getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                        popup.dismiss();
                                        Intent returnIntent = new Intent(ProfileCustomerActivity.this, ProfileCustomerActivity.class);
                                        startActivity(returnIntent);
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        try {
                                            String responseBody = new String(error.networkResponse.data,
                                                    StandardCharsets.UTF_8);
                                            JSONObject errors = new JSONObject(responseBody);

                                            Toast.makeText(ProfileCustomerActivity.this, errors.getString("message"),
                                                    Toast.LENGTH_SHORT).show();
                                        } catch (Exception e) {
                                            Toast.makeText(ProfileCustomerActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }) {
                                    @Override
                                    public Map<String, String> getHeaders() throws AuthFailureError {
                                        HashMap<String, String> headers = new HashMap<String, String>();
                                        headers.put("Accept", "application/json");
                                        headers.put("Authorization", "Bearer "+
                                                customerPreferences.getCustomerLogin().getAccess_token());
                                        return headers;
                                    }
                                    @Override
                                    public byte[] getBody() throws AuthFailureError {
                                        Gson gson = new Gson();
                                        String requestBody = gson.toJson(customer1);
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
                        btnBatal.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popup.dismiss();
                            }
                        });

                    }
                });




            }
        });

    }

}