package com.p3l.ajr_190710057;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.p3l.ajr_190710057.models.Customer;
import com.p3l.ajr_190710057.preferences.CustomerPreferences;

public class MainActivity extends AppCompatActivity {
    CardView btnViewMobil, btnViewPromo, btnViewProfile, btnViewHistory;
    Button btnLogout;
    TextView tvNamaWelcome;
    CustomerPreferences customerPreferences;
    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnViewMobil = findViewById(R.id.btnViewMobil);

        btnViewMobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MobilActivity.class);
                startActivity(intent);
            }
        });

        btnViewPromo = findViewById(R.id.btnViewPromo);

        btnViewPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PromoActivity.class);
                startActivity(intent);
            }
        });

        btnViewHistory = findViewById(R.id.btnViewHistory);

        btnViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RiwayatCustomerActivity.class);
                startActivity(intent);
            }
        });

        tvNamaWelcome = findViewById(R.id.tvNamaWelcome);
        customerPreferences = new CustomerPreferences(this);
        customer = customerPreferences.getCustomerLogin();
        tvNamaWelcome.setText(customer.getNamaCustomer());

        btnLogout = findViewById(R.id.btnLogoutCustomer);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerPreferences.logout();
                if(!customerPreferences.checkLogin()){
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        });



    }


}

