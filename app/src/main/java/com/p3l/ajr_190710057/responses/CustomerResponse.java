package com.p3l.ajr_190710057.responses;

import com.google.gson.annotations.SerializedName;
import com.p3l.ajr_190710057.models.Customer;

public class CustomerResponse {
    private String message;

    @SerializedName("user")
    private Customer customer;
    @SerializedName("access_token")
    private String accessToken;

    public CustomerResponse(String message, Customer customer, String accessToken) {
        this.message = message;
        this.customer = customer;
        this.accessToken = accessToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
