package com.example.admin.entrevistaironbit.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.entrevistaironbit.R;
import com.example.admin.entrevistaironbit.modelo.Lugar;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class CustomAdapterPrincipal extends RecyclerView.Adapter<CustomAdapterPrincipal.PrincipalViewHolder> {
    private final List<Lugar> listaLugares;
    private final PublishSubject<View> mViewClickSubject;

    public CustomAdapterPrincipal(List<Lugar> listaLugares) {
        this.listaLugares = listaLugares;
        mViewClickSubject = PublishSubject.create();
    }

    @NonNull
    @Override
    public PrincipalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_principal, viewGroup, false);

        RxView.clicks(itemView)
                .takeUntil(RxView.detaches(viewGroup))
                .map(aVoid -> itemView)
                .subscribe(mViewClickSubject);

        return new PrincipalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PrincipalViewHolder principalViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return listaLugares.size();
    }

    public Observable<View> getViewClickedObservable() {
        return mViewClickSubject.hide();
    }

    static class PrincipalViewHolder extends RecyclerView.ViewHolder {
        /*@BindView(R.id.txv_plantel_id)
        TextView txvPlantel;
        @BindView(R.id.txv_telefono_id)
        TextView txvTelefono;*/

        PrincipalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}