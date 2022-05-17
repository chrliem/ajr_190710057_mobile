package com.p3l.ajr_190710057.responses;

import com.google.gson.annotations.SerializedName;
import com.p3l.ajr_190710057.models.Driver;

public class DriverResponse {
    private String message;
    @SerializedName("user")
    private Driver driver;
    @SerializedName("access_token")
    private String accessToken;

    public DriverResponse(String message, Driver driver, String accessToken) {
        this.message = message;
        this.driver = driver;
        this.accessToken = accessToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
