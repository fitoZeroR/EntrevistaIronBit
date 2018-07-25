package com.example.admin.entrevistaironbit.interactor;

import com.example.admin.entrevistaironbit.cliente.Servicio;
import com.example.admin.entrevistaironbit.modelo.Lugar;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MainInteractor {
    private Servicio servicio;

    @Inject
    public MainInteractor(Servicio servicio) {
        this.servicio = servicio;
    }

    public Observable<Lugar> consultaListaLugares() {
        return servicio.consultaLugares();
    }
}