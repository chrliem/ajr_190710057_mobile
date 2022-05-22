package com.p3l.ajr_190710057.models;

import com.google.gson.annotations.SerializedName;

public class RiwayatCustomer {
    @SerializedName("no_transaksi")
    private String noTransaksi;
    @SerializedName("tgl_transaksi")
    private String tglTransaksi;
    @SerializedName("tgl_mulai_sewa")
    private String tglMulaiSewa;
    @SerializedName("tgl_selesai_sewa")
    private String tglSelesaiSewa;
    @SerializedName("tgl_pengembalian")
    private String tglPengembalian;
    @SerializedName("tipe_sewa_customer")
    private Integer tipeSewa;
    @SerializedName("nama_mobil")
    private String namaMobil;
    @SerializedName("nama_driver")
    private String namaDriver;
    @SerializedName("tarif_mobil_harian")
    private float tarifMobilHarian;
    @SerializedName("tarif_driver_harian")
    private float tarifDriverHarian;
    @SerializedName("durasi_penyewaan")
    private Integer durasiSewa;
    @SerializedName("foto_mobil")
    private String fotoMobil;
    @SerializedName("foto_driver")
    private String fotoDriver;
    @SerializedName("nama_pegawai")
    private String namaPegawai;
    @SerializedName("total_biaya_mobil")
    private float totalBiayaMobil;
    @SerializedName("total_biaya_driver")
    private float totalBiayaDriver;
    @SerializedName("total_biaya_ekstensi")
    private float totalBiayaEkstensi;
    @SerializedName("grand_total_pembayaran")
    private float totalPembayaran;
    @SerializedName("status_pembayaran")
    private String statusPembayaran;
    @SerializedName("status_transaksi")
    private String statusTransaksi;
    @SerializedName("rating_ajr")
    private Integer ratingAJR;
    @SerializedName("rating_driver")
    private Integer ratingDriver;
    @SerializedName("potongan_promo")
    private float potonganPromo;
    @SerializedName("kode_promo")
    private String kodePromo;
    @SerializedName("metode_pembayaran")
    private String metodePembayaran;
    @SerializedName("id_driver")
    private String idDriver;
    @SerializedName("no_plat")
    private String noPlat;
    @SerializedName("bukti_pembayaran")
    private String buktiPembayaran;


    public RiwayatCustomer(String noTransaksi, String tglTransaksi, String tglMulaiSewa, String tglSelesaiSewa, String tglPengembalian, Integer tipeSewa, String namaMobil, String namaDriver, float tarifMobilHarian, float tarifDriverHarian, Integer durasiSewa, String fotoMobil, String fotoDriver, String namaPegawai, float totalBiayaMobil, float totalBiayaDriver, float totalBiayaEkstensi, float totalPembayaran, String statusPembayaran, String statusTransaksi, Integer ratingAJR, Integer ratingDriver, float potonganPromo, String kodePromo, String metodePembayaran, String idDriver, String noPlat, String buktiPembayaran) {
        this.noTransaksi = noTransaksi;
        this.tglTransaksi = tglTransaksi;
        this.tglMulaiSewa = tglMulaiSewa;
        this.tglSelesaiSewa = tglSelesaiSewa;
        this.tglPengembalian = tglPengembalian;
        this.tipeSewa = tipeSewa;
        this.namaMobil = namaMobil;
        this.namaDriver = namaDriver;
        this.tarifMobilHarian = tarifMobilHarian;
        this.tarifDriverHarian = tarifDriverHarian;
        this.durasiSewa = durasiSewa;
        this.fotoMobil = fotoMobil;
        this.fotoDriver = fotoDriver;
        this.namaPegawai = namaPegawai;
        this.totalBiayaMobil = totalBiayaMobil;
        this.totalBiayaDriver = totalBiayaDriver;
        this.totalBiayaEkstensi = totalBiayaEkstensi;
        this.totalPembayaran = totalPembayaran;
        this.statusPembayaran = statusPembayaran;
        this.statusTransaksi = statusTransaksi;
        this.ratingAJR = ratingAJR;
        this.ratingDriver = ratingDriver;
        this.potonganPromo = potonganPromo;
        this.kodePromo = kodePromo;
        this.metodePembayaran = metodePembayaran;
        this.idDriver = idDriver;
        this.noPlat = noPlat;
        this.buktiPembayaran = buktiPembayaran;
    }

    public String getBuktiPembayaran() {
        return buktiPembayaran;
    }

    public void setBuktiPembayaran(String buktiPembayaran) {
        this.buktiPembayaran = buktiPembayaran;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
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

    public String getTglPengembalian() {
        return tglPengembalian;
    }

    public void setTglPengembalian(String tglPengembalian) {
        this.tglPengembalian = tglPengembalian;
    }

    public Integer getTipeSewa() {
        return tipeSewa;
    }

    public void setTipeSewa(Integer tipeSewa) {
        this.tipeSewa = tipeSewa;
    }

    public String getNamaMobil() {
        return namaMobil;
    }

    public void setNamaMobil(String namaMobil) {
        this.namaMobil = namaMobil;
    }

    public String getNamaDriver() {
        return namaDriver;
    }

    public void setNamaDriver(String namaDriver) {
        this.namaDriver = namaDriver;
    }

    public float getTarifMobilHarian() {
        return tarifMobilHarian;
    }

    public void setTarifMobilHarian(float tarifMobilHarian) {
        this.tarifMobilHarian = tarifMobilHarian;
    }

    public float getTarifDriverHarian() {
        return tarifDriverHarian;
    }

    public void setTarifDriverHarian(float tarifDriverHarian) {
        this.tarifDriverHarian = tarifDriverHarian;
    }

    public Integer getDurasiSewa() {
        return durasiSewa;
    }

    public void setDurasiSewa(Integer durasiSewa) {
        this.durasiSewa = durasiSewa;
    }

    public String getFotoMobil() {
        return fotoMobil;
    }

    public void setFotoMobil(String fotoMobil) {
        this.fotoMobil = fotoMobil;
    }

    public String getFotoDriver() {
        return fotoDriver;
    }

    public void setFotoDriver(String fotoDriver) {
        this.fotoDriver = fotoDriver;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public float getTotalBiayaMobil() {
        return totalBiayaMobil;
    }

    public void setTotalBiayaMobil(float totalBiayaMobil) {
        this.totalBiayaMobil = totalBiayaMobil;
    }

    public float getTotalBiayaDriver() {
        return totalBiayaDriver;
    }

    public void setTotalBiayaDriver(float totalBiayaDriver) {
        this.totalBiayaDriver = totalBiayaDriver;
    }

    public float getTotalBiayaEkstensi() {
        return totalBiayaEkstensi;
    }

    public void setTotalBiayaEkstensi(float totalBiayaEkstensi) {
        this.totalBiayaEkstensi = totalBiayaEkstensi;
    }

    public float getTotalPembayaran() {
        return totalPembayaran;
    }

    public void setTotalPembayaran(float totalPembayaran) {
        this.totalPembayaran = totalPembayaran;
    }

    public String getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }

    public String getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }

    public Integer getRatingAJR() {
        return ratingAJR;
    }

    public void setRatingAJR(Integer ratingAJR) {
        this.ratingAJR = ratingAJR;
    }

    public Integer getRatingDriver() {
        return ratingDriver;
    }

    public void setRatingDriver(Integer ratingDriver) {
        this.ratingDriver = ratingDriver;
    }

    public float getPotonganPromo() {
        return potonganPromo;
    }

    public void setPotonganPromo(float potonganPromo) {
        this.potonganPromo = potonganPromo;
    }

    public String getKodePromo() {
        return kodePromo;
    }

    public void setKodePromo(String kodePromo) {
        this.kodePromo = kodePromo;
    }

    public String getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(String idDriver) {
        this.idDriver = idDriver;
    }

    public String getNoPlat() {
        return noPlat;
    }

    public void setNoPlat(String noPlat) {
        this.noPlat = noPlat;
    }
}
