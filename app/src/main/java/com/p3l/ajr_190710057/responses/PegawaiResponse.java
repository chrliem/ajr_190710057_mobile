package com.p3l.ajr_190710057.responses;

import com.google.gson.annotations.SerializedName;
import com.p3l.ajr_190710057.models.Pegawai;

public class PegawaiResponse {

    private String message;
    @SerializedName("user")
    private Pegawai pegawai;
    @SerializedName("access_token")
    private String accessToken;

    public PegawaiResponse(String message, Pegawai pegawai, String accessToken) {
        this.message = message;
        this.pegawai = pegawai;
        this.accessToken = accessToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Pegawai getPegawai() {
        return pegawai;
    }

    public void setPegawai(Pegawai pegawai) {
        this.pegawai = pegawai;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
