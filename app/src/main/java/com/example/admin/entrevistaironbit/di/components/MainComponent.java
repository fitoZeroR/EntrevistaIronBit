package com.example.admin.entrevistaironbit.di.components;

import com.example.admin.entrevistaironbit.Aplicacion;
import com.example.admin.entrevistaironbit.di.modules.MainModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(Aplicacion app);

    //Exposed to sub-graphs.
    Aplicacion app();
}