package com.example.admin.entrevistaironbit.view.activity;

import android.Manifest;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.admin.entrevistaironbit.R;
import com.example.admin.entrevistaironbit.modelo.Lugar;
import com.example.admin.entrevistaironbit.view.ToolBarVC;
import com.example.admin.entrevistaironbit.view.fragment.FavoritosFragment;
import com.example.admin.entrevistaironbit.view.fragment.PrincipalFragment;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.example.admin.entrevistaironbit.utilidades.Tools.isConnectionNetwork;
import static com.example.admin.entrevistaironbit.utilidades.Tools.mensajeInformativo;

public class MainActivity extends ToolBarVC {
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    private Fragment selectedFragment;
    private PrincipalFragment principalFragment;
    private FavoritosFragment favoritosFragment;

    private List<Lugar> listaLugares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isConnectionNetwork(this)) {
            requestForSpecificPermission();

            mainPresenter.disparaServicioLugares();
        } else {
            mensajeInformativo(this, getString(R.string.msg_no_conexion_internet));
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    private void createMenuBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener ((item) -> {
            switch (item.getItemId()) {
                case R.id.menu_principal_item:
                    selectedFragment = PrincipalFragment.newInstance(listaLugares);
                    setTitle(getString(R.string.txt_pincipal));
                    break;
                case R.id.menu_favoritos_item:
                    selectedFragment = FavoritosFragment.newInstance();
                    setTitle(getString(R.string.txt_pincipal));
                    break;
            }
            addFragment(selectedFragment);
            return true;
        });
    }

    private void requestForSpecificPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE)
                .subscribe(granted -> {
                    if (granted) {
                        // The permission has been granted//
                    } else {
                        // The permission has been denied//
                    }
                });
    }

    @Override
    public void despliegaLugares(Lugar registroList) {
        listaLugares = new ArrayList<>();
        listaLugares.add(registroList);
        addFragment(PrincipalFragment.initInstance(listaLugares));
    }
}