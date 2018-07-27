package com.example.admin.entrevistaironbit.interactor;

import android.content.Context;

import com.example.admin.entrevistaironbit.db.dao.DAOFavorito;
import com.example.admin.entrevistaironbit.modelo.modeloDB.Favorito;
import com.example.admin.entrevistaironbit.modelo.modeloWS.Venue;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FavoritosInteractor {
    private FavoritosInteractorListener favoritosInteractorListener;
    
    @Inject
    public FavoritosInteractor() {}

    public void setFavoritosInteractorListener(FavoritosInteractorListener favoritosInteractorListener) {
        this.favoritosInteractorListener = favoritosInteractorListener;
    }

    @SuppressWarnings("unchecked")
    public void recuperaFavoritosALL(Context context) {
        List<Favorito> listFavoritos = (List<Favorito>) new DAOFavorito(context).findAll();
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