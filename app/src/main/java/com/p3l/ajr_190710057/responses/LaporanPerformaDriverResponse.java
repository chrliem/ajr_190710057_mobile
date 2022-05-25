package com.p3l.ajr_190710057.responses;

import com.google.gson.annotations.SerializedName;
import com.p3l.ajr_190710057.models.LaporanPerformaDriver;

import java.util.List;

public class LaporanPerformaDriverResponse {
    private String message;
    @SerializedName("data")
    private List<LaporanPerformaDriver> laporanPerformaDriver;

    public LaporanPerformaDriverResponse(String message, List<LaporanPerformaDriver> laporanPerformaDriver) {
        this.message = message;
        this.laporanPerformaDriver = laporanPerformaDriver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LaporanPerformaDriver> getLaporanPerformaDriver() {
        return laporanPerformaDriver;
    }

    public void setLaporanPerformaDriver(List<LaporanPerformaDriver> laporanPerformaDriver) {
        this.laporanPerformaDriver = laporanPerformaDriver;
    }
}
