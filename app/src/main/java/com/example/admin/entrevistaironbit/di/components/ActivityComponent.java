package com.example.admin.entrevistaironbit.di.components;

import android.app.Activity;

import com.example.admin.entrevistaironbit.di.PerActivity;
import com.example.admin.entrevistaironbit.di.modules.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = MainComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    //Exposed to sub-graphs.
    Activity activity();
}