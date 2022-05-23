package com.p3l.ajr_190710057.responses;

import com.google.gson.annotations.SerializedName;
import com.p3l.ajr_190710057.models.AverageDriver;

public class AverageResponse {
    private String message;
    @SerializedName("data")
    private AverageDriver averageDriver;

    public AverageResponse(String message, AverageDriver averageDriver) {
        this.message = message;
        this.averageDriver = averageDriver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AverageDriver getAverageDriver() {
        return averageDriver;
    }

    public void setAverageDriver(AverageDriver averageDriver) {
        this.averageDriver = averageDriver;
    }
}
