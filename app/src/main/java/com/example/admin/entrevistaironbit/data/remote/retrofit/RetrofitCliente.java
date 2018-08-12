package com.example.admin.entrevistaironbit.data.remote.retrofit;

import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Lugar;
import com.example.admin.entrevistaironbit.presentation.utilidades.Constantes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitCliente {
    private RetrofitServicio retrofitServicio;

    protected RetrofitCliente() {
        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit retrofit = retrofitBuilder();
        retrofitServicio = retrofit.create(getConsumoServiceClass());
    }

    private Retrofit retrofitBuilder() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();

        return new Retrofit.Builder()
                .baseUrl(Constantes.URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(getConsumoDeserializer()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private Class<RetrofitServicio> getConsumoServiceClass() {
        return RetrofitServicio.class;
    }

    private Gson getConsumoDeserializer() {
        return new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(Lugar.class, (JsonDeserializer<Lugar>) (json, typeOfT, context) -> new Gson().fromJson(json, Lugar.class))
                .create();
    }

    protected RetrofitServicio getConsumoService() {
        return retrofitServicio;
    }
}