package com.example.admin.entrevistaironbit.di.components;

import com.example.admin.entrevistaironbit.di.PerActivity;
import com.example.admin.entrevistaironbit.di.modules.ActivityModule;
import com.example.admin.entrevistaironbit.di.modules.ClienteModule;
import com.example.admin.entrevistaironbit.view.ToolBarVC;

import dagger.Component;

@PerActivity
@Component(dependencies = MainComponent.class, modules = {ActivityModule.class, ClienteModule.class})
public interface ClienteComponent {
    void inject(ToolBarVC toolBarVC);
}