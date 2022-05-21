package com.p3l.ajr_190710057.adapters;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.p3l.ajr_190710057.R;
import com.p3l.ajr_190710057.api.MobilApi;
import com.p3l.ajr_190710057.models.Mobil;
import com.p3l.ajr_190710057.preferences.CustomerPreferences;

import java.util.List;
import java.util.logging.Filter;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

public class MobilAdapter extends RecyclerView.Adapter<MobilAdapter.ViewHolder> {
    private List<Mobil> mobilList;
    private Context context;
    MobilApi mobilApi;
    CustomerPreferences customerPreferences;

    public MobilAdapter(List<Mobil> mobilList, Context context) {
        this.mobilList = mobilList;
        this.context = context;
    }

    @NonNull
    @Override
    public MobilAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_mobil, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MobilAdapter.ViewHolder holder, int position) {
        Mobil mobil = mobilList.get(position);
        //Kurang Foto Mobil
        holder.tvNamaMobil.setText(mobil.getNamaMobil());
        holder.tvTipeMobil.setText(mobil.getTipeMobil());
        holder.tvJenisTransmisi.setText(mobil.getJenisTransmisi());
        holder.tvJenisBahanBakar.setText(mobil.getJenisBahanBakar());
        holder.tvWarnaMobil.setText(mobil.getWarnaMobil());
        holder.tvVolumeBahanBakar.setText(String.valueOf(mobil.getVolumeBahanBakar())+" Liter");
        holder.tvFasilitas.setText(mobil.getFasilitasMobil());
        holder.tvTarifHarianMobil.setText("Rp "+String.valueOf(mobil.getTarifMobilHarian()+"/Hari"));
        holder.tvKapasitasPenumpang.setText(String.valueOf(mobil.getKapasitasPenumpang()+" Orang"));
        String urlImage = "http://192.168.100.7:8000/storage/foto_mobil/"+mobil.getFotoMobil();
        Glide.with(context)
                .load(urlImage)
                .into(holder.ivFotoMobil);
    }

    @Override
    public int getItemCount() {
        return mobilList.size();
    }

    public void setMobilList(List<Mobil> mobilList){
        this.mobilList = mobilList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaMobil, tvTipeMobil, tvJenisTransmisi, tvJenisBahanBakar, tvWarnaMobil, tvVolumeBahanBakar, tvFasilitas, tvTarifHarianMobil, tvKapasitasPenumpang;
        ImageView ivFotoMobil;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaMobil = itemView.findViewById(R.id.tv_nama_mobil);
            tvTipeMobil = itemView.findViewById(R.id.tv_tipe_mobil);
            tvJenisTransmisi = itemView.findViewById(R.id.tv_jenis_transmisi);
            tvJenisBahanBakar = itemView.findViewById(R.id.tv_jenis_bahan_bakar);
            tvWarnaMobil = itemView.findViewById(R.id.tv_warna_mobil);
            tvVolumeBahanBakar = itemView.findViewById(R.id.tv_volume_bahan_bakar);
            tvFasilitas = itemView.findViewById(R.id.tv_fasilitas_mobil);
            tvTarifHarianMobil = itemView.findViewById(R.id.tv_tarif_mobil_harian);
            tvKapasitasPenumpang = itemView.findViewById(R.id.tv_kapasitas_mobil);
            ivFotoMobil = itemView.findViewById(R.id.iv_foto_mobil);
        }
    }
}
