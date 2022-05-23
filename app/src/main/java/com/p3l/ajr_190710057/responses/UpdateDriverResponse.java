package com.p3l.ajr_190710057.responses;

import com.google.gson.annotations.SerializedName;
import com.p3l.ajr_190710057.models.Driver;

public class UpdateDriverResponse {

    private String message;
    @SerializedName("data")
    private Driver newDriver;
    @SerializedName("access_token")
    private String accessToken;

    public UpdateDriverResponse(String message, Driver newDriver, String accessToken) {
        this.message = message;
        this.newDriver = newDriver;
        this.accessToken = accessToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Driver getNewDriver() {
        return newDriver;
    }

    public void setNewDriver(Driver newDriver) {
        this.newDriver = newDriver;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
