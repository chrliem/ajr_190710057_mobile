package com.p3l.ajr_190710057.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.p3l.ajr_190710057.models.Pegawai;

public class PegawaiPreferences {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_ID = "id_pegawai";
    public static final String KEY_ROLE = "id_role";
    public static final String KEY_NAMA = "nama_pegawai";
    public static final String KEY_TGLLAHIR = "tgl_lahir_pegawai";
    public static final String KEY_JENISKELAMIN = "jenis_kelamin_pegawai";
    public static final String KEY_ALAMAT = "alamat_pegawai";
    public static final String KEY_NOTELP = "no_telepon_pegawai";
    public static final String KEY_FOTO = "foto_pegawai";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String ACCESS_TOKEN = "access_token";

    public PegawaiPreferences(Context context){
        this.context = context;

        sharedPreferences = context.getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLogin(String idPegawai, int idRole, String namaPegawai,
                         String tglLahirPegawai, String jenisKelaminPegawai, String alamatPegawai,
                         String noTeleponPegawai, String fotoPegawai, String email, String password, String access_token){

        editor.putString(KEY_ID, idPegawai);
        editor.putString(KEY_NAMA, namaPegawai);
        editor.putString(KEY_ALAMAT, alamatPegawai);
        editor.putInt(KEY_ROLE, idRole);
        editor.putString(KEY_TGLLAHIR, tglLahirPegawai);
        editor.putString(KEY_JENISKELAMIN, jenisKelaminPegawai);
        editor.putString(KEY_NOTELP, noTeleponPegawai);
        editor.putString(KEY_FOTO, fotoPegawai);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(ACCESS_TOKEN, access_token);
        editor.putBoolean(IS_LOGIN, true);

        editor.commit();
    }

    public Pegawai getPegawaiLogin(){
        String idPegawai, namaPegawai, tglLahirPegawai, jenisKelaminPegawai, alamatPegawai,
                noTeleponPegawai, fotoPegawai, email, password, access_token;
        int idRole;

        idPegawai = sharedPreferences.getString(KEY_ID, null);
        idRole = sharedPreferences.getInt(KEY_ROLE, 0);
        namaPegawai = sharedPreferences.getString(KEY_NAMA, null);
        tglLahirPegawai = sharedPreferences.getString(KEY_TGLLAHIR, null);
        jenisKelaminPegawai = sharedPreferences.getString(KEY_JENISKELAMIN,null);
        alamatPegawai = sharedPreferences.getString(KEY_ALAMAT, null);
        noTeleponPegawai = sharedPreferences.getString(KEY_NOTELP, null);
        fotoPegawai = sharedPreferences.getString(KEY_FOTO, null);
        email = sharedPreferences.getString(KEY_EMAIL, null);
        password = sharedPreferences.getString(KEY_PASSWORD, null);
        access_token = sharedPreferences.getString(ACCESS_TOKEN, null);

        return new Pegawai(idPegawai, idRole, namaPegawai, tglLahirPegawai,jenisKelaminPegawai,alamatPegawai,noTeleponPegawai,
                fotoPegawai,email,password, access_token);
    }

    public boolean checkLogin(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }
}
