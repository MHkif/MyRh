package com.example.myrh.service;

import org.springframework.data.domain.Page;

public interface IService<Entity, pk, Req, Res>  {
    Res getById(pk id);
    Page<Res> getAll(int page, int size);
    Res create(Req request);
    Res update(int id, Res request);
    void deleteById(int id);

}
