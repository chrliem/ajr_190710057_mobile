package com.p3l.ajr_190710057.models;

import com.google.gson.annotations.SerializedName;

public class Driver {
    @SerializedName("id_driver")
    private String idDriver;
    @SerializedName("nama_driver")
    private String namaDriver;
    @SerializedName("alamat_driver")
    private String alamatDriver;
    @SerializedName("tgl_lahir_driver")
    private String tglLahirDriver;
    @SerializedName("jenis_kelamin_driver")
    private String jenisKelaminDriver;
    @SerializedName("no_telepon_driver")
    private String noTeleponDriver;
    private String email;
    private String password;
    @SerializedName("foto_driver")
    private String fotoDriver;
    @SerializedName("tarif_driver_harian")
    private float tarifDriverHarian;
    @SerializedName("status_ketersediaan_driver")
    private Integer statusKetersediaan;
    @SerializedName("kemampuan_bahasa_asing")
    private Integer kemampuanBahasaAsing;
    @SerializedName("access_token")
    private String accessToken;

    public Driver(String idDriver, String namaDriver, String alamatDriver,
                  String tglLahirDriver, String jenisKelaminDriver, String noTeleponDriver,
                  String email, String password, String fotoDriver,
                  float tarifDriverHarian, Integer statusKetersediaan, Integer kemampuanBahasaAsing,
                  String accessToken) {
        this.idDriver = idDriver;
        this.namaDriver = namaDriver;
        this.alamatDriver = alamatDriver;
        this.tglLahirDriver = tglLahirDriver;
        this.jenisKelaminDriver = jenisKelaminDriver;
        this.noTeleponDriver = noTeleponDriver;
        this.email = email;
        this.password = password;
        this.fotoDriver = fotoDriver;
        this.tarifDriverHarian = tarifDriverHarian;
        this.statusKetersediaan = statusKetersediaan;
        this.kemampuanBahasaAsing = kemampuanBahasaAsing;
        this.accessToken = accessToken;
    }

    public String getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(String idDriver) {
        this.idDriver = idDriver;
    }

    public String getNamaDriver() {
        return namaDriver;
    }

    public void setNamaDriver(String namaDriver) {
        this.namaDriver = namaDriver;
    }

    public String getAlamatDriver() {
        return alamatDriver;
    }

    public void setAlamatDriver(String alamatDriver) {
        this.alamatDriver = alamatDriver;
    }

    public String getTglLahirDriver() {
        return tglLahirDriver;
    }

    public void setTglLahirDriver(String tglLahirDriver) {
        this.tglLahirDriver = tglLahirDriver;
    }

    public String getJenisKelaminDriver() {
        return jenisKelaminDriver;
    }

    public void setJenisKelaminDriver(String jenisKelaminDriver) {
        this.jenisKelaminDriver = jenisKelaminDriver;
    }

    public String getNoTeleponDriver() {
        return noTeleponDriver;
    }

    public void setNoTeleponDriver(String noTeleponDriver) {
        this.noTeleponDriver = noTeleponDriver;
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

    public String getFotoDriver() {
        return fotoDriver;
    }

    public void setFotoDriver(String fotoDriver) {
        this.fotoDriver = fotoDriver;
    }

    public float getTarifDriverHarian() {
        return tarifDriverHarian;
    }

    public void setTarifDriverHarian(float tarifDriverHarian) {
        this.tarifDriverHarian = tarifDriverHarian;
    }

    public Integer getStatusKetersediaan() {
        return statusKetersediaan;
    }

    public void setStatusKetersediaan(Integer statusKetersediaan) {
        this.statusKetersediaan = statusKetersediaan;
    }

    public Integer getKemampuanBahasaAsing() {
        return kemampuanBahasaAsing;
    }

    public void setKemampuanBahasaAsing(Integer kemampuanBahasaAsing) {
        this.kemampuanBahasaAsing = kemampuanBahasaAsing;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
