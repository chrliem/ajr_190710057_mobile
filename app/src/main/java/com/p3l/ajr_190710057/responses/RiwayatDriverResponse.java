package com.p3l.ajr_190710057.responses;

import com.google.gson.annotations.SerializedName;
import com.p3l.ajr_190710057.models.RiwayatDriver;

import java.util.List;

public class RiwayatDriverResponse {
    private String message;

    @SerializedName("data")
    private List<RiwayatDriver> riwayatDriverList;

    public RiwayatDriverResponse(String message, List<RiwayatDriver> riwayatDriverList) {
        this.message = message;
        this.riwayatDriverList = riwayatDriverList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RiwayatDriver> getRiwayatDriverList() {
        return riwayatDriverList;
    }

    public void setRiwayatDriverList(List<RiwayatDriver> riwayatDriverList) {
        this.riwayatDriverList = riwayatDriverList;
    }
}
