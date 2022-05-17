package com.p3l.ajr_190710057.models;

import com.google.gson.annotations.SerializedName;

public class Customer {
    @SerializedName("id_customer")
    private String idCustomer;
    @SerializedName("nama_customer")
    private String namaCustomer;
    @SerializedName("alamat_customer")
    private String alamatCustomer;
    @SerializedName("tgl_lahir_customer")
    private String tglLahirCustomer;
    @SerializedName("jenis_kelamin_customer")
    private String jenisKelaminCustomer;
    @SerializedName("no_telepon_customer")
    private String noTeleponCustomer;
    @SerializedName("no_kartu_identitas_customer")
    private String noKartuIdentitasCustomer;
    @SerializedName("kartu_identitas_customer")
    private String kartuIdentitasCustomer;
    @SerializedName("no_sim_customer")
    private String noSimCustomer;
    @SerializedName("sim_customer")
    private String simCustomer;
    private String email;
    private String password;
    @SerializedName("tipe_sewa_customer")
    private Integer tipeSewaCustomer;
    private String access_token;

    public Customer(String idCustomer, String namaCustomer, String alamatCustomer, String tglLahirCustomer,
                    String jenisKelaminCustomer, String noTeleponCustomer, String noKartuIdentitasCustomer,
                    String kartuIdentitasCustomer, String noSimCustomer, String simCustomer,
                    Integer tipeSewaCustomer, String email, String password, String access_token) {
        this.idCustomer = idCustomer;
        this.namaCustomer = namaCustomer;
        this.alamatCustomer = alamatCustomer;
        this.tglLahirCustomer = tglLahirCustomer;
        this.jenisKelaminCustomer = jenisKelaminCustomer;
        this.noTeleponCustomer = noTeleponCustomer;
        this.noKartuIdentitasCustomer = noKartuIdentitasCustomer;
        this.kartuIdentitasCustomer = kartuIdentitasCustomer;
        this.noSimCustomer = noSimCustomer;
        this.simCustomer = simCustomer;
        this.email = email;
        this.password = password;
        this.tipeSewaCustomer = tipeSewaCustomer;
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNamaCustomer() {
        return namaCustomer;
    }

    public void setNamaCustomer(String namaCustomer) {
        this.namaCustomer = namaCustomer;
    }

    public String getAlamatCustomer() {
        return alamatCustomer;
    }

    public void setAlamatCustomer(String alamatCustomer) {
        this.alamatCustomer = alamatCustomer;
    }

    public String getTglLahirCustomer() {
        return tglLahirCustomer;
    }

    public void setTglLahirCustomer(String tglLahirCustomer) {
        this.tglLahirCustomer = tglLahirCustomer;
    }

    public String getJenisKelaminCustomer() {
        return jenisKelaminCustomer;
    }

    public void setJenisKelaminCustomer(String jenisKelaminCustomer) {
        this.jenisKelaminCustomer = jenisKelaminCustomer;
    }

    public String getNoTeleponCustomer() {
        return noTeleponCustomer;
    }

    public void setNoTeleponCustomer(String noTeleponCustomer) {
        this.noTeleponCustomer = noTeleponCustomer;
    }

    public String getNoKartuIdentitasCustomer() {
        return noKartuIdentitasCustomer;
    }

    public void setNoKartuIdentitasCustomer(String noKartuIdentitasCustomer) {
        this.noKartuIdentitasCustomer = noKartuIdentitasCustomer;
    }

    public String getKartuIdentitasCustomer() {
        return kartuIdentitasCustomer;
    }

    public void setKartuIdentitasCustomer(String kartuIdentitasCustomer) {
        this.kartuIdentitasCustomer = kartuIdentitasCustomer;
    }

    public String getNoSimCustomer() {
        return noSimCustomer;
    }

    public void setNoSimCustomer(String noSimCustomer) {
        this.noSimCustomer = noSimCustomer;
    }

    public String getSimCustomer() {
        return simCustomer;
    }

    public void setSimCustomer(String simCustomer) {
        this.simCustomer = simCustomer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTipeSewaCustomer() {
        return tipeSewaCustomer;
    }

    public void setTipeSewaCustomer(Integer tipeSewaCustomer) {
        this.tipeSewaCustomer = tipeSewaCustomer;
    }
}
