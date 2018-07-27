package com.example.admin.entrevistaironbit.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.entrevistaironbit.R;
import com.example.admin.entrevistaironbit.di.components.ClienteComponent;
import com.example.admin.entrevistaironbit.modelo.modeloWS.Venue;
import com.example.admin.entrevistaironbit.presenter.LugaresPresenter;
import com.example.admin.entrevistaironbit.view.activity.DetalleActivity;
import com.example.admin.entrevistaironbit.view.adapter.CustomAdapterLugares;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class LugaresFragment extends BaseFragment implements LugaresPresenter.View, CustomAdapterLugares.AdapterLugaresBDListener {
    @BindView(R.id.rcv_principal_id)
    RecyclerView rcvPrincipal;

    @Inject
    public LugaresPresenter lugaresPresenter;

    private final List<Venue> venueList;

    private LugaresFragment(List<Venue> venueList) {
        this.venueList = venueList;
    }

    public static LugaresFragment initInstance(List<Venue> venueList) {
        return new LugaresFragment(venueList);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(ClienteComponent.class).inject(this);
        lugaresPresenter.setView(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_principal, container, false);
        ButterKnife.bind(this, view);

        lugaresPresenter.recuperaFavoritosGPS(venueList, context());

        return view;
    }

    @Override
    public void onDestroy() {
        lugaresPresenter.terminate();
        super.onDestroy();
    }

    @Override
    public void obtieneFavoritosGPS(List<Venue> venueList) {
        CustomAdapterLugares customAdapterLugares = new CustomAdapterLugares(venueList, context(), this);
        customAdapterLugares.getViewClickedObservable()
                .subscribe(itemView -> {
                    /*Intent intentDetalle = new Intent(context(), DetalleActivity.class);
                    Bundle bundleDetalle = new Bundle();
                    bundleDetalle.putDouble(BUNDLE_LATITUD, registroList.get(rcvRegistros.getChildAdapterPosition(itemView)).getGeometry().getLocation().getLat());
                    bundleDetalle.putDouble(BUNDLE_LONGITUD, registroList.get(rcvRegistros.getChildAdapterPosition(itemView)).getGeometry().getLocation().getLng());
                    bundleDetalle.putString(BUNDLE_NOMBRE, registroList.get(rcvRegistros.getChildAdapterPosition(itemView)).getName());
                    intentDetalle.putExtras(bundleDetalle);
                    startActivity(intentDetalle);*/
                    startActivity(new Intent(context(), DetalleActivity.class));
                });
        rcvPrincipal.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvPrincipal.setHasFixedSize(true);
        rcvPrincipal.swapAdapter(customAdapterLugares, true);
    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public void creaFavorito(Venue venue) {
        lugaresPresenter.insertaFavoritoDB(venue, context());
    }

    @Override
    public void eliminaFavorito(Venue venue) {
        lugaresPresenter.borraFavoritoDB(venue, context());
    }
}