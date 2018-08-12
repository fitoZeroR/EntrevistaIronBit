package com.example.admin.entrevistaironbit.presentation.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.admin.entrevistaironbit.R;
import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Venue;
import com.example.admin.entrevistaironbit.presentation.view.fragment.FavoritosFragment;
import com.example.admin.entrevistaironbit.presentation.view.fragment.LugaresFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;

import static android.location.LocationManager.GPS_PROVIDER;
import static com.example.admin.entrevistaironbit.presentation.utilidades.Tools.*;

public class MainActivity extends ToolBarActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    private Fragment selectedFragment;
    private LugaresFragment lugaresFragment;

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;

    private static boolean banderaInicioServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getString(R.string.txt_pincipal));

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    private void createMenuBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener ((item) -> {
            switch (item.getItemId()) {
                case R.id.menu_principal_item:
                    selectedFragment = lugaresFragment;
                    setTitle(getString(R.string.txt_pincipal));
                    break;
                case R.id.menu_favoritos_item:
                    selectedFragment = FavoritosFragment.newInstance();
                    setTitle(getString(R.string.txt_favoritos));
                    break;
            }
            addFragment(selectedFragment);
            return true;
        });
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("MissingPermission")
    private void requestForSpecificPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if (granted) {
                        // The permission has been granted//
                        if (isConnectionNetwork(this)) {
                            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                            mainPresenter.disparaServicioLugares(mLastLocation.getLatitude()+","+mLastLocation.getLongitude(), parsearFechaCumpleanos());
                        } else {
                            mensajeInformativo(this, getString(R.string.msg_no_conexion_internet), true);
                        }
                    } else {
                        // The permission has been denied//
                        mensajeInformativo(this, getString(R.string.txt_aceptar_permisos), true);
                    }
                });
    }

    @Override
    public void despliegaLugares(List<Venue> venueList) {
        banderaInicioServicio = true;
        createMenuBottomNavigationView();
        addFragment(lugaresFragment = LugaresFragment.initInstance(venueList, mLastLocation.getLatitude()+","+mLastLocation.getLongitude()));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //Conectado correctamente a Google Play Services
        if (!banderaInicioServicio) {
            LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (!Objects.requireNonNull(manager).isProviderEnabled(GPS_PROVIDER)) {
                mensajeInformativo(this, getString(R.string.txt_encender_gps), true);
            } else {
                requestForSpecificPermission();
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        //Se ha interrumpido la conexión con Google Play Services
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //Se ha producido un error que no se puede resolver automáticamente
        //y la conexión con los Google Play Services no se ha establecido.
    }
}