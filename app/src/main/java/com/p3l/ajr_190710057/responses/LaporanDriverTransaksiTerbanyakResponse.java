package com.p3l.ajr_190710057.responses;

import com.google.gson.annotations.SerializedName;
import com.p3l.ajr_190710057.models.LaporanDriverTransaksiTerbanyak;

import java.util.List;

public class LaporanDriverTransaksiTerbanyakResponse {
    private String message;
    @SerializedName("data")
    private List<LaporanDriverTransaksiTerbanyak> laporanDriverTransaksiTerbanyak;

    public LaporanDriverTransaksiTerbanyakResponse(String message, List<LaporanDriverTransaksiTerbanyak> laporanDriverTransaksiTerbanyak) {
        this.message = message;
        this.laporanDriverTransaksiTerbanyak = laporanDriverTransaksiTerbanyak;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LaporanDriverTransaksiTerbanyak> getLaporanDriverTransaksiTerbanyak() {
        return laporanDriverTransaksiTerbanyak;
    }

    public void setLaporanDriverTransaksiTerbanyak(List<LaporanDriverTransaksiTerbanyak> laporanDriverTransaksiTerbanyak) {
        this.laporanDriverTransaksiTerbanyak = laporanDriverTransaksiTerbanyak;
    }
}
