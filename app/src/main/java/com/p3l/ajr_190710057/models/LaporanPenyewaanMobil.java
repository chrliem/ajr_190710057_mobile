package com.p3l.ajr_190710057.models;

import com.google.gson.annotations.SerializedName;

public class LaporanPenyewaanMobil {
    @SerializedName("tipe_mobil")
    private String tipeMobil;
    @SerializedName("nama_mobil")
    private String namaMobil;
    @SerializedName("jumlah_peminjaman")
    private int jumlahPeminjaman;
    @SerializedName("total_pendapatan")
    private float totalPendapatan;

    public LaporanPenyewaanMobil(String tipeMobil, String namaMobil, int jumlahPeminjaman, float totalPendapatan) {
        this.tipeMobil = tipeMobil;
        this.namaMobil = namaMobil;
        this.jumlahPeminjaman = jumlahPeminjaman;
        this.totalPendapatan = totalPendapatan;
    }

    public String getTipeMobil() {
        return tipeMobil;
    }

    public void setTipeMobil(String tipeMobil) {
        this.tipeMobil = tipeMobil;
    }

    public String getNamaMobil() {
        return namaMobil;
    }

    public void setNamaMobil(String namaMobil) {
        this.namaMobil = namaMobil;
    }

    public int getJumlahPeminjaman() {
        return jumlahPeminjaman;
    }

    public void setJumlahPeminjaman(int jumlahPeminjaman) {
        this.jumlahPeminjaman = jumlahPeminjaman;
    }

    public float getTotalPendapatan() {
        return totalPendapatan;
    }

    public void setTotalPendapatan(float totalPendapatan) {
        this.totalPendapatan = totalPendapatan;
    }
}
