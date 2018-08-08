package com.example.admin.entrevistaironbit.di.components;

import com.example.admin.entrevistaironbit.di.PerActivity;
import com.example.admin.entrevistaironbit.di.modules.ActivityModule;
import com.example.admin.entrevistaironbit.di.modules.ClienteModule;
import com.example.admin.entrevistaironbit.view.activity.ToolBarActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = MainComponent.class, modules = {ActivityModule.class, ClienteModule.class})
public interface ClienteComponent extends ActivityComponent {
    void inject(ToolBarActivity toolBarActivity);
}