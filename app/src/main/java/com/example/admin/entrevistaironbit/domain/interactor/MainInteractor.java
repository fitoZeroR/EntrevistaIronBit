package com.example.admin.entrevistaironbit.domain.interactor;

import com.example.admin.entrevistaironbit.data.cliente.Servicio;
import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Lugar;
import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Venue;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.example.admin.entrevistaironbit.presentation.utilidades.Tools.distanciaCoord;

public class MainInteractor {
    private final Servicio servicio;
    private MainInteractorListener mainInteractorListener;

    @Inject
    public MainInteractor(Servicio servicio) {
        this.servicio = servicio;
    }

    public void setMainInteractorListener(MainInteractorListener mainInteractorListener) {
        this.mainInteractorListener = mainInteractorListener;
    }

    public Observable<Lugar> consultaListaLugares(String latLong, String fecha) {
        return servicio.consultaLugares(latLong, fecha);
    }

    public void ordenaRegistros(List<Venue> venueList, String latLon) {
        for (int x = 0; x < venueList.size(); x++) {
            String[] coordenadaFija = latLon.split(",");
            venueList.get(x).setDistancia(Double.parseDouble(new DecimalFormat("#.00").format(1000 * distanciaCoord(Double.parseDouble(coordenadaFija[0]), Double.parseDouble(coordenadaFija[1]),
                    venueList.get(x).getLocation().getLat(), venueList.get(x).getLocation().getLng())).replace(",", ".")));
        }
        Collections.sort(venueList);
        mainInteractorListener.onOrderRegistros(venueList);
    }

    public interface MainInteractorListener {
        void onOrderRegistros(List<Venue> venueList);
    }
}