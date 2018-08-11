package com.example.admin.entrevistaironbit.data.cliente;

import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Lugar;
import com.example.admin.entrevistaironbit.data.retrofit.RetrofitCliente;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.admin.entrevistaironbit.presentation.utilidades.Constantes.*;


public class Cliente extends RetrofitCliente implements Servicio {
    @Override
    public Observable<Lugar> consultaLugares(String latLong, String fecha) {
        return getConsumoService().obtieneLugares(fecha, LOCALIDAD, CLIENT_ID, CLIENT_SECRET, latLong)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}