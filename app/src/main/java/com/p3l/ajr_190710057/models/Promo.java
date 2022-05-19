package com.p3l.ajr_190710057.models;

import com.google.gson.annotations.SerializedName;

public class Promo {
    @SerializedName("kode_promo")
    private String kodePromo;
    @SerializedName("jenis_promo")
    private String jenisPromo;
    @SerializedName("keterangan")
    private String keterangan;
    @SerializedName("potongan_promo")
    private float potonganPromo;

    public Promo(String kodePromo, String jenisPromo, String keterangan, float potonganPromo) {
        this.kodePromo = kodePromo;
        this.jenisPromo = jenisPromo;
        this.keterangan = keterangan;
        this.potonganPromo = potonganPromo;
    }

    public String getKodePromo() {
        return kodePromo;
    }

    public void setKodePromo(String kodePromo) {
        this.kodePromo = kodePromo;
    }

    public String getJenisPromo() {
        return jenisPromo;
    }

    public void setJenisPromo(String jenisPromo) {
        this.jenisPromo = jenisPromo;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public float getPotonganPromo() {
        return potonganPromo;
    }

    public void setPotonganPromo(float potonganPromo) {
        this.potonganPromo = potonganPromo;
    }
}
