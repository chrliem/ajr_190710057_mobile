package com.p3l.ajr_190710057.responses;

import com.google.gson.annotations.SerializedName;
import com.p3l.ajr_190710057.models.LaporanDetailPendapatanTransaksi;

import java.util.List;

public class LaporanDetailPendapatanTransaksiResponse {
    private String message;
    @SerializedName("data")
    private List<LaporanDetailPendapatanTransaksi> laporanDetailPendapatanTransaksi;

    public LaporanDetailPendapatanTransaksiResponse(String message, List<LaporanDetailPendapatanTransaksi> laporanDetailPendapatanTransaksi) {
        this.message = message;
        this.laporanDetailPendapatanTransaksi = laporanDetailPendapatanTransaksi;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LaporanDetailPendapatanTransaksi> getLaporanDetailPendapatanTransaksi() {
        return laporanDetailPendapatanTransaksi;
    }

    public void setLaporanDetailPendapatanTransaksi(List<LaporanDetailPendapatanTransaksi> laporanDetailPendapatanTransaksi) {
        this.laporanDetailPendapatanTransaksi = laporanDetailPendapatanTransaksi;
    }
}
