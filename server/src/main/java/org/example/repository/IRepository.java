package org.example.repository;


import org.example.model.Entity;

public interface IRepository <ID, T extends Entity<ID>>{
    void add(T elem);
    void update(ID id, T elem);
    void delete(ID id);
    T findAfterId(ID id);
    Iterable<T> getAll();
}
