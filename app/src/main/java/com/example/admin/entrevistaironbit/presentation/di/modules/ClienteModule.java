package com.example.admin.entrevistaironbit.presentation.di.modules;

import com.example.admin.entrevistaironbit.data.cliente.Cliente;
import com.example.admin.entrevistaironbit.data.cliente.Servicio;
import com.example.admin.entrevistaironbit.presentation.di.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ClienteModule {
    public ClienteModule() {}

    @Provides
    @PerActivity
    Servicio provideCliente() {
        return new Cliente();
    }
}