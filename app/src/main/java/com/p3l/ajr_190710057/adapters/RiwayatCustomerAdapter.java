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
import com.p3l.ajr_190710057.models.RiwayatCustomer;

import java.util.List;

public class RiwayatCustomerAdapter extends RecyclerView.Adapter<RiwayatCustomerAdapter.ViewHolder> {
    private List<RiwayatCustomer> riwayatCustomerList;
    private Context context;

    public RiwayatCustomerAdapter(List<RiwayatCustomer> riwayatCustomerList, Context context) {
        this.riwayatCustomerList = riwayatCustomerList;
        this.context = context;
    }

    @NonNull
    @Override
    public RiwayatCustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_riwayat_customer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatCustomerAdapter.ViewHolder holder, int position) {
        RiwayatCustomer riwayatCustomer = riwayatCustomerList.get(position);

        holder.tvNoTransaksi.setText(riwayatCustomer.getNoTransaksi());
        holder.tvTglTransaksi.setText(riwayatCustomer.getTglTransaksi());
        if(riwayatCustomer.getNamaDriver()==null){
            holder.tvTipeSewa.setText("Sewa Mobil Tanpa Driver");
        }else{
            holder.tvTipeSewa.setText("Sewa Mobil dengan Driver");
        }
        holder.tvTotalTransaksi.setText("Rp "+String.valueOf(riwayatCustomer.getTotalPembayaran()));
        holder.tvStatusTransaksiH.setText(riwayatCustomer.getStatusTransaksi());
        holder.tvStatusPembayaranH.setText(riwayatCustomer.getStatusPembayaran());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                View newLayout = LayoutInflater.from(builder.getContext())
                        .inflate(R.layout.detail_riwayat_transaksi_customer, null);

                TextView tvNoTransaksiDetail, tvTipeSewaDetail, tvNamaMobil, tvNamaDriver, tvTarifMobilHarian, tvtarifDriverHarian,
                        tvTglTransaksiDetail, tvTglMulaiSewa, tvTglSelesaiSewa, tvTglPengembalian, tvNamaPegawai, tvKodePromo, tvPotonganPromo,
                        tvMetodePembayaran, tvTotalBiayaMobil, tvTotalBiayaDriver, tvTotalBiayaEkstensi, tvTotalPembayaran, tvStatusPembayaran, tvStatusTransaksi,tvDurasiSewa,
                        txtRatingAJR, txtRatingDriver, txtRating, tvIdDriver, tvNoPlat;
                RatingBar rtRatingAJR, rtRatingDriver;
                MaterialButton btnCloseDetail;
                ImageView ivFotoMobil, ivFotoDriver, ivBuktiPembayaran;

                tvNoTransaksiDetail = newLayout.findViewById(R.id.tv_no_transaksi_detail);
                tvTipeSewaDetail = newLayout.findViewById(R.id.tv_tipe_sewa_detail);
                tvNamaMobil = newLayout.findViewById(R.id.tv_nama_mobil);
                ivFotoMobil = newLayout.findViewById(R.id.iv_foto_mobil_detail);
                ivFotoDriver = newLayout.findViewById(R.id.iv_foto_driver_detail);
                tvNamaDriver = newLayout.findViewById(R.id.tv_nama_driver);
                tvNamaPegawai = newLayout.findViewById(R.id.tv_nama_pegawai);
                tvTarifMobilHarian = newLayout.findViewById(R.id.tv_tarif_mobil_harian);
                tvtarifDriverHarian = newLayout.findViewById(R.id.tv_tarif_driver_harian);
                tvTglTransaksiDetail = newLayout.findViewById(R.id.tv_tgl_transaksi_detail);
                tvTglMulaiSewa = newLayout.findViewById(R.id.tv_tgl_mulai_sewa);
                tvTglSelesaiSewa = newLayout.findViewById(R.id.tv_tgl_selesai_sewa);
                tvTglPengembalian = newLayout.findViewById(R.id.tv_tgl_pengembalian);
                tvKodePromo = newLayout.findViewById(R.id.tv_kode_promo_detail);
                tvPotonganPromo = newLayout.findViewById(R.id.tv_potongan_promo_detail);
                tvMetodePembayaran = newLayout.findViewById(R.id.tv_metode_pembayaran);
                tvTotalBiayaMobil = newLayout.findViewById(R.id.tv_total_biaya_mobil);
                tvTotalBiayaDriver = newLayout.findViewById(R.id.tv_total_biaya_driver);
                tvTotalBiayaEkstensi = newLayout.findViewById(R.id.tv_total_biaya_ekstensi);
                tvTotalPembayaran = newLayout.findViewById(R.id.tv_grand_total_pembayaran);
                tvStatusPembayaran = newLayout.findViewById(R.id.tv_status_pembayaran);
                tvStatusTransaksi = newLayout.findViewById(R.id.tv_status_transaksi);
                tvDurasiSewa = newLayout.findViewById(R.id.tv_durasi_sewa);
                rtRatingAJR = newLayout.findViewById(R.id.rt_rating_ajr);
                rtRatingDriver = newLayout.findViewById(R.id.rt_rating_driver);
                btnCloseDetail = newLayout.findViewById(R.id.btnCloseDetail);
                txtRatingAJR = newLayout.findViewById(R.id.txt_rating_ajr);
                txtRatingDriver = newLayout.findViewById(R.id.txt_rating_driver);
                txtRating = newLayout.findViewById(R.id.txt_rating);
                tvNoPlat = newLayout.findViewById(R.id.tv_no_plat_mobil);
                tvIdDriver = newLayout.findViewById(R.id.tv_no_driver);
                ivBuktiPembayaran = newLayout.findViewById(R.id.iv_bukti_pembayaran);

                tvNoTransaksiDetail.setText(riwayatCustomer.getNoTransaksi());
                if(riwayatCustomer.getNamaDriver()==null){
                    tvTipeSewaDetail.setText("Sewa Mobil Tanpa Driver");
                    String urlImage = "http://192.168.100.7:8000/storage/foto_mobil/"+riwayatCustomer.getFotoMobil();
                    Glide.with(context)
                            .load(urlImage)
                            .apply(new RequestOptions().override(600, 200))
                            .into(ivFotoMobil);
                    tvNamaMobil.setText(riwayatCustomer.getNamaMobil());
                    tvNoPlat.setText(riwayatCustomer.getNoPlat());
                    tvTarifMobilHarian.setText("Rp "+String.valueOf(riwayatCustomer.getTarifMobilHarian())+"/Hari");
                    if(riwayatCustomer.getRatingAJR()==null){
                        rtRatingAJR.setVisibility(View.GONE);
                        txtRatingAJR.setVisibility(View.GONE);
                        txtRating.setVisibility(View.GONE);
                    }else{
                        rtRatingAJR.setRating((float) riwayatCustomer.getRatingAJR());
                    }

                    tvTotalBiayaMobil.setText("Rp"+String.valueOf(riwayatCustomer.getTotalBiayaMobil()));
                    ivFotoDriver.setVisibility(View.GONE);
                    tvNamaDriver.setVisibility(View.GONE);
                    tvtarifDriverHarian.setVisibility(View.GONE);
                    tvTotalBiayaDriver.setVisibility(View.GONE);
                    tvIdDriver.setVisibility(View.GONE);
                    rtRatingDriver.setVisibility(View.GONE);
                    txtRatingDriver.setVisibility(View.GONE);
                }else{
                    tvTipeSewaDetail.setText("Sewa Mobil dengan Driver");
                    String urlImage = "http://192.168.100.7:8000/storage/foto_mobil/"+riwayatCustomer.getFotoMobil();
                    Glide.with(context)
                            .load(urlImage)
                            .apply(new RequestOptions().override(600, 200))
                            .into(ivFotoMobil);
                    tvNamaMobil.setText(riwayatCustomer.getNamaMobil());
                    tvTarifMobilHarian.setText("Rp "+String.valueOf(riwayatCustomer.getTarifMobilHarian())+"/Hari");
                    tvTotalBiayaMobil.setText("Rp"+String.valueOf(riwayatCustomer.getTotalBiayaMobil()));
                    tvNoPlat.setText(riwayatCustomer.getNoPlat());
                    String urlImage1 = "http://192.168.100.7:8000/storage/foto_driver/"+riwayatCustomer.getFotoDriver();
                    Glide.with(context)
                            .load(urlImage1)
                            .apply(new RequestOptions().override(600, 200))
                            .into(ivFotoDriver);
                    tvNamaDriver.setText(riwayatCustomer.getNamaDriver());
                    tvIdDriver.setText(riwayatCustomer.getIdDriver());
                    tvtarifDriverHarian.setText("Rp "+String.valueOf(riwayatCustomer.getTarifDriverHarian())+"/Hari");
                    tvTotalBiayaDriver.setText("Rp"+String.valueOf(riwayatCustomer.getTotalBiayaDriver()));
                    if(riwayatCustomer.getRatingAJR()==null && riwayatCustomer.getRatingDriver()==null){
                        txtRating.setVisibility(View.GONE);
                    }

                    if(riwayatCustomer.getRatingAJR()==null){
                        rtRatingAJR.setVisibility(View.GONE);
                        txtRatingAJR.setVisibility(View.GONE);
                    }else{
                        rtRatingAJR.setRating((float)riwayatCustomer.getRatingAJR());
                    }

                    if(riwayatCustomer.getRatingDriver()==null){
                        rtRatingDriver.setVisibility(View.GONE);
                        txtRatingDriver.setVisibility(View.GONE);
                    }else{
                        rtRatingDriver.setRating((float)riwayatCustomer.getRatingDriver());
                    }

                }
                tvNamaPegawai.setText(riwayatCustomer.getNamaPegawai());
                tvTglTransaksiDetail.setText(riwayatCustomer.getTglTransaksi());
                tvTglMulaiSewa.setText(riwayatCustomer.getTglMulaiSewa());
                tvTglSelesaiSewa.setText(riwayatCustomer.getTglSelesaiSewa());
                tvTglPengembalian.setText(riwayatCustomer.getTglPengembalian());
                tvKodePromo.setText(riwayatCustomer.getKodePromo());
                tvMetodePembayaran.setText(riwayatCustomer.getMetodePembayaran());
                float temp = (riwayatCustomer.getTotalBiayaDriver()+riwayatCustomer.getTotalBiayaMobil())*riwayatCustomer.getPotonganPromo();
                String format = String.format("%.2f",temp);
                tvPotonganPromo.setText("Rp "+format);
                tvTotalBiayaEkstensi.setText("Rp "+String.valueOf(riwayatCustomer.getTotalBiayaEkstensi()));
                tvTotalPembayaran.setText("Rp "+String.valueOf(riwayatCustomer.getTotalPembayaran()));
                tvDurasiSewa.setText(String.valueOf(riwayatCustomer.getDurasiSewa())+" Hari");
                tvStatusPembayaran.setText(riwayatCustomer.getStatusPembayaran());
                tvStatusTransaksi.setText(riwayatCustomer.getStatusTransaksi());
                if(riwayatCustomer.getBuktiPembayaran()==null){
                    ivBuktiPembayaran.setVisibility(View.GONE);
                }else{
                    String urlImage1 = "http://192.168.100.7:8000/storage/bukti_pembayaran/"+riwayatCustomer.getBuktiPembayaran();
                    Glide.with(context)
                            .load(urlImage1)
                            .apply(new RequestOptions().override(1280, 720))
                            .into(ivBuktiPembayaran);
                }

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
        return riwayatCustomerList.size();
    }

    public void setRiwayatCustomerList(List<RiwayatCustomer> riwayatCustomerList) {
        this.riwayatCustomerList = riwayatCustomerList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //Item
        protected TextView tvNoTransaksi, tvTglTransaksi, tvTipeSewa, tvTotalTransaksi, tvStatusPembayaranH, tvStatusTransaksiH;
        protected CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNoTransaksi = itemView.findViewById(R.id.tv_no_transaksi);
            tvTglTransaksi = itemView.findViewById(R.id.tv_tgl_transaksi);
            tvTipeSewa = itemView.findViewById(R.id.tv_tipe_sewa);
            tvTotalTransaksi = itemView.findViewById(R.id.tv_total_transaksi);
            tvStatusPembayaranH = itemView.findViewById(R.id.tv_status_pembayaran_history);
            tvStatusTransaksiH = itemView.findViewById(R.id.tv_status_transaksi_history);

            cardView = itemView.findViewById(R.id.cv_riwayat_customer);

        }
    }
}
