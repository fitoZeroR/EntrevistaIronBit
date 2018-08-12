package com.example.admin.entrevistaironbit.data.local.cliente;

import com.example.admin.entrevistaironbit.domain.modelo.modeloDB.Favorito;
import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Venue;

import io.reactivex.Observable;

public interface ServicioDB {
    Observable<Favorito> recuperaFavoritosTodos();
    void insertaFavorito(Venue venue);
    void borraFavorito(Venue venue);
}