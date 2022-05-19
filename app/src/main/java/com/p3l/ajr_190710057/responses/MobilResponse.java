package com.p3l.ajr_190710057.responses;

import com.google.gson.annotations.SerializedName;
import com.p3l.ajr_190710057.models.Mobil;

import java.util.List;

public class MobilResponse {
    private String message;

    @SerializedName("data")
    private List<Mobil> mobilList;

    public MobilResponse(String message, List<Mobil> mobilList) {
        this.message = message;
        this.mobilList = mobilList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Mobil> getMobilList() {
        return mobilList;
    }

    public void setMobilList(List<Mobil> mobilList) {
        this.mobilList = mobilList;
    }
}
