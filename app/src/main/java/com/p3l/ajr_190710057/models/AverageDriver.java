package com.p3l.ajr_190710057.models;

import com.google.gson.annotations.SerializedName;

public class AverageDriver {
    @SerializedName("rerata_rating")
    private float rerataRating;

    public AverageDriver(float rerataRating) {
        this.rerataRating = rerataRating;
    }

    public float getRerataRating() {
        return rerataRating;
    }

    public void setRerataRating(float rerataRating) {
        this.rerataRating = rerataRating;
    }
}
