package com.example.admin.entrevistaironbit.interactor;

import com.example.admin.entrevistaironbit.db.dao.ICrud;
import com.example.admin.entrevistaironbit.modelo.modeloDB.Favorito;
import com.example.admin.entrevistaironbit.modelo.modeloWS.Venue;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
        List<Favorito> listFavoritos = (List<Favorito>) iCrud.findAll();
        List<Venue> listVenue = new ArrayList<>();
        for (Favorito favorito : listFavoritos) {
            listVenue.add(new Gson().fromJson(favorito.getRegistroJson(), Venue.class));
        }
        favoritosInteractorListener.retornaFavoritosAll(listVenue);
    }

    public interface FavoritosInteractorListener {
        void retornaFavoritosAll(List<Venue> venueList);
    }
}