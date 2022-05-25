package com.p3l.ajr_190710057.responses;

import com.google.gson.annotations.SerializedName;
import com.p3l.ajr_190710057.models.LaporanCustomerTransaksiTerbanyak;

import java.util.List;

public class LaporanCustomerTransaksiTerbanyakResponse {
    private String message;
    @SerializedName("data")
    private List<LaporanCustomerTransaksiTerbanyak> laporanCustomerTransaksiTerbanyak;

    public LaporanCustomerTransaksiTerbanyakResponse(String message, List<LaporanCustomerTransaksiTerbanyak> laporanCustomerTransaksiTerbanyak) {
        this.message = message;
        this.laporanCustomerTransaksiTerbanyak = laporanCustomerTransaksiTerbanyak;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LaporanCustomerTransaksiTerbanyak> getLaporanCustomerTransaksiTerbanyak() {
        return laporanCustomerTransaksiTerbanyak;
    }

    public void setLaporanCustomerTransaksiTerbanyak(List<LaporanCustomerTransaksiTerbanyak> laporanCustomerTransaksiTerbanyak) {
        this.laporanCustomerTransaksiTerbanyak = laporanCustomerTransaksiTerbanyak;
    }
}
