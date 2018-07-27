package com.example.admin.entrevistaironbit.interactor;

import android.content.Context;

import com.example.admin.entrevistaironbit.db.dao.DAOFavorito;
import com.example.admin.entrevistaironbit.modelo.modeloDB.Favorito;
import com.example.admin.entrevistaironbit.modelo.modeloWS.Venue;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class LugaresInteractor {
    private LugaresInteractorListener lugaresInteractorListener;

    @Inject
    public LugaresInteractor() {}

    public void setLugaresInteractorListener(LugaresInteractorListener lugaresInteractorListener) {
        this.lugaresInteractorListener = lugaresInteractorListener;
    }

    @SuppressWarnings("unchecked")
    public void recuperaFavoritosGPS(List<Venue> venueList, Context context) {
        List<Favorito> listFavoritos = (List<Favorito>) new DAOFavorito(context).findAll();
        if (listFavoritos.size() != 0) {
            List<Venue> listVenue = new ArrayList<>();
            for (int x = 0; x < listFavoritos.size(); x++) {
                listVenue.add(new Gson().fromJson(listFavoritos.get(x).getRegistroJson(), Venue.class));
                for (int y = 0; y < venueList.size(); y++) {
                    if (venueList.get(y).getId().equals(listVenue.get(x).getId())) {
                        venueList.get(y).setFavorito(true);
                    }
                }
            }
        }
        lugaresInteractorListener.retornaFavoritosGPS(venueList);
    }

    public void creaFavoritoDB(Venue venue, Context context) {
        venue.setFavorito(true);
        new DAOFavorito(context).create(new Favorito(venue.getId(), new Gson().toJson(venue, Venue.class)));
    }

    public void eliminaFavoritoDB(Venue venue, Context context) {
        venue.setFavorito(false);
        new DAOFavorito(context).delete(venue.getId());
    }

    public interface LugaresInteractorListener {
        void retornaFavoritosGPS(List<Venue> venueList);
    }
}