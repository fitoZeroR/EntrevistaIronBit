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

import static com.example.admin.entrevistaironbit.utilidades.Constantes.*;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class LugaresFragment extends BaseFragment implements LugaresPresenter.View, CustomAdapterLugares.AdapterLugaresBDListener {
    @BindView(R.id.rcv_principal_id)
    RecyclerView rcvPrincipal;

    @Inject
    public LugaresPresenter lugaresPresenter;

    private final String gps;

    private final List<Venue> venueList;

    private LugaresFragment(List<Venue> venueList, String gps) {
        this.venueList = venueList;
        this.gps = gps;
    }

    public static LugaresFragment initInstance(List<Venue> venueList, String gps) {
        return new LugaresFragment(venueList, gps);
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
                    Intent intentDetalle = new Intent(context(), DetalleActivity.class);
                    Bundle bundleDetalle = new Bundle();
                    bundleDetalle.putString(BUNDLE_GPS, gps);
                    bundleDetalle.putDouble(BUNDLE_LATITUD, venueList.get(rcvPrincipal.getChildAdapterPosition(itemView)).getLocation().getLat());
                    bundleDetalle.putDouble(BUNDLE_LONGITUD, venueList.get(rcvPrincipal.getChildAdapterPosition(itemView)).getLocation().getLng());
                    bundleDetalle.putString(BUNDLE_NOMBRE, venueList.get(rcvPrincipal.getChildAdapterPosition(itemView)).getName() + ": " + (venueList.get(rcvPrincipal.getChildAdapterPosition(itemView)).getCategories().size() == 0 ? context().getString(R.string.txt_sin_informacion) : venueList.get(rcvPrincipal.getChildAdapterPosition(itemView)).getCategories().get(0).getName()));
                    intentDetalle.putExtras(bundleDetalle);
                    startActivity(intentDetalle);
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