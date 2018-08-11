package com.example.admin.entrevistaironbit.presentation.presenter;

import com.example.admin.entrevistaironbit.domain.interactor.FavoritosInteractor;
import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Venue;

import java.util.List;

import javax.inject.Inject;

public class FavoritosPresenter extends Presenter<FavoritosPresenter.View> implements FavoritosInteractor.FavoritosInteractorListener {
    private final FavoritosInteractor favoritosInteractor;

    @Inject
    public FavoritosPresenter(FavoritosInteractor favoritosInteractor) {
        this.favoritosInteractor = favoritosInteractor;
        this.favoritosInteractor.setFavoritosInteractorListener(this);
    }

    @Override
    public void terminate() {
        super.terminate();
        setView(null);
    }

    public void recuperaFavoritosAll() {
        favoritosInteractor.recuperaFavoritosALL();
    }

    @Override
    public void retornaFavoritosAll(List<Venue> venueList) {
        getView().obtieneFavoritosAll(venueList);
    }

    public interface View extends Presenter.View {
        void obtieneFavoritosAll(List<Venue> venueList);
    }
}