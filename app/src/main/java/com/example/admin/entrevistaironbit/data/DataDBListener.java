package com.example.admin.entrevistaironbit.data;

import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Venue;

import java.util.List;

public interface DataDBListener {
    void retornaFavoritosGPS(List<Venue> venueList);
}