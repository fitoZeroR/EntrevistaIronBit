
package com.example.admin.entrevistaironbit.modelo.modeloWS;

import android.support.annotation.NonNull;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Venue implements Comparable<Venue> {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("referralId")
    @Expose
    private String referralId;
    @SerializedName("hasPerk")
    @Expose
    private Boolean hasPerk;
    @SerializedName("venuePage")
    @Expose
    private VenuePage venuePage;
    private double distancia;
    private boolean favorito;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getReferralId() {
        return referralId;
    }

    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }

    public Boolean getHasPerk() {
        return hasPerk;
    }

    public void setHasPerk(Boolean hasPerk) {
        this.hasPerk = hasPerk;
    }

    public VenuePage getVenuePage() {
        return venuePage;
    }

    public void setVenuePage(VenuePage venuePage) {
        this.venuePage = venuePage;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    @Override
    public int compareTo(@NonNull Venue venue) {
        Double distanciaThis = this.distancia;
        Double distanciaAComparar = venue.getDistancia();
        return distanciaThis.compareTo(distanciaAComparar);
    }
}