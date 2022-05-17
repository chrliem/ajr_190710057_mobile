package com.p3l.ajr_190710057.models;

import com.google.gson.annotations.SerializedName;

public class Pegawai {
    @SerializedName("id_pegawai")
    private String idPegawai;
    @SerializedName("id_role")
    private Integer idRole;
    @SerializedName("nama_pegawai")
    private String namaPegawai;
    @SerializedName("tgl_lahir_pegawai")
    private String tglLahirPegawai;
    @SerializedName("jenis_kelamin_pegawai")
    private String jenisKelaminPegawai;
    @SerializedName("alamat_pegawai")
    private String alamatPegawai;
    @SerializedName("no_telepon_pegawai")
    private String noTeleponPegawai;
    @SerializedName("foto_pegawai")
    private String fotoPegawai;
    private String email;
    private String password;
    @SerializedName("access_token")
    private String accessToken;

    public Pegawai(String idPegawai, Integer idRole, String namaPegawai,
                   String tglLahirPegawai, String jenisKelaminPegawai, String alamatPegawai,
                   String noTeleponPegawai, String fotoPegawai, String email, String password, String accessToken) {
        this.idPegawai = idPegawai;
        this.idRole = idRole;
        this.namaPegawai = namaPegawai;
        this.tglLahirPegawai = tglLahirPegawai;
        this.jenisKelaminPegawai = jenisKelaminPegawai;
        this.alamatPegawai = alamatPegawai;
        this.noTeleponPegawai = noTeleponPegawai;
        this.fotoPegawai = fotoPegawai;
        this.email = email;
        this.password = password;
        this.accessToken = accessToken;
    }

    public String getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(String idPegawai) {
        this.idPegawai = idPegawai;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getTglLahirPegawai() {
        return tglLahirPegawai;
    }

    public void setTglLahirPegawai(String tglLahirPegawai) {
        this.tglLahirPegawai = tglLahirPegawai;
    }

    public String getJenisKelaminPegawai() {
        return jenisKelaminPegawai;
    }

    public void setJenisKelaminPegawai(String jenisKelaminPegawai) {
        this.jenisKelaminPegawai = jenisKelaminPegawai;
    }

    public String getAlamatPegawai() {
        return alamatPegawai;
    }

    public void setAlamatPegawai(String alamatPegawai) {
        this.alamatPegawai = alamatPegawai;
    }

    public String getNoTeleponPegawai() {
        return noTeleponPegawai;
    }

    public void setNoTeleponPegawai(String noTeleponPegawai) {
        this.noTeleponPegawai = noTeleponPegawai;
    }

    public String getFotoPegawai() {
        return fotoPegawai;
    }

    public void setFotoPegawai(String fotoPegawai) {
        this.fotoPegawai = fotoPegawai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
