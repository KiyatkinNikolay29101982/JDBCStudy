package dao;

import entity.Entity;
import exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BaseDao <K, T extends Entity>{
    List<T> findAll() throws DaoException;

    Optional<T> findById(K k) throws DaoException;

    void update(T t) throws DaoException;

    T save (T t) throws DaoException;

    boolean delete(K id) throws DaoException;

}
