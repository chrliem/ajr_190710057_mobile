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
import com.p3l.ajr_190710057.adapters.MobilAdapter;
import com.p3l.ajr_190710057.adapters.RiwayatCustomerAdapter;
import com.p3l.ajr_190710057.api.MobilApi;
import com.p3l.ajr_190710057.api.RiwayatCustomerApi;
import com.p3l.ajr_190710057.models.Customer;
import com.p3l.ajr_190710057.preferences.CustomerPreferences;
import com.p3l.ajr_190710057.responses.MobilResponse;
import com.p3l.ajr_190710057.responses.RiwayatCustomerResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RiwayatCustomerActivity extends AppCompatActivity {
    private CustomerPreferences customerPreferences;
    private Customer customer;
    private RiwayatCustomerAdapter adapter;
    private RequestQueue queue;
    private RecyclerView rvRiwayatCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_customer);

        ImageButton btnBack = findViewById(R.id.btnBackFromRiwayatCustomer);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RiwayatCustomerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        queue = Volley.newRequestQueue(this);
        customerPreferences = new CustomerPreferences(this);
        customer = customerPreferences.getCustomerLogin();
        rvRiwayatCustomer = findViewById(R.id.rv_riwayat_customer);
        adapter = new RiwayatCustomerAdapter(new ArrayList<>(), this);
        rvRiwayatCustomer.setLayoutManager(new LinearLayoutManager(RiwayatCustomerActivity.this,
                LinearLayoutManager.VERTICAL, false));
        rvRiwayatCustomer.setAdapter(adapter);

        getAllTransaksi();
    }

    private void getAllTransaksi(){
        StringRequest stringRequest = new StringRequest(GET, RiwayatCustomerApi.GET_ALL_URL+customerPreferences.getCustomerLogin().getIdCustomer(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                RiwayatCustomerResponse riwayatCustomerResponse = gson.fromJson(response, RiwayatCustomerResponse.class);

                adapter.setRiwayatCustomerList(riwayatCustomerResponse.getRiwayatCustomerList());
                Log.d("API RESPONSE", response);
                Log.d("Cek isi list",String.valueOf(adapter.getItemCount()));
                rvRiwayatCustomer.setAdapter(adapter);

//                Toast.makeText(RiwayatCustomerActivity.this, riwayatCustomerResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(RiwayatCustomerActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch (Exception e) {
                    Toast.makeText(RiwayatCustomerActivity.this, e.getMessage(),
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