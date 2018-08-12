package com.example.admin.entrevistaironbit.presentation.di.modules;

import android.support.v4.app.Fragment;

import com.example.admin.entrevistaironbit.data.local.cliente.ClienteDB;
import com.example.admin.entrevistaironbit.data.local.cliente.ServicioDB;
import com.example.admin.entrevistaironbit.data.local.db.dao.DAOFavorito;
import com.example.admin.entrevistaironbit.data.local.db.dao.ICrud;
import com.example.admin.entrevistaironbit.presentation.di.PerFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    private final Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerFragment
    Fragment provideFragment() {
        return fragment;
    }

    @Provides
    @PerFragment
    ICrud provideDAOFavorito() {
        return new DAOFavorito(provideFragment().getContext());
    }

    @Provides
    @PerFragment
    ServicioDB provideDB() {
        return new ClienteDB(provideDAOFavorito());
    }
}