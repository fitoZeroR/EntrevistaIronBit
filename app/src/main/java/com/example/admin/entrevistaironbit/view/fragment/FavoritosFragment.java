package com.example.admin.entrevistaironbit.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.entrevistaironbit.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment {


    private static FavoritosFragment instancia;

    public static FavoritosFragment newInstance() {
        if (instancia == null) {
            instancia = new FavoritosFragment();
        }
        return instancia;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_principal, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

}
