package com.p3l.ajr_190710057.models;

import com.google.gson.annotations.SerializedName;

public class LaporanCustomerTransaksiTerbanyak {
    @SerializedName("nama_customer")
    private String namaCustomer;
    @SerializedName("jumlah_transaksi")
    private String jumlahTransaksi;

    public LaporanCustomerTransaksiTerbanyak(String namaCustomer, String jumlahTransaksi) {
        this.namaCustomer = namaCustomer;
        this.jumlahTransaksi = jumlahTransaksi;
    }

    public String getNamaCustomer() {
        return namaCustomer;
    }

    public void setNamaCustomer(String namaCustomer) {
        this.namaCustomer = namaCustomer;
    }

    public String getJumlahTransaksi() {
        return jumlahTransaksi;
    }

    public void setJumlahTransaksi(String jumlahTransaksi) {
        this.jumlahTransaksi = jumlahTransaksi;
    }
}
