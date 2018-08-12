package com.example.admin.entrevistaironbit.presentation.presenter;

import com.example.admin.entrevistaironbit.domain.interactor.LugaresInteractor;
import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Venue;

import java.util.List;

import javax.inject.Inject;

public class LugaresPresenter extends Presenter<LugaresPresenter.View> implements LugaresInteractor.LugaresInteractorListener {
    private final LugaresInteractor lugaresInteractor;

    @Inject
    public LugaresPresenter(LugaresInteractor lugaresInteractor) {
        this.lugaresInteractor = lugaresInteractor;
        this.lugaresInteractor.setLugaresInteractorListener(this);
    }

    @Override
    public void terminate() {
        super.terminate();
        setView(null);
    }

    public void recuperaFavoritosGPS(List<Venue> venueList) {
        lugaresInteractor.recuperaFavoritosGPS(venueList);
    }

    public void insertaFavoritoDB(Venue venue) {
        lugaresInteractor.creaFavoritoDB(venue);
    }

    public void borraFavoritoDB(Venue venue) {
        lugaresInteractor.eliminaFavoritoDB(venue);
    }

    @Override
    public void retornaFavoritosGPS(List<Venue> venueList) {
        getView().obtieneFavoritosGPS(venueList);
    }

    public interface View extends Presenter.View {
        void obtieneFavoritosGPS(List<Venue> venueList);
    }
}