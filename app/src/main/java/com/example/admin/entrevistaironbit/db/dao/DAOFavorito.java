package com.example.admin.entrevistaironbit.db.dao;

import android.content.Context;

import com.example.admin.entrevistaironbit.db.DatabaseHelper;
import com.example.admin.entrevistaironbit.db.DatabaseManager;
import com.example.admin.entrevistaironbit.modelo.modeloDB.Favorito;
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
    public int create(Object item) {
        int index = -1;

        Favorito favorito = (Favorito) item;
        try {
            index = helper.getFavoritoDao().create(favorito);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int delete(String idVenue) {
        int index = -1;

        try {
            DeleteBuilder<Favorito, Integer> deleteBuilder = helper.getFavoritoDao().deleteBuilder();
            deleteBuilder.where().eq("idVenue", idVenue);
            index = deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
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