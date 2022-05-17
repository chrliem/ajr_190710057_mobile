package com.p3l.ajr_190710057.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.p3l.ajr_190710057.models.Customer;

public class CustomerPreferences {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_ID = "id_customer";
    public static final String KEY_NAMA = "nama_customer";
    public static final String KEY_ALAMAT = "alamat_customer";
    public static final String KEY_TGLLAHIR = "tgl_lahir_customer";
    public static final String KEY_JENISKELAMIN = "jenis_kelamin_customer";
    public static final String KEY_NOTELP = "no_telepon_customer";
    public static final String KEY_NOKARTUIDENTITAS = "no_kartu_identitas_customer";
    public static final String KEY_KARTUIDENTITAS = "kartu_identitas_customer";
    public static final String KEY_NOSIM = "no_sim_customer";
    public static final String KEY_SIM = "sim_customer";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_TIPE = "tipe_sewa_customer";
    public static final String ACCESS_TOKEN = "access_token";

    public CustomerPreferences(Context context){
        this.context = context;

        sharedPreferences = context.getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLogin(String idCustomer, String namaCustomer, String alamatCustomer, String tglLahirCustomer,
                         String jenisKelaminCustomer, String noTeleponCustomer, String noKartuIdentitasCustomer,
                         String kartuIdentitasCustomer, String noSimCustomer, String simCustomer,
                         String email, String password, int tipeSewaCustomer, String access_token){
        editor.putString(KEY_ID, idCustomer);
        editor.putString(KEY_NAMA, namaCustomer);
        editor.putString(KEY_ALAMAT, alamatCustomer);
        editor.putString(KEY_TGLLAHIR, tglLahirCustomer);
        editor.putString(KEY_NOTELP, noTeleponCustomer);
        editor.putString(KEY_NOKARTUIDENTITAS, noKartuIdentitasCustomer);
        editor.putString(KEY_JENISKELAMIN, jenisKelaminCustomer);
        editor.putString(KEY_KARTUIDENTITAS, kartuIdentitasCustomer);
        editor.putString(KEY_NOSIM, noSimCustomer);
        editor.putString(KEY_SIM, simCustomer);
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(ACCESS_TOKEN, access_token);
        editor.putInt(KEY_TIPE, tipeSewaCustomer);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);

        editor.commit();
    }

    public Customer getCustomerLogin(){
        String idCustomer, namaCustomer, alamatCustomer, tglLahirCustomer,
                jenisKelaminCustomer, noTeleponCustomer, noKartuIdentitasCustomer,
                kartuIdentitasCustomer, noSimCustomer, simCustomer, email, password, access_token;
                int tipeSewaCustomer;

        idCustomer = sharedPreferences.getString(KEY_ID, null);
        namaCustomer = sharedPreferences.getString(KEY_NAMA, null);
        alamatCustomer = sharedPreferences.getString(KEY_ALAMAT, null);
        tglLahirCustomer = sharedPreferences.getString(KEY_TGLLAHIR, null);
        jenisKelaminCustomer = sharedPreferences.getString(KEY_JENISKELAMIN, null);
        noTeleponCustomer = sharedPreferences.getString(KEY_NOTELP, null);
        noKartuIdentitasCustomer = sharedPreferences.getString(KEY_NOTELP, null);
        kartuIdentitasCustomer = sharedPreferences.getString(KEY_KARTUIDENTITAS, null);
        noSimCustomer = sharedPreferences.getString(KEY_NOSIM, null);
        simCustomer = sharedPreferences.getString(KEY_SIM, null);
        tipeSewaCustomer = sharedPreferences.getInt(KEY_TIPE, 0);
        access_token = sharedPreferences.getString(ACCESS_TOKEN, null);
        email = sharedPreferences.getString(KEY_EMAIL, null);
        password = sharedPreferences.getString(KEY_PASSWORD, null);

        return new Customer(idCustomer, namaCustomer, alamatCustomer, tglLahirCustomer, jenisKelaminCustomer,
                noTeleponCustomer, noKartuIdentitasCustomer, kartuIdentitasCustomer,
                noSimCustomer, simCustomer, tipeSewaCustomer, email, password, access_token);

    }
    public boolean checkLogin(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }

}
