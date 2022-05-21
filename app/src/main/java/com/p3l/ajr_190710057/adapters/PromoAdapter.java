package com.p3l.ajr_190710057.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.p3l.ajr_190710057.R;
import com.p3l.ajr_190710057.models.Promo;
import com.p3l.ajr_190710057.preferences.CustomerPreferences;

import java.util.List;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.ViewHolder> {
    private List<Promo> promoList;
    private Context context;
    CustomerPreferences customerPreferences;

    public PromoAdapter(List<Promo> promoList, Context context) {
        this.promoList = promoList;
        this.context = context;
    }

    @NonNull
    @Override
    public PromoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_promo, parent, false);
        return new PromoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromoAdapter.ViewHolder holder, int position) {
        Promo promo = promoList.get(position);

        holder.tvKodePromo.setText(promo.getKodePromo());
        holder.tvJenisPromo.setText(promo.getJenisPromo());
        String potonganPromo =String.format("%.1f",promo.getPotonganPromo()*100);
        holder.tvPotonganPromo.setText(potonganPromo+'%');
        holder.tvKeterangan.setText(promo.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return promoList.size();
    }

    public void setPromoList(List<Promo> promoList){
        this.promoList = promoList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvKodePromo, tvPotonganPromo, tvKeterangan, tvJenisPromo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvKodePromo = itemView.findViewById(R.id.tv_kode_promo);
            tvPotonganPromo = itemView.findViewById(R.id.tv_potongan_promo);
            tvKeterangan = itemView.findViewById(R.id.tv_keterangan);
            tvJenisPromo = itemView.findViewById(R.id.tv_jenis_promo);
        }
    }
}
