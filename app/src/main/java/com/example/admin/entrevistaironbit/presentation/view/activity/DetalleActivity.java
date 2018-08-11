package com.example.admin.entrevistaironbit.presentation.view.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.admin.entrevistaironbit.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

import static com.example.admin.entrevistaironbit.presentation.utilidades.Constantes.*;

public class DetalleActivity extends ToolBarActivity implements OnMapReadyCallback {
    private String nombre, gps;
    private double latitud, longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getString(R.string.txt_detalle));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_arrow);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            gps = bundle.getString(BUNDLE_GPS);
            latitud = bundle.getDouble(BUNDLE_LATITUD);
            longitud = bundle.getDouble(BUNDLE_LONGITUD);
            nombre = bundle.getString(BUNDLE_NOMBRE);
        }

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return true;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_detalle;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        String[] coordenadaFija = gps.split(",");

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(Double.parseDouble(coordenadaFija[0]), Double.parseDouble(coordenadaFija[1])))
                .title(getString(R.string.txt_origen)));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitud, longitud))
                .title(nombre));

        LatLngBounds.Builder builder = LatLngBounds.builder();
        builder.include(new LatLng(Double.parseDouble(coordenadaFija[0]), Double.parseDouble(coordenadaFija[1])));
        builder.include(new LatLng(latitud,longitud));

        LatLngBounds bounds = builder.build();
        CameraUpdate zoomCamara = CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), 17);
        googleMap.animateCamera(zoomCamara);
    }
}