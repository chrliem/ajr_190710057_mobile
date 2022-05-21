package com.p3l.ajr_190710057;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
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
import com.p3l.ajr_190710057.adapters.PromoAdapter;
import com.p3l.ajr_190710057.api.MobilApi;
import com.p3l.ajr_190710057.api.PromoApi;
import com.p3l.ajr_190710057.models.Customer;
import com.p3l.ajr_190710057.preferences.CustomerPreferences;
import com.p3l.ajr_190710057.responses.MobilResponse;
import com.p3l.ajr_190710057.responses.PromoResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PromoActivity extends AppCompatActivity {
    private CustomerPreferences customerPreferences;
    private Customer customer;
    private PromoAdapter adapter;
    private RequestQueue queue;
    private RecyclerView rvPromo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);

        ImageButton btnBack = findViewById(R.id.btnBackFromPromo);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PromoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        queue = Volley.newRequestQueue(this);
        customerPreferences = new CustomerPreferences(this);
        customer = customerPreferences.getCustomerLogin();

        rvPromo = findViewById(R.id.rv_promo);
        adapter = new PromoAdapter(new ArrayList<>(), this);

        rvPromo.setLayoutManager(new LinearLayoutManager(PromoActivity.this));



        getAllPromo();
    }

    private void getAllPromo(){
        StringRequest stringRequest = new StringRequest(GET, PromoApi.GET_ALL_URL,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                PromoResponse promoResponse = gson.fromJson(response, PromoResponse.class);

                adapter.setPromoList(promoResponse.getPromoList());
                rvPromo.setAdapter(adapter);
                Log.d("API RESPONSE", response);
                Toast.makeText(PromoActivity.this, promoResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(PromoActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch (Exception e) {
                    Toast.makeText(PromoActivity.this, e.getMessage(),
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