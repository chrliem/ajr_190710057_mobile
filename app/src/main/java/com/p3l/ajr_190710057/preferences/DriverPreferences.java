package com.p3l.ajr_190710057.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.p3l.ajr_190710057.models.Driver;

public class DriverPreferences {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_ID ="id_driver";
    public static final String KEY_NAMA = "nama_driver";
    public static final String KEY_ALAMAT = "alamat_driver";
    public static final String KEY_TGLLAHIR = "tgl_lahir_driver";
    public static final String KEY_JENISKELAMIN = "jenis_kelamin_driver";
    public static final String KEY_NOTELEPON = "no_telepon_driver";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_FOTODRIVER = "foto_driver";
    public static final String KEY_TARIFDRIVER = "tarif_driver_harian";
    public static final String KEY_STATUSKETERSEDIAAN = "status_ketersediaan_driver";
    public static final String KEY_KEMAMPUANBHSASING = "kemampuan_bahasa_asing";
    public static final String ACCESS_TOKEN = "access_token";

    public DriverPreferences(Context context){
        this.context = context;

        sharedPreferences = context.getSharedPreferences("driverPreferences",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLogin(String idDriver, String namaDriver, String alamatDriver,
                         String tglLahirDriver, String jenisKelaminDriver, String noTeleponDriver,
                         String email, String password, String fotoDriver,
                         float tarifDriverHarian, Integer statusKetersediaan, Integer kemampuanBahasaAsing,
                         String accessToken){
        editor.putString(KEY_ID, idDriver);
        editor.putString(KEY_NAMA, namaDriver);
        editor.putString(KEY_ALAMAT, alamatDriver);
        editor.putString(KEY_TGLLAHIR, tglLahirDriver);
        editor.putString(KEY_JENISKELAMIN, jenisKelaminDriver);
        editor.putString(KEY_NOTELEPON, noTeleponDriver);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_FOTODRIVER, fotoDriver);
        editor.putFloat(KEY_TARIFDRIVER, tarifDriverHarian);
        editor.putInt(KEY_STATUSKETERSEDIAAN, statusKetersediaan);
        editor.putInt(KEY_KEMAMPUANBHSASING, kemampuanBahasaAsing);
        editor.putString(ACCESS_TOKEN, accessToken);
        editor.putBoolean(IS_LOGIN, true);

        editor.commit();
    }
    public Driver getDriverLogin(){
        String idDriver, namaDriver, alamatDriver,
                tglLahirDriver, jenisKelaminDriver,noTeleponDriver,
                email, password, fotoDriver, accessToken;
        float tarifDriverHarian;
        int statusKetersediaan, kemampuanBahasaAsing;

        idDriver = sharedPreferences.getString(KEY_ID, null);
        namaDriver = sharedPreferences.getString(KEY_NAMA, null);
        alamatDriver = sharedPreferences.getString(KEY_ALAMAT, null);
        tglLahirDriver = sharedPreferences.getString(KEY_TGLLAHIR, null);
        jenisKelaminDriver = sharedPreferences.getString(KEY_JENISKELAMIN, null);
        noTeleponDriver = sharedPreferences.getString(KEY_NOTELEPON, null);
        email = sharedPreferences.getString(KEY_EMAIL, null);
        password = sharedPreferences.getString(KEY_PASSWORD, null);
        fotoDriver = sharedPreferences.getString(KEY_FOTODRIVER, null);
        tarifDriverHarian = sharedPreferences.getFloat(KEY_TARIFDRIVER, 0);
        statusKetersediaan = sharedPreferences.getInt(KEY_STATUSKETERSEDIAAN, 0);
        kemampuanBahasaAsing = sharedPreferences.getInt(KEY_KEMAMPUANBHSASING, 0);
        accessToken = sharedPreferences.getString(ACCESS_TOKEN, null);

        return new Driver(idDriver, namaDriver, alamatDriver, tglLahirDriver,
                jenisKelaminDriver, noTeleponDriver, email, password, fotoDriver,
                tarifDriverHarian, statusKetersediaan, kemampuanBahasaAsing, accessToken);
    }
    public boolean checkLogin(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }
}
