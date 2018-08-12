package com.example.admin.entrevistaironbit.domain.interactor;

import com.example.admin.entrevistaironbit.data.local.cliente.ServicioDB;
import com.example.admin.entrevistaironbit.domain.modelo.modeloDB.Favorito;

import javax.inject.Inject;

import io.reactivex.Observable;

public class FavoritosInteractor {
    private final ServicioDB servicioDB;

    @Inject
    public FavoritosInteractor(ServicioDB servicioDB) {
        this.servicioDB = servicioDB;
    }

    public Observable<Favorito> recuperaFavoritosALL() {
        return servicioDB.recuperaFavoritosTodos();
    }
}