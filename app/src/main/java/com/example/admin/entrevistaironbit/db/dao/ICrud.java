package com.example.admin.entrevistaironbit.db.dao;

import java.util.List;

public interface ICrud {
    void create(Object item);
    void delete(String idVenue);
    List<?> findAll();
}