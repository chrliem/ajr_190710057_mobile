package com.p3l.ajr_190710057.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.p3l.ajr_190710057.R;
import com.p3l.ajr_190710057.models.Mobil;
import com.p3l.ajr_190710057.preferences.CustomerPreferences;

import java.util.List;
import java.util.logging.Filter;

public class MobilAdapter extends RecyclerView.Adapter<MobilAdapter.ViewHolder> {
    private List<Mobil> mobilList;
    private Context context;
    CustomerPreferences customerPreferences;

    public MobilAdapter(List<Mobil> mobilList, Context context, CustomerPreferences customerPreferences) {
        this.mobilList = mobilList;
        this.context = context;
        this.customerPreferences = customerPreferences;
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
        holder.tvVolumeBahanBakar.setText(mobil.getVolumeBahanBakar());
        holder.tvFasilitas.setText(mobil.getFasilitasMobil());
        holder.tvTarifHarianMobil.setText((int) mobil.getTarifMobilHarian());
    }

    @Override
    public int getItemCount() {
        return mobilList.size();
    }

    public void setMobilList(List<Mobil> mobilList){
        this.mobilList = mobilList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaMobil, tvTipeMobil, tvJenisTransmisi, tvJenisBahanBakar, tvWarnaMobil, tvVolumeBahanBakar, tvFasilitas, tvTarifHarianMobil;
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
            ivFotoMobil = itemView.findViewById(R.id.iv_foto_mobil);
        }
    }
}
