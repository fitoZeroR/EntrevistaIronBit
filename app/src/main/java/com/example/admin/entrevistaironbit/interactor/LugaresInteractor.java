package com.example.admin.entrevistaironbit.interactor;

import com.example.admin.entrevistaironbit.db.dao.ICrud;
import com.example.admin.entrevistaironbit.modelo.modeloDB.Favorito;
import com.example.admin.entrevistaironbit.modelo.modeloWS.Venue;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class LugaresInteractor {
    private final ICrud iCrud;
    private LugaresInteractorListener lugaresInteractorListener;

    @Inject
    public LugaresInteractor(ICrud iCrud) {
        this.iCrud = iCrud;
    }

    public void setLugaresInteractorListener(LugaresInteractorListener lugaresInteractorListener) {
        this.lugaresInteractorListener = lugaresInteractorListener;
    }

    @SuppressWarnings("unchecked")
    public void recuperaFavoritosGPS(List<Venue> venueList) {
        List<Favorito> listFavoritos = (List<Favorito>) iCrud.findAll();
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

    public void creaFavoritoDB(Venue venue) {
        venue.setFavorito(true);
        iCrud.create(new Favorito(venue.getId(), new Gson().toJson(venue, Venue.class)));
    }

    public void eliminaFavoritoDB(Venue venue) {
        venue.setFavorito(false);
        iCrud.delete(venue.getId());
    }

    public interface LugaresInteractorListener {
        void retornaFavoritosGPS(List<Venue> venueList);
    }
}