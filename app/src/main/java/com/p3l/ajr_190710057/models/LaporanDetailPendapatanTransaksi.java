package com.p3l.ajr_190710057.models;

import com.google.gson.annotations.SerializedName;

public class LaporanDetailPendapatanTransaksi {
    @SerializedName("nama_customer")
    private String namaCustomer;
    @SerializedName("nama_mobil")
    private String namaMobil;
    @SerializedName("jenis_transaksi")
    private String jenisTransaksi;
    @SerializedName("jumlah_transaksi")
    private int jumlahTransaksi;
    @SerializedName("pendapatan")
    private float pendapatan;

    public LaporanDetailPendapatanTransaksi(String namaCustomer, String namaMobil, String jenisTransaksi, int jumlahTransaksi, float pendapatan) {
        this.namaCustomer = namaCustomer;
        this.namaMobil = namaMobil;
        this.jenisTransaksi = jenisTransaksi;
        this.jumlahTransaksi = jumlahTransaksi;
        this.pendapatan = pendapatan;
    }

    public String getNamaCustomer() {
        return namaCustomer;
    }

    public void setNamaCustomer(String namaCustomer) {
        this.namaCustomer = namaCustomer;
    }

    public String getNamaMobil() {
        return namaMobil;
    }

    public void setNamaMobil(String namaMobil) {
        this.namaMobil = namaMobil;
    }

    public String getJenisTransaksi() {
        return jenisTransaksi;
    }

    public void setJenisTransaksi(String jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }

    public int getJumlahTransaksi() {
        return jumlahTransaksi;
    }

    public void setJumlahTransaksi(int jumlahTransaksi) {
        this.jumlahTransaksi = jumlahTransaksi;
    }

    public float getPendapatan() {
        return pendapatan;
    }

    public void setPendapatan(float pendapatan) {
        this.pendapatan = pendapatan;
    }
}
