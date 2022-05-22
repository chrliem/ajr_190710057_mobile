package com.p3l.ajr_190710057;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.p3l.ajr_190710057.models.Driver;
import com.p3l.ajr_190710057.preferences.DriverPreferences;

public class ProfileDriverActivity extends AppCompatActivity {
    private DriverPreferences driverPreferences;
    private Driver driver;
    private Context context;
    private MaterialButton btnEditProfile, btnEditStatus;
    private ImageButton btnBack;
    private TextView tvIdDriver, tvNama, tvTglLahir, tvAlamat, tvJenisKelamin,
            tvNoTelepon, tvTarif, tvKemampuanBahasa, tvStatusKetersediaan;
    private RatingBar rtRerata;

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

        btnEditProfile = findViewById(R.id.btnEditProfileDriver);
        btnEditStatus = findViewById(R.id.btnEditStatusDriver);

        btnBack = findViewById(R.id.btnBackFromProfileDriver);

        driverPreferences = new DriverPreferences(this);
        driver = driverPreferences.getDriverLogin();

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
        }else{
            tvStatusKetersediaan.setText("Tersedia");
        }

        //Untuk rerata, buat fungsi getAveragebyIdDriver di laravel
        //Add url di DriverApi untuk getAveragebyIdDriver
        //Buat average response, nanti dtampung disana dari alravel
    }
}