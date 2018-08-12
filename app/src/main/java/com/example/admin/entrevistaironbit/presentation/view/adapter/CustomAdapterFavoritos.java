package com.example.admin.entrevistaironbit.presentation.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.entrevistaironbit.R;
import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Venue;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomAdapterFavoritos extends RecyclerView.Adapter<CustomAdapterFavoritos.FavoritosViewHolder> {
    private final List<Venue> venueList;
    private final Context context;

    public CustomAdapterFavoritos(List<Venue> venueList, Context context) {
        this.venueList = venueList;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoritosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FavoritosViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_fragments, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritosViewHolder favoritosViewHolder, int i) {
        favoritosViewHolder.txvNombreLugar.setText(venueList.get(i).getName());
        favoritosViewHolder.txvCategoria.setText(venueList.get(i).getCategories().size() == 0 ? context.getString(R.string.txt_sin_informacion) : venueList.get(i).getCategories().get(0).getName());
        favoritosViewHolder.txvDistancia.setText(venueList.get(i).getDistancia() + " M");
        favoritosViewHolder.imvFavoritos.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return venueList.size();
    }

    static class FavoritosViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txv_nombre_lugar_id)
        TextView txvNombreLugar;
        @BindView(R.id.txv_categoria_id)
        TextView txvCategoria;
        @BindView(R.id.txv_distancia_id)
        TextView txvDistancia;
        @BindView(R.id.imv_favoritos_id)
        AppCompatImageView imvFavoritos;

        FavoritosViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}