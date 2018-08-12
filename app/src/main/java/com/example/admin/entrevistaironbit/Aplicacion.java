package com.example.admin.entrevistaironbit;

import android.app.Application;

import com.example.admin.entrevistaironbit.presentation.di.components.DaggerMainComponent;
import com.example.admin.entrevistaironbit.presentation.di.components.MainComponent;
import com.example.admin.entrevistaironbit.presentation.di.modules.MainModule;
import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

public class Aplicacion extends Application {
    @Inject
    LogAdapter logAdapter;

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeInjector();

        Logger.addLogAdapter(logAdapter);
    }

    private void initializeInjector() {
        mainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build();
        mainComponent.inject(this);
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}