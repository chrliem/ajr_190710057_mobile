package com.p3l.ajr_190710057.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.p3l.ajr_190710057.R;
import com.p3l.ajr_190710057.models.RiwayatDriver;

import org.w3c.dom.Text;

import java.util.List;

public class RiwayatDriverAdapter extends RecyclerView.Adapter<RiwayatDriverAdapter.ViewHolder> {
    private List<RiwayatDriver> riwayatDriverList;
    private Context context;

    public RiwayatDriverAdapter(List<RiwayatDriver> riwayatDriverList, Context context) {
        this.riwayatDriverList = riwayatDriverList;
        this.context = context;
    }

    @NonNull
    @Override
    public RiwayatDriverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_riwayat_driver, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatDriverAdapter.ViewHolder holder, int position) {
        RiwayatDriver riwayatDriver = riwayatDriverList.get(position);

        holder.tvNoTransaksi.setText(riwayatDriver.getNoTransaksi());
        holder.tvTglTransaksi.setText(riwayatDriver.getTglTransaksi());
        holder.tvStatus.setText(riwayatDriver.getStatusTransaksi());
        holder.tvTglMulai.setText(riwayatDriver.getTglMulaiSewa());
        holder.tvTglSelesai.setText(riwayatDriver.getTglSelesaiSewa());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                View newLayout = LayoutInflater.from(builder.getContext())
                    .inflate(R.layout.detail_riwayat_driver, null);

                TextView tvNoTransaksi, tvNamaMobil, tvNoPlat, tvNamaDriver, tvIdDriver, tvTarifMobilHarian, tvTarifDriverHarian,
                        tvTglTransaksi, tvTglMulai, tvTglSelesai, tvDurasi, tvTglPengembalian, tvNamaPegawai, tvKodePromo,
                        tvTotalBiayaMobil, tvTotalBiayaDriver, tvBiayaEkstensi, tvPotonganPromo, tvGrandTotal, tvStatus;
                ImageView ivFotoMobil, ivFotoDriver;
                RatingBar rtRatingDriver;
                MaterialButton btnCloseDetail;

                tvNoTransaksi = newLayout.findViewById(R.id.tv_no_transaksi_detail_driver);
                tvNamaMobil = newLayout.findViewById(R.id.tv_nama_mobil_driver);
                tvNoPlat = newLayout.findViewById(R.id.tv_no_plat_mobil_driver);
                tvNamaDriver = newLayout.findViewById(R.id.tv_nama_driver_driver);
                tvIdDriver = newLayout.findViewById(R.id.tv_no_driver_driver);
                tvTarifMobilHarian = newLayout.findViewById(R.id.tv_tarif_mobil_harian_driver);
                tvTarifDriverHarian = newLayout.findViewById(R.id.tv_tarif_driver_harian_driver);
                tvTglTransaksi = newLayout.findViewById(R.id.tv_tgl_transaksi_detail_driver);
                tvTglMulai = newLayout.findViewById(R.id.tv_tgl_mulai_sewa_driver);
                tvTglSelesai = newLayout.findViewById(R.id.tv_tgl_selesai_sewa_driver);
                tvDurasi = newLayout.findViewById(R.id.tv_durasi_sewa_driver);
                tvTglPengembalian = newLayout.findViewById(R.id.tv_tgl_pengembalian_driver);
                tvNamaPegawai = newLayout.findViewById(R.id.tv_nama_pegawai_driver);
                tvKodePromo = newLayout.findViewById(R.id.tv_kode_promo_detail_driver);
                tvTotalBiayaDriver = newLayout.findViewById(R.id.tv_total_biaya_driver_driver);
                tvTotalBiayaMobil = newLayout.findViewById(R.id.tv_total_biaya_mobil_driver);
                tvBiayaEkstensi = newLayout.findViewById(R.id.tv_total_biaya_ekstensi_driver);
                tvPotonganPromo = newLayout.findViewById(R.id.tv_potongan_promo_detail_driver);
                tvGrandTotal = newLayout.findViewById(R.id.tv_grand_total_pembayaran_driver);
                tvStatus = newLayout.findViewById(R.id.tv_status_transaksi_driver);
                ivFotoMobil = newLayout.findViewById(R.id.iv_foto_mobil_detail_driver);
                ivFotoDriver = newLayout.findViewById(R.id.iv_foto_driver_detail_driver);
                rtRatingDriver = newLayout.findViewById(R.id.rt_rating_driver_driver);
                btnCloseDetail = newLayout.findViewById(R.id.btnCloseDetailDriver);

                tvNoTransaksi.setText(riwayatDriver.getNoTransaksi());

                tvNoPlat.setText(riwayatDriver.getNoPlat());
                tvNamaMobil.setText(riwayatDriver.getNamaMobil());
                String urlImage = "http://192.168.100.7:8000/storage/foto_mobil/"+riwayatDriver.getFotoMobil();
                Glide.with(context)
                        .load(urlImage)
                        .apply(new RequestOptions().override(600, 200))
                        .into(ivFotoMobil);
                tvTarifMobilHarian.setText("Rp "+String.valueOf(riwayatDriver.getTarifMobilHarian())+"/Hari");
                tvTotalBiayaMobil.setText("Rp "+String.valueOf(riwayatDriver.getTotalBiayaMobil()));
                
                tvIdDriver.setText(riwayatDriver.getIdDriver());
                tvNamaDriver.setText(riwayatDriver.getNamaDriver());
                tvTarifDriverHarian.setText("Rp "+String.valueOf(riwayatDriver.getTarifDriverHarian())+"/Hari");
                String urlImage1 = "http://192.168.100.7:8000/storage/foto_driver/"+riwayatDriver.getFotoDriver();
                Glide.with(context)
                        .load(urlImage1)
                        .apply(new RequestOptions().override(600, 200))
                        .into(ivFotoDriver);
                tvTotalBiayaDriver.setText("Rp "+String.valueOf(riwayatDriver.getTotalBiayaDriver()));
                
                tvNamaPegawai.setText(riwayatDriver.getNamaPegawai());
                tvKodePromo.setText(riwayatDriver.getKodePromo());
                tvNamaPegawai.setText(riwayatDriver.getNamaPegawai());
                tvTglTransaksi.setText(riwayatDriver.getTglTransaksi());
                tvTglMulai.setText(riwayatDriver.getTglMulaiSewa());
                tvTglSelesai.setText(riwayatDriver.getTglSelesaiSewa());
                tvTglPengembalian.setText(riwayatDriver.getTglPengembalian());

                float temp = (riwayatDriver.getTotalBiayaDriver()+riwayatDriver.getTotalBiayaMobil())*riwayatDriver.getPotonganPromo();
                String format = String.format("%.2f",temp);
                tvPotonganPromo.setText("Rp "+format);
                tvBiayaEkstensi.setText("Rp "+String.valueOf(riwayatDriver.getTotalBiayaEkstensi()));
                tvGrandTotal.setText("Rp "+String.valueOf(riwayatDriver.getGrandTotal()));
                tvDurasi.setText(String.valueOf(riwayatDriver.getDurasiPenyewaan())+" Hari");
                tvStatus.setText(riwayatDriver.getStatusTransaksi());
                rtRatingDriver.setRating((float)riwayatDriver.getRatingDriver());
                builder.setView(newLayout);
                AlertDialog popup = builder.create();
                popup.show();

                btnCloseDetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popup.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return riwayatDriverList.size();
    }

    public void setRiwayatDriverList(List<RiwayatDriver> riwayatDriverList) {
        this.riwayatDriverList = riwayatDriverList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //Item
        protected TextView tvNoTransaksi, tvTglTransaksi, tvStatus, tvTglMulai, tvTglSelesai;
        protected CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNoTransaksi = itemView.findViewById(R.id.tv_no_transaksi_item_driver);
            tvTglTransaksi = itemView.findViewById(R.id.tv_tgl_transaksi_item_driver);
            tvStatus = itemView.findViewById(R.id.tv_status_item_driver);
            tvTglMulai = itemView.findViewById(R.id.tv_tgl_mulai_sewa_item_driver);
            tvTglSelesai = itemView.findViewById(R.id.tv_tgl_selesai_sewa_item_driver);
            cardView = itemView.findViewById(R.id.cv_riwayat_driver);
        }
    }
}
