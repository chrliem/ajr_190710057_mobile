package com.p3l.ajr_190710057.models;

import com.google.gson.annotations.SerializedName;

public class RiwayatDriver {
    @SerializedName("no_transaksi")
    private String noTransaksi;
    @SerializedName("kode_promo")
    private String kodePromo;
    @SerializedName("potongan_promo")
    private float potonganPromo;
    @SerializedName("nama_mobil")
    private String namaMobil;
    @SerializedName("foto_mobil")
    private String fotoMobil;
    @SerializedName("no_plat")
    private String noPlat;
    @SerializedName("tarif_mobil_harian")
    private float tarifMobilHarian;
    @SerializedName("foto_driver")
    private String fotoDriver;
    @SerializedName("nama_driver")
    private String namaDriver;
    @SerializedName("tarif_driver_harian")
    private float tarifDriverHarian;
    @SerializedName("nama_pegawai")
    private String namaPegawai;
    @SerializedName("tgl_transaksi")
    private String tglTransaksi;
    @SerializedName("tgl_mulai_sewa")
    private String tglMulaiSewa;
    @SerializedName("tgl_selesai_sewa")
    private String tglSelesaiSewa;
    @SerializedName("durasi_penyewaan")
    private String durasiPenyewaan;
    @SerializedName("tgl_pengembalian")
    private String tglPengembalian;
    @SerializedName("total_biaya_ekstensi")
    private float totalBiayaEkstensi;
    @SerializedName("total_biaya_driver")
    private float totalBiayaDriver;
    @SerializedName("total_biaya_mobil")
    private float totalBiayaMobil;
    @SerializedName("status_transaksi")
    private String statusTransaksi;
    @SerializedName("grand_total_pembayaran")
    private float grandTotal;
    @SerializedName("rating_driver")
    private int ratingDriver;
    @SerializedName("id_driver")
    private String idDriver;

    public RiwayatDriver(String noTransaksi, String kodePromo, float potonganPromo, String namaMobil, String fotoMobil, String noPlat, float tarifMobilHarian, String fotoDriver, String namaDriver, float tarifDriverHarian, String namaPegawai, String tglTransaksi, String tglMulaiSewa, String tglSelesaiSewa, String durasiPenyewaan, String tglPengembalian, float totalBiayaEkstensi, float totalBiayaDriver, float totalBiayaMobil, String statusTransaksi, float grandTotal, int ratingDriver, String idDriver) {
        this.noTransaksi = noTransaksi;
        this.kodePromo = kodePromo;
        this.potonganPromo = potonganPromo;
        this.namaMobil = namaMobil;
        this.fotoMobil = fotoMobil;
        this.noPlat = noPlat;
        this.tarifMobilHarian = tarifMobilHarian;
        this.fotoDriver = fotoDriver;
        this.namaDriver = namaDriver;
        this.tarifDriverHarian = tarifDriverHarian;
        this.namaPegawai = namaPegawai;
        this.tglTransaksi = tglTransaksi;
        this.tglMulaiSewa = tglMulaiSewa;
        this.tglSelesaiSewa = tglSelesaiSewa;
        this.durasiPenyewaan = durasiPenyewaan;
        this.tglPengembalian = tglPengembalian;
        this.totalBiayaEkstensi = totalBiayaEkstensi;
        this.totalBiayaDriver = totalBiayaDriver;
        this.totalBiayaMobil = totalBiayaMobil;
        this.statusTransaksi = statusTransaksi;
        this.grandTotal = grandTotal;
        this.ratingDriver = ratingDriver;
        this.idDriver = idDriver;
    }


    public String getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(String idDriver) {
        this.idDriver = idDriver;
    }

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public String getKodePromo() {
        return kodePromo;
    }

    public void setKodePromo(String kodePromo) {
        this.kodePromo = kodePromo;
    }

    public float getPotonganPromo() {
        return potonganPromo;
    }

    public void setPotonganPromo(float potonganPromo) {
        this.potonganPromo = potonganPromo;
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

    public String getNoPlat() {
        return noPlat;
    }

    public void setNoPlat(String noPlat) {
        this.noPlat = noPlat;
    }

    public float getTarifMobilHarian() {
        return tarifMobilHarian;
    }

    public void setTarifMobilHarian(float tarifMobilHarian) {
        this.tarifMobilHarian = tarifMobilHarian;
    }

    public String getFotoDriver() {
        return fotoDriver;
    }

    public void setFotoDriver(String fotoDriver) {
        this.fotoDriver = fotoDriver;
    }

    public String getNamaDriver() {
        return namaDriver;
    }

    public void setNamaDriver(String namaDriver) {
        this.namaDriver = namaDriver;
    }

    public float getTarifDriverHarian() {
        return tarifDriverHarian;
    }

    public void setTarifDriverHarian(float tarifDriverHarian) {
        this.tarifDriverHarian = tarifDriverHarian;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(String tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }

    public String getTglMulaiSewa() {
        return tglMulaiSewa;
    }

    public void setTglMulaiSewa(String tglMulaiSewa) {
        this.tglMulaiSewa = tglMulaiSewa;
    }

    public String getTglSelesaiSewa() {
        return tglSelesaiSewa;
    }

    public void setTglSelesaiSewa(String tglSelesaiSewa) {
        this.tglSelesaiSewa = tglSelesaiSewa;
    }

    public String getDurasiPenyewaan() {
        return durasiPenyewaan;
    }

    public void setDurasiPenyewaan(String durasiPenyewaan) {
        this.durasiPenyewaan = durasiPenyewaan;
    }

    public String getTglPengembalian() {
        return tglPengembalian;
    }

    public void setTglPengembalian(String tglPengembalian) {
        this.tglPengembalian = tglPengembalian;
    }

    public float getTotalBiayaEkstensi() {
        return totalBiayaEkstensi;
    }

    public void setTotalBiayaEkstensi(float totalBiayaEkstensi) {
        this.totalBiayaEkstensi = totalBiayaEkstensi;
    }

    public float getTotalBiayaDriver() {
        return totalBiayaDriver;
    }

    public void setTotalBiayaDriver(float totalBiayaDriver) {
        this.totalBiayaDriver = totalBiayaDriver;
    }

    public float getTotalBiayaMobil() {
        return totalBiayaMobil;
    }

    public void setTotalBiayaMobil(float totalBiayaMobil) {
        this.totalBiayaMobil = totalBiayaMobil;
    }

    public String getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }

    public float getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(float grandTotal) {
        this.grandTotal = grandTotal;
    }

    public int getRatingDriver() {
        return ratingDriver;
    }

    public void setRatingDriver(int ratingDriver) {
        this.ratingDriver = ratingDriver;
    }
}
