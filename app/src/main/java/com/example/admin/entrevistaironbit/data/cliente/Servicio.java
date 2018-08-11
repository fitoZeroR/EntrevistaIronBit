package com.example.admin.entrevistaironbit.data.cliente;

import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Lugar;

import io.reactivex.Observable;

public interface Servicio {
    Observable<Lugar> consultaLugares(String latLong, String fecha);
}