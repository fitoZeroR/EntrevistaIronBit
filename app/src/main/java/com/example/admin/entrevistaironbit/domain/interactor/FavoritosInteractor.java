package com.example.admin.entrevistaironbit.domain.interactor;

import com.example.admin.entrevistaironbit.data.DataDBListener;
import com.example.admin.entrevistaironbit.data.local.cliente.ServicioDB;
import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Venue;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FavoritosInteractor {
    private final ServicioDB servicioDB;
    private DataDBListener dataDBListener;

    @Inject
    public FavoritosInteractor(ServicioDB servicioDB) {
        this.servicioDB = servicioDB;
    }

    public void setLugaresInteractorListener(DataDBListener dataDBListener) {
        this.dataDBListener = dataDBListener;
    }

    public void recuperaFavoritosALL() {
        List<Venue> listVenue = new ArrayList<>();
        servicioDB.recuperaFavoritosTodos().subscribe(favorito -> listVenue.add(new Gson().fromJson(favorito.getRegistroJson(), Venue.class)),
                throwable -> {},
                () -> dataDBListener.retornaFavoritosGPS(listVenue));
    }
}