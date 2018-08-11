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
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class CustomAdapterLugares extends RecyclerView.Adapter<CustomAdapterLugares.LugaresViewHolder> {
    private final List<Venue> venueList;
    private final PublishSubject<View> mViewClickSubject;
    private final Context context;
    private final AdapterLugaresBDListener adapterLugaresBDListener;

    public CustomAdapterLugares(List<Venue> venueList, Context context, AdapterLugaresBDListener adapterLugaresBDListener) {
        this.adapterLugaresBDListener = adapterLugaresBDListener;
        this.venueList = venueList;
        this.context = context;
        mViewClickSubject = PublishSubject.create();
    }

    @NonNull
    @Override
    public LugaresViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_fragments, viewGroup, false);

        RxView.clicks(itemView)
                .takeUntil(RxView.detaches(viewGroup))
                .map(aVoid -> itemView)
                .subscribe(mViewClickSubject);

        return new LugaresViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LugaresViewHolder lugaresViewHolder, int i) {
        lugaresViewHolder.txvNombreLugar.setText(venueList.get(i).getName());
        lugaresViewHolder.txvCategoria.setText(venueList.get(i).getCategories().size() == 0 ? context.getString(R.string.txt_sin_informacion) : venueList.get(i).getCategories().get(0).getName());
        lugaresViewHolder.txvDistancia.setText(venueList.get(i).getDistancia() + " M");

        lugaresViewHolder.imvFavoritos.setBackgroundResource(venueList.get(i).isFavorito() ? R.drawable.ic_favorite_si : R.drawable.ic_favorite_no);
        lugaresViewHolder.imvFavoritos.setOnClickListener(view -> {

            if (venueList.get(i).isFavorito()) {
                adapterLugaresBDListener.eliminaFavorito(venueList.get(i));
                lugaresViewHolder.imvFavoritos.setBackgroundResource(R.drawable.ic_favorite_no);
            } else {
                adapterLugaresBDListener.creaFavorito(venueList.get(i));
                lugaresViewHolder.imvFavoritos.setBackgroundResource(R.drawable.ic_favorite_si);
            }
        });
    }

    @Override
    public int getItemCount() {
        return venueList.size();
    }

    public Observable<View> getViewClickedObservable() {
        return mViewClickSubject.hide();
    }

    static class LugaresViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txv_nombre_lugar_id)
        TextView txvNombreLugar;
        @BindView(R.id.txv_categoria_id)
        TextView txvCategoria;
        @BindView(R.id.txv_distancia_id)
        TextView txvDistancia;
        @BindView(R.id.imv_favoritos_id)
        AppCompatImageView imvFavoritos;

        LugaresViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface AdapterLugaresBDListener {
        void creaFavorito(Venue venue);
        void eliminaFavorito(Venue venue);
    }
}