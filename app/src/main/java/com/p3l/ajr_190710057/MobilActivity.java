package com.p3l.ajr_190710057;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.p3l.ajr_190710057.adapters.MobilAdapter;
import com.p3l.ajr_190710057.api.MobilApi;
import com.p3l.ajr_190710057.models.Customer;
import com.p3l.ajr_190710057.models.Mobil;
import com.p3l.ajr_190710057.preferences.CustomerPreferences;
import com.p3l.ajr_190710057.responses.MobilResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MobilActivity extends AppCompatActivity {
    private CustomerPreferences customerPreferences;
    private Customer customer;
    private MobilAdapter adapter;
    private RequestQueue queue;
    private RecyclerView rvMobil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobil);

        ImageButton btnBack = findViewById(R.id.btnBackFromMobil);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MobilActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        queue = Volley.newRequestQueue(this);
        customerPreferences = new CustomerPreferences(this);
        customer = customerPreferences.getCustomerLogin();
        rvMobil = findViewById(R.id.rv_mobil);
        adapter = new MobilAdapter(new ArrayList<>(), this);
        rvMobil.setLayoutManager(new LinearLayoutManager(MobilActivity.this,
                LinearLayoutManager.VERTICAL, false));
        rvMobil.setAdapter(adapter);

        getAllMobil();

    }

    private void getAllMobil(){
        StringRequest stringRequest = new StringRequest(GET, MobilApi.GET_ALL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                MobilResponse mobilResponse = gson.fromJson(response, MobilResponse.class);

                adapter.setMobilList(mobilResponse.getMobilList());
                Log.d("API RESPONSE", response);
                Log.d("Cek isi list",String.valueOf(adapter.getItemCount()));
                rvMobil.setAdapter(adapter);

                Toast.makeText(MobilActivity.this, mobilResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(MobilActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch (Exception e) {
                    Toast.makeText(MobilActivity.this, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Authorization", "Bearer "+
                        customerPreferences.getCustomerLogin().getAccess_token());
                return headers;
            }

        };

        queue.add(stringRequest);
    }
}