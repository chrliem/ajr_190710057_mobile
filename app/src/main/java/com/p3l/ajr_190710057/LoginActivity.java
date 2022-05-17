package com.p3l.ajr_190710057;

import static com.android.volley.Request.Method.POST;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.p3l.ajr_190710057.api.CustomerApi;
import com.p3l.ajr_190710057.api.LoginApi;
import com.p3l.ajr_190710057.models.User;
import com.p3l.ajr_190710057.preferences.CustomerPreferences;
import com.p3l.ajr_190710057.preferences.DriverPreferences;
import com.p3l.ajr_190710057.preferences.PegawaiPreferences;
import com.p3l.ajr_190710057.responses.CustomerResponse;
import com.p3l.ajr_190710057.responses.DriverResponse;
import com.p3l.ajr_190710057.responses.PegawaiResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextInputLayout txtEmail, txtPassword;
    private CustomerPreferences customerPreferences;
    private PegawaiPreferences pegawaiPreferences;
    private DriverPreferences driverPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        customerPreferences = new CustomerPreferences(LoginActivity.this);
        pegawaiPreferences = new PegawaiPreferences(LoginActivity.this);
        driverPreferences = new DriverPreferences(LoginActivity.this);

        txtEmail = findViewById(R.id.txtEmailLogin);
        txtPassword = findViewById(R.id.txtPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);

        checkLogin();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getEditText().getText().toString();
                String password = txtPassword.getEditText().getText().toString();

                if (email.trim().isEmpty() || password.trim().isEmpty()) {
                    if (email.trim().isEmpty()) {
                        txtEmail.setError("Email is required!");
                    }
                    if (password.trim().isEmpty()) {
                        txtPassword.setError("Password is required");
                    }
                } else {
                    login();
                }
            }
        });

    }

    public void login(){
        final String email = txtEmail.getEditText().getText().toString().trim();
        final String password = txtPassword.getEditText().getText().toString().trim();

        User user = new User(email, password);

        StringRequest stringRequest = new StringRequest(POST, LoginApi.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        CustomerResponse customerResponse = gson.fromJson(response, CustomerResponse.class);
                        PegawaiResponse pegawaiResponse = gson.fromJson(response, PegawaiResponse.class);
                        DriverResponse driverResponse = gson.fromJson(response, DriverResponse.class);
                        if(customerResponse.getMessage().equals("Authenticated as Customer")){
                            Toast.makeText(LoginActivity.this, customerResponse.getMessage(),
                                    Toast.LENGTH_SHORT).show();

                            customerPreferences.setLogin(
                                    customerResponse.getCustomer().getIdCustomer(),
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
                                    customerResponse.getAccessToken()
                            );
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else if(pegawaiResponse.getMessage().equals("Authenticated as Pegawai")){
                            Toast.makeText(LoginActivity.this, pegawaiResponse.getMessage(),
                                    Toast.LENGTH_SHORT).show();

                            pegawaiPreferences.setLogin(
                                    pegawaiResponse.getPegawai().getIdPegawai(),
                                    pegawaiResponse.getPegawai().getIdRole(),
                                    pegawaiResponse.getPegawai().getNamaPegawai(),
                                    pegawaiResponse.getPegawai().getTglLahirPegawai(),
                                    pegawaiResponse.getPegawai().getJenisKelaminPegawai(),
                                    pegawaiResponse.getPegawai().getAlamatPegawai(),
                                    pegawaiResponse.getPegawai().getNoTeleponPegawai(),
                                    pegawaiResponse.getPegawai().getFotoPegawai(),
                                    pegawaiResponse.getPegawai().getEmail(),
                                    pegawaiResponse.getPegawai().getPassword(),
                                    pegawaiResponse.getAccessToken()
                            );
                            Intent intent = new Intent(LoginActivity.this, MainActivity2.class);
                            startActivity(intent);
                            finish();
                        }else if(driverResponse.getMessage().equals("Authenticated as Driver")){
                            Toast.makeText(LoginActivity.this, driverResponse.getMessage(),
                                    Toast.LENGTH_SHORT).show();

                            driverPreferences.setLogin(
                                    driverResponse.getDriver().getIdDriver(),
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
                                    driverResponse.getAccessToken()
                            );
                            Intent intent = new Intent(LoginActivity.this, MainActivity3.class);
                            startActivity(intent);
                            finish();
                        }

//                        Intent intent = new Intent(LoginActivity.this, MainActivity2.class);
//                        startActivity(intent);
//                        finish();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(LoginActivity.this, errors.getString("message"),
                            Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(LoginActivity.this, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
            @Override
            public byte[] getBody() throws AuthFailureError{
                Gson gson = new Gson();
                String requestBody = gson.toJson(user);
                return requestBody.getBytes(StandardCharsets.UTF_8);
            }
//            @Override
//            protected Map<String, String> getParams()throws AuthFailureError{
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("email", email);
//                params.put("password", password);
//                return params;
//            }

            @Override
            public String getBodyContentType(){
                return "application/json";
            }

        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void checkLogin(){
        if(customerPreferences.checkLogin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }else if(pegawaiPreferences.checkLogin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }
}