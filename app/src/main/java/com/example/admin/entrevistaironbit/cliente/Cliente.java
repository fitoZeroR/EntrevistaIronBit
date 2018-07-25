package com.example.admin.entrevistaironbit.cliente;

import com.example.admin.entrevistaironbit.modelo.Lugar;
import com.example.admin.entrevistaironbit.retrofit.RetrofitCliente;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.admin.entrevistaironbit.utilidades.Constantes.*;


public class Cliente extends RetrofitCliente implements Servicio {
    @Override
    public Observable<Lugar> consultaLugares() {
        return getConsumoService().obtieneLugares(FECHA, LOCALIDAD, CLIENT_ID, CLIENT_SECRET, LL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}