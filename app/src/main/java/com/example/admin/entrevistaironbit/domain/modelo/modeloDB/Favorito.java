package com.example.admin.entrevistaironbit.domain.modelo.modeloDB;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Favorito {
    @DatabaseField(generatedId = true, columnName = "_id")
    private int id;
    @DatabaseField(columnName = "idVenue")
    private String idVenue;
    @DatabaseField(columnName = "registroJson")
    private String registroJson;

    public Favorito() {}

    public Favorito(String idVenue, String registroJson) {
        this.idVenue = idVenue;
        this.registroJson = registroJson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistroJson() {
        return registroJson;
    }

    public void setRegistroJson(String registroJson) {
        this.registroJson = registroJson;
    }

    public String getIdVenue() {
        return idVenue;
    }

    public void setIdVenue(String idVenue) {
        this.idVenue = idVenue;
    }
}