package com.example.admin.entrevistaironbit.cliente;

import com.example.admin.entrevistaironbit.modelo.modeloWS.Lugar;

import io.reactivex.Observable;

public interface Servicio {
    Observable<Lugar> consultaLugares(String latLong, String fecha);
}