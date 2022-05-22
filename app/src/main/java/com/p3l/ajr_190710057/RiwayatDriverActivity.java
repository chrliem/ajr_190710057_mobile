package com.p3l.ajr_190710057;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.p3l.ajr_190710057.adapters.RiwayatCustomerAdapter;
import com.p3l.ajr_190710057.adapters.RiwayatDriverAdapter;
import com.p3l.ajr_190710057.api.RiwayatCustomerApi;
import com.p3l.ajr_190710057.api.RiwayatDriverApi;
import com.p3l.ajr_190710057.models.Driver;
import com.p3l.ajr_190710057.preferences.CustomerPreferences;
import com.p3l.ajr_190710057.preferences.DriverPreferences;
import com.p3l.ajr_190710057.responses.RiwayatCustomerResponse;
import com.p3l.ajr_190710057.responses.RiwayatDriverResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RiwayatDriverActivity extends AppCompatActivity {
    private DriverPreferences driverPreferences;
    private Driver driver;
    private RiwayatDriverAdapter adapter;
    private RequestQueue queue;
    private RecyclerView rvRiwayatDriver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_driver);

        ImageButton btnBack = findViewById(R.id.btnBackFromRiwayatDriver);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RiwayatDriverActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        queue = Volley.newRequestQueue(this);
        driverPreferences = new DriverPreferences(this);
        driver = driverPreferences.getDriverLogin();
        rvRiwayatDriver = findViewById(R.id.rv_riwayat_driver);
        adapter = new RiwayatDriverAdapter(new ArrayList<>(), this);
        rvRiwayatDriver.setLayoutManager(new LinearLayoutManager(RiwayatDriverActivity.this,
                LinearLayoutManager.VERTICAL, false));
        rvRiwayatDriver.setAdapter(adapter);

        getAllTransaksi();
    }

    private void getAllTransaksi(){
        StringRequest stringRequest = new StringRequest(GET, RiwayatDriverApi.GET_ALL_URL+driverPreferences.getDriverLogin().getIdDriver()+"/history", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                RiwayatDriverResponse riwayatDriverResponse = gson.fromJson(response, RiwayatDriverResponse.class);

                adapter.setRiwayatDriverList(riwayatDriverResponse.getRiwayatDriverList());
                Log.d("API RESPONSE", response);
                Log.d("Cek isi list",String.valueOf(adapter.getItemCount()));
                rvRiwayatDriver.setAdapter(adapter);

//                Toast.makeText(RiwayatCustomerActivity.this, riwayatCustomerResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(RiwayatDriverActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch (Exception e) {
                    Toast.makeText(RiwayatDriverActivity.this, e.getMessage(),
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

        queue.add(stringRequest);
    }
}