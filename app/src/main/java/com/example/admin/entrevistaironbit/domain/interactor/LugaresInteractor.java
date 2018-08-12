package com.example.admin.entrevistaironbit.domain.interactor;

import com.example.admin.entrevistaironbit.data.local.cliente.ServicioDB;
import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Venue;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

public class LugaresInteractor {
    private final ServicioDB servicioDB;
    private LugaresInteractorListener lugaresInteractorListener;

    @Inject
    public LugaresInteractor(ServicioDB servicioDB) {
        this.servicioDB = servicioDB;
    }

    public void setLugaresInteractorListener(LugaresInteractorListener lugaresInteractorListener) {
        this.lugaresInteractorListener = lugaresInteractorListener;
    }

    @SuppressWarnings("unchecked")
    public void recuperaFavoritosGPS(List<Venue> venueList) {
        servicioDB.recuperaFavoritosTodos().subscribe(favorito -> {
                            Venue venue = new Gson().fromJson(favorito.getRegistroJson(), Venue.class);
                            for (int y = 0; y < venueList.size(); y++) {
                                if (venueList.get(y).getId().equals(venue.getId())) {
                                    venueList.get(y).setFavorito(true);
                                }
                            }
                        },
                        throwable -> {},
                        () -> lugaresInteractorListener.retornaFavoritosGPS(venueList));

        /*Observable.just((List<Favorito>) iCrud.findAll())
                .subscribe(favoritos -> {
                            for (int x = 0; x < favoritos.size(); x++) {
                                listVenue.add(new Gson().fromJson(favoritos.get(x).getRegistroJson(), Venue.class));
                                for (int y = 0; y < venueList.size(); y++) {
                                    if (venueList.get(y).getId().equals(listVenue.get(x).getId())) {
                                        venueList.get(y).setFavorito(true);
                                    }
                                }
                            }
                        },
                        throwable -> {},
                        () -> lugaresInteractorListener.retornaFavoritosGPS(venueList));*/
    }

    public void creaFavoritoDB(Venue venue) {
        venue.setFavorito(true);
        servicioDB.insertaFavorito(venue);
    }

    public void eliminaFavoritoDB(Venue venue) {
        venue.setFavorito(false);
        servicioDB.borraFavorito(venue);
    }

    public interface LugaresInteractorListener {
        void retornaFavoritosGPS(List<Venue> venueList);
    }
}