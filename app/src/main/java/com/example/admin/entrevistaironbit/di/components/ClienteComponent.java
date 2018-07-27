package com.example.admin.entrevistaironbit.di.components;

import com.example.admin.entrevistaironbit.di.PerActivity;
import com.example.admin.entrevistaironbit.di.modules.ActivityModule;
import com.example.admin.entrevistaironbit.di.modules.ClienteModule;
import com.example.admin.entrevistaironbit.view.activity.ToolBarActivity;
import com.example.admin.entrevistaironbit.view.fragment.FavoritosFragment;
import com.example.admin.entrevistaironbit.view.fragment.LugaresFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = MainComponent.class, modules = {ActivityModule.class, ClienteModule.class})
public interface ClienteComponent {
    void inject(ToolBarActivity toolBarActivity);
    void inject(LugaresFragment lugaresFragment);
    void inject(FavoritosFragment favoritosFragment);
}