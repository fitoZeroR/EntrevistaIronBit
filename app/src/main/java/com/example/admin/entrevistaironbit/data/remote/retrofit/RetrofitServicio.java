package com.example.admin.entrevistaironbit.data.remote.retrofit;


import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Lugar;
import com.example.admin.entrevistaironbit.presentation.utilidades.Constantes;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitServicio {
    @GET(Constantes.Endpoint.URL_LUGAR)
    Observable<Lugar> obtieneLugares(@Query("v") String v, @Query("locale") String locale, @Query("client_id") String clientId, @Query("client_secret") String client_secret,
                                     @Query("ll") String ll);
}