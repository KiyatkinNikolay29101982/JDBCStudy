package dao;

import entity.Location;
import exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface LocationDao extends BaseDao<Long, Location>{
    @Override
    List<Location> findAll() throws DaoException;

    @Override
    Optional<Location> findById(Long id) throws DaoException;

    @Override
    void update(Location location) throws DaoException;

    @Override
    Location save(Location location) throws DaoException;

    @Override
    boolean delete(Long id) throws DaoException;
}
