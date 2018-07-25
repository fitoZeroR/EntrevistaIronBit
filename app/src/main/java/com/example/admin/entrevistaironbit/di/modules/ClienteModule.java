package com.example.admin.entrevistaironbit.di.modules;

import com.example.admin.entrevistaironbit.cliente.Cliente;
import com.example.admin.entrevistaironbit.cliente.Servicio;
import com.example.admin.entrevistaironbit.di.PerActivity;

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