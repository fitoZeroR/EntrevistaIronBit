package com.example.admin.entrevistaironbit.data.remote.cliente;

import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Lugar;

import io.reactivex.Observable;

public interface ServicioWS {
    Observable<Lugar> consultaLugares(String latLong, String fecha);
}