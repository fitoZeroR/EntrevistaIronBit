package com.example.admin.entrevistaironbit.presentation.di.components;

import com.example.admin.entrevistaironbit.Aplicacion;
import com.example.admin.entrevistaironbit.presentation.di.modules.MainModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(Aplicacion app);

    //Exposed to sub-graphs.
    Aplicacion app();
}