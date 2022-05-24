package com.p3l.ajr_190710057.responses;

import com.google.gson.annotations.SerializedName;
import com.p3l.ajr_190710057.models.LaporanPenyewaanMobil;

import java.util.List;

public class LaporanPenyewaanMobilResponse {
    private String message;
    @SerializedName("data")
    private List<LaporanPenyewaanMobil> laporanPenyewaanMobilList;

    public LaporanPenyewaanMobilResponse(String message, List<LaporanPenyewaanMobil> laporanPenyewaanMobilList) {
        this.message = message;
        this.laporanPenyewaanMobilList = laporanPenyewaanMobilList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LaporanPenyewaanMobil> getLaporanPenyewaanMobilList() {
        return laporanPenyewaanMobilList;
    }

    public void setLaporanPenyewaanMobilList(List<LaporanPenyewaanMobil> laporanPenyewaanMobilList) {
        this.laporanPenyewaanMobilList = laporanPenyewaanMobilList;
    }
}
