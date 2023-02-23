package dao;

import entity.Abonent;
import exception.DaoException;

import java.util.List;
import java.util.Optional;

 interface AbonentDao extends BaseDao<Long, Abonent>{
    @Override
    List<Abonent> findAll() throws DaoException;

    @Override
    Optional<Abonent> findById(Long id) throws DaoException;

    @Override
    void update(Abonent abonent) throws DaoException;
    @Override
    Abonent save(Abonent abonent) throws DaoException;

    @Override
    boolean delete(Long ig) throws DaoException;

    List<Abonent> findByName(String name) throws DaoException;
}
