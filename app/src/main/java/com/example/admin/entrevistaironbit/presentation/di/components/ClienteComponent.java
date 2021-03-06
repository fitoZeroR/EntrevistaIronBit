package com.example.admin.entrevistaironbit.presentation.di.components;

import com.example.admin.entrevistaironbit.presentation.di.PerActivity;
import com.example.admin.entrevistaironbit.presentation.di.modules.ActivityModule;
import com.example.admin.entrevistaironbit.presentation.di.modules.ClienteModule;
import com.example.admin.entrevistaironbit.presentation.view.activity.ToolBarActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = MainComponent.class, modules = {ActivityModule.class, ClienteModule.class})
public interface ClienteComponent extends ActivityComponent {
    void inject(ToolBarActivity toolBarActivity);
}