package com.p3l.ajr_190710057.models;

import com.google.gson.annotations.SerializedName;

public class Mobil {
    @SerializedName("nama_mobil")
    private String namaMobil;
    @SerializedName("foto_mobil")
    private String fotoMobil;
    @SerializedName("tipe_mobil")
    private String tipeMobil;
    @SerializedName("jenis_transmisi")
    private String jenisTransmisi;
    @SerializedName("jenis_bahan_bakar")
    private String jenisBahanBakar;
    @SerializedName("volume_bahan_bakar")
    private Integer volumeBahanBakar;
    @SerializedName("warna_mobil")
    private String warnaMobil;
    @SerializedName("kapasitas_penumpang")
    private Integer kapasitasPenumpang;
    @SerializedName("fasilitas_mobil")
    private String fasilitasMobil;
    @SerializedName("tarif_mobil_harian")
    private float tarifMobilHarian;

    public Mobil(String namaMobil, String fotoMobil, String tipeMobil, String jenisTransmisi, String jenisBahanBakar, Integer volumeBahanBakar, String warnaMobil, Integer kapasitasPenumpang, String fasilitasMobil, float tarifMobilHarian) {
        this.namaMobil = namaMobil;
        this.fotoMobil = fotoMobil;
        this.tipeMobil = tipeMobil;
        this.jenisTransmisi = jenisTransmisi;
        this.jenisBahanBakar = jenisBahanBakar;
        this.volumeBahanBakar = volumeBahanBakar;
        this.warnaMobil = warnaMobil;
        this.kapasitasPenumpang = kapasitasPenumpang;
        this.fasilitasMobil = fasilitasMobil;
        this.tarifMobilHarian = tarifMobilHarian;
    }

    public String getNamaMobil() {
        return namaMobil;
    }

    public void setNamaMobil(String namaMobil) {
        this.namaMobil = namaMobil;
    }

    public String getFotoMobil() {
        return fotoMobil;
    }

    public void setFotoMobil(String fotoMobil) {
        this.fotoMobil = fotoMobil;
    }

    public String getTipeMobil() {
        return tipeMobil;
    }

    public void setTipeMobil(String tipeMobil) {
        this.tipeMobil = tipeMobil;
    }

    public String getJenisTransmisi() {
        return jenisTransmisi;
    }

    public void setJenisTransmisi(String jenisTransmisi) {
        this.jenisTransmisi = jenisTransmisi;
    }

    public String getJenisBahanBakar() {
        return jenisBahanBakar;
    }

    public void setJenisBahanBakar(String jenisBahanBakar) {
        this.jenisBahanBakar = jenisBahanBakar;
    }

    public Integer getVolumeBahanBakar() {
        return volumeBahanBakar;
    }

    public void setVolumeBahanBakar(Integer volumeBahanBakar) {
        this.volumeBahanBakar = volumeBahanBakar;
    }

    public String getWarnaMobil() {
        return warnaMobil;
    }

    public void setWarnaMobil(String warnaMobil) {
        this.warnaMobil = warnaMobil;
    }

    public Integer getKapasitasPenumpang() {
        return kapasitasPenumpang;
    }

    public void setKapasitasPenumpang(Integer kapasitasPenumpang) {
        this.kapasitasPenumpang = kapasitasPenumpang;
    }

    public String getFasilitasMobil() {
        return fasilitasMobil;
    }

    public void setFasilitasMobil(String fasilitasMobil) {
        this.fasilitasMobil = fasilitasMobil;
    }

    public float getTarifMobilHarian() {
        return tarifMobilHarian;
    }

    public void setTarifMobilHarian(float tarifMobilHarian) {
        this.tarifMobilHarian = tarifMobilHarian;
    }
}
