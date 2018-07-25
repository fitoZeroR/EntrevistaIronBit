package com.example.admin.entrevistaironbit.cliente;

import com.example.admin.entrevistaironbit.modelo.Lugar;

import io.reactivex.Observable;

public interface Servicio {
    Observable<Lugar> consultaLugares();
}