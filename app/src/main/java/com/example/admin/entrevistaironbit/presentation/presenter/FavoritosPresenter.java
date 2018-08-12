package com.example.admin.entrevistaironbit.presentation.presenter;

import com.example.admin.entrevistaironbit.domain.interactor.FavoritosInteractor;
import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Venue;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FavoritosPresenter extends Presenter<FavoritosPresenter.View> {
    private final FavoritosInteractor favoritosInteractor;

    @Inject
    public FavoritosPresenter(FavoritosInteractor favoritosInteractor) {
        this.favoritosInteractor = favoritosInteractor;
    }

    @Override
    public void terminate() {
        super.terminate();
        setView(null);
    }

    public void recuperaFavoritosAll() {
        List<Venue> listVenue = new ArrayList<>();
        favoritosInteractor.recuperaFavoritosALL().subscribe(favorito -> listVenue.add(new Gson().fromJson(favorito.getRegistroJson(), Venue.class)),
                throwable -> {},
                () -> getView().obtieneFavoritosAll(listVenue));
    }

    public interface View extends Presenter.View {
        void obtieneFavoritosAll(List<Venue> venueList);
    }
}