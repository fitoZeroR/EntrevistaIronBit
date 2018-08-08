package com.example.admin.entrevistaironbit.di.components;

import android.support.v4.app.Fragment;

import com.example.admin.entrevistaironbit.di.PerFragment;
import com.example.admin.entrevistaironbit.di.modules.FragmentModule;
import com.example.admin.entrevistaironbit.view.fragment.FavoritosFragment;
import com.example.admin.entrevistaironbit.view.fragment.LugaresFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = MainComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(FavoritosFragment favoritosFragment);
    void inject(LugaresFragment lugaresFragment);

    Fragment fragment();
}