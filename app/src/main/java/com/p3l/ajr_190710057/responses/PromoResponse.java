package com.p3l.ajr_190710057.responses;

import com.google.gson.annotations.SerializedName;
import com.p3l.ajr_190710057.models.Promo;

import java.util.List;

public class PromoResponse {
    private String message;

    @SerializedName("data")
    private List<Promo> promoList;

    public PromoResponse(String message, List<Promo> promoList) {
        this.message = message;
        this.promoList = promoList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Promo> getPromoList() {
        return promoList;
    }

    public void setPromoList(List<Promo> promoList) {
        this.promoList = promoList;
    }
}
