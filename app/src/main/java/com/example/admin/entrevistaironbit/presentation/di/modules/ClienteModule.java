package com.example.admin.entrevistaironbit.presentation.di.modules;

import com.example.admin.entrevistaironbit.data.remote.cliente.ClienteWS;
import com.example.admin.entrevistaironbit.data.remote.cliente.ServicioWS;
import com.example.admin.entrevistaironbit.presentation.di.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ClienteModule {
    public ClienteModule() {}

    @Provides
    @PerActivity
    ServicioWS provideCliente() {
        return new ClienteWS();
    }
}