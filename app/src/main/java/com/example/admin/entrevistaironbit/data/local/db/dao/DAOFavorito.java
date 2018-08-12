package com.example.admin.entrevistaironbit.data.local.db.dao;

import android.content.Context;

import com.example.admin.entrevistaironbit.data.local.db.DatabaseHelper;
import com.example.admin.entrevistaironbit.data.local.db.DatabaseManager;
import com.example.admin.entrevistaironbit.domain.modelo.modeloDB.Favorito;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.List;

public class DAOFavorito implements ICrud {
    private final DatabaseHelper helper;

    public DAOFavorito(Context context) {
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public void create(Object item) {
        Favorito favorito = (Favorito) item;
        try {
            helper.getFavoritoDao().create(favorito);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(String idVenue) {
        try {
            DeleteBuilder<Favorito, Integer> deleteBuilder = helper.getFavoritoDao().deleteBuilder();
            deleteBuilder.where().eq("idVenue", idVenue);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<?> findAll() {
        List<Favorito> favoritos = null;

        try {
            favoritos =  helper.getFavoritoDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return favoritos;
    }
}