package com.example.repository;

import java.util.List;

public interface Repository<T>{
    List<T> findAll() throws Exception;
    
    T getById(Integer id) throws Exception;

    void save(T t) throws Exception;
    
    void delete(Integer id) throws Exception;
}
