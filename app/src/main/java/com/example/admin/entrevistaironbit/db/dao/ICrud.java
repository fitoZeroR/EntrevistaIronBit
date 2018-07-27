package com.example.admin.entrevistaironbit.db.dao;

import java.util.List;

interface ICrud {
    int create(Object item);
    int delete(String idVenue);
    List<?> findAll();
}