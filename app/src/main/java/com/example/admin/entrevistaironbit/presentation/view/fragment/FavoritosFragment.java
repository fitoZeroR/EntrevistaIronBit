package com.example.admin.entrevistaironbit.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.entrevistaironbit.R;
import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Venue;
import com.example.admin.entrevistaironbit.presentation.presenter.FavoritosPresenter;
import com.example.admin.entrevistaironbit.presentation.view.adapter.CustomAdapterFavoritos;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends BaseFragment implements FavoritosPresenter.View {
    @BindView(R.id.rcv_principal_id)
    RecyclerView rcvPrincipal;

    @Inject
    public FavoritosPresenter favoritosPresenter;

    private static FavoritosFragment instancia;

    public static FavoritosFragment newInstance() {
        if (instancia == null) {
            instancia = new FavoritosFragment();
        }
        return instancia;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        favoritosPresenter.setView(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_principal, container, false);
        ButterKnife.bind(this, view);

        favoritosPresenter.recuperaFavoritosAll();

        return view;
    }

    @Override
    public void onDestroy() {
        favoritosPresenter.terminate();
        super.onDestroy();
    }

    @Override
    public void obtieneFavoritosAll(List<Venue> venueList) {
        CustomAdapterFavoritos customAdapterFavoritos = new CustomAdapterFavoritos(venueList, getContext());
        rcvPrincipal.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvPrincipal.setHasFixedSize(true);
        rcvPrincipal.swapAdapter(customAdapterFavoritos, true);
    }

    @Override
    public Context context() {
        return getActivity();
    }
}