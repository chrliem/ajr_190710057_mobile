package com.p3l.ajr_190710057.models;

import com.google.gson.annotations.SerializedName;

public class LaporanPerformaDriver {
    @SerializedName("id_driver")
    private String idDriver;
    @SerializedName("nama_driver")
    private String namaDriver;
    @SerializedName("jumlah_transaksi")
    private int jumlahTransaksi;
    @SerializedName("rerata_rating")
    private float rerataRating;

    public LaporanPerformaDriver(String idDriver, String namaDriver, int jumlahTransaksi, float rerataRating) {
        this.idDriver = idDriver;
        this.namaDriver = namaDriver;
        this.jumlahTransaksi = jumlahTransaksi;
        this.rerataRating = rerataRating;
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

    public int getJumlahTransaksi() {
        return jumlahTransaksi;
    }

    public void setJumlahTransaksi(int jumlahTransaksi) {
        this.jumlahTransaksi = jumlahTransaksi;
    }

    public float getRerataRating() {
        return rerataRating;
    }

    public void setRerataRating(float rerataRating) {
        this.rerataRating = rerataRating;
    }
}
