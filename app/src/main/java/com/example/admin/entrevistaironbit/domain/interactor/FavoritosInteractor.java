package com.example.admin.entrevistaironbit.domain.interactor;

import com.example.admin.entrevistaironbit.data.db.dao.ICrud;
import com.example.admin.entrevistaironbit.domain.modelo.modeloDB.Favorito;
import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Venue;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class FavoritosInteractor {
    private final ICrud iCrud;
    private FavoritosInteractorListener favoritosInteractorListener;
    
    @Inject
    public FavoritosInteractor(ICrud iCrud) {
        this.iCrud = iCrud;
    }

    public void setFavoritosInteractorListener(FavoritosInteractorListener favoritosInteractorListener) {
        this.favoritosInteractorListener = favoritosInteractorListener;
    }

    @SuppressWarnings("unchecked")
    public void recuperaFavoritosALL() {
        List<Venue> listVenue = new ArrayList<>();
        Observable.fromIterable((List<Favorito>) iCrud.findAll())
                .subscribe(favorito -> listVenue.add(new Gson().fromJson(favorito.getRegistroJson(), Venue.class)),
                        throwable -> {},
                        () -> favoritosInteractorListener.retornaFavoritosAll(listVenue));


        /*Observable.just((List<Favorito>) iCrud.findAll())
                .subscribe(favoritos -> {
                            for (Favorito favorito : favoritos) {
                                listVenue.add(new Gson().fromJson(favorito.getRegistroJson(), Venue.class));
                            }
                        },
                        throwable -> {},
                        () -> favoritosInteractorListener.retornaFavoritosAll(listVenue));*/
    }

    public interface FavoritosInteractorListener {
        void retornaFavoritosAll(List<Venue> venueList);
    }
}