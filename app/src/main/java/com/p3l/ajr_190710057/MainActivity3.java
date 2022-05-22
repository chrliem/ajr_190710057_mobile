package com.p3l.ajr_190710057;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.p3l.ajr_190710057.models.Driver;
import com.p3l.ajr_190710057.preferences.DriverPreferences;

public class MainActivity3 extends AppCompatActivity {
    CardView btnViewProfile, btnViewRiwayat;
    Button btnLogout;
    TextView tvNamaWelcome;
    DriverPreferences driverPreferences;
    Driver driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnViewProfile = findViewById(R.id.btnViewProfileDriver);
        btnViewRiwayat = findViewById(R.id.btnViewHistoryDriver);

        btnViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, ProfileDriverActivity.class);
                startActivity(intent);
            }
        });

        btnViewRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, RiwayatDriverActivity.class);
                startActivity(intent);
            }
        });

        tvNamaWelcome = findViewById(R.id.tvNamaWelcomeDriver);
        driverPreferences = new DriverPreferences(this);
        driver = driverPreferences.getDriverLogin();
        tvNamaWelcome.setText(driver.getNamaDriver());

        btnLogout = findViewById(R.id.btnLogoutDriver);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                driverPreferences.logout();
                if(!driverPreferences.checkLogin()){
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        });
    }
}