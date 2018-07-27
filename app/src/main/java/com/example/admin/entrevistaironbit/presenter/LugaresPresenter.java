package com.example.admin.entrevistaironbit.presenter;

import android.content.Context;

import com.example.admin.entrevistaironbit.interactor.LugaresInteractor;
import com.example.admin.entrevistaironbit.modelo.modeloWS.Venue;

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

    public void recuperaFavoritosGPS(List<Venue> venueList, Context context) {
        lugaresInteractor.recuperaFavoritosGPS(venueList, context);
    }

    public void insertaFavoritoDB(Venue venue, Context context) {
        lugaresInteractor.creaFavoritoDB(venue, context);
    }
    public void borraFavoritoDB(Venue venue, Context context) {
        lugaresInteractor.eliminaFavoritoDB(venue, context);
    }

    @Override
    public void retornaFavoritosGPS(List<Venue> venueList) {
        getView().obtieneFavoritosGPS(venueList);
    }

    public interface View extends Presenter.View {
        void obtieneFavoritosGPS(List<Venue> venueList);
    }
}