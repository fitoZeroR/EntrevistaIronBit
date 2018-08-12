package com.example.admin.entrevistaironbit.data.local.cliente;

import com.example.admin.entrevistaironbit.data.local.db.dao.ICrud;
import com.example.admin.entrevistaironbit.domain.modelo.modeloDB.Favorito;
import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Venue;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observable;

public class ClienteDB implements ServicioDB {
    private final ICrud iCrud;

    public ClienteDB(ICrud iCrud) {
        this.iCrud = iCrud;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Observable<Favorito> recuperaFavoritosTodos() {
        return Observable.fromIterable((List<Favorito>) iCrud.findAll());
    }

    @Override
    public void insertaFavorito(Venue venue) {
        iCrud.create(new Favorito(venue.getId(), new Gson().toJson(venue, Venue.class)));
    }

    @Override
    public void borraFavorito(Venue venue) {
        iCrud.delete(venue.getId());
    }
}