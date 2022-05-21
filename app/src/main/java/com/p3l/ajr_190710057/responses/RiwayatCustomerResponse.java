package com.p3l.ajr_190710057.responses;

import com.google.gson.annotations.SerializedName;
import com.p3l.ajr_190710057.models.RiwayatCustomer;

import java.util.List;

public class RiwayatCustomerResponse {
    private String message;

    @SerializedName("data")
    private List<RiwayatCustomer> riwayatCustomerList;

    public RiwayatCustomerResponse(String message, List<RiwayatCustomer> riwayatCustomerList) {
        this.message = message;
        this.riwayatCustomerList = riwayatCustomerList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RiwayatCustomer> getRiwayatCustomerList() {
        return riwayatCustomerList;
    }

    public void setRiwayatCustomerList(List<RiwayatCustomer> riwayatCustomerList) {
        this.riwayatCustomerList = riwayatCustomerList;
    }
}
