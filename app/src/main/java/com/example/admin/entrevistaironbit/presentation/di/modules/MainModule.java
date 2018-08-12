package com.example.admin.entrevistaironbit.presentation.di.modules;

import com.example.admin.entrevistaironbit.Aplicacion;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.PrettyFormatStrategy;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private final Aplicacion aplicacion;

    public MainModule(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }

    @Provides
    @Singleton
    Aplicacion provideImeiApplicationContext() {
        return this.aplicacion;
    }

    @Provides
    @Singleton
    LogAdapter provideLogAdapterConfig() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .methodOffset(3)
                .tag("Services")
                .build();
        return new AndroidLogAdapter(formatStrategy);
    }
}