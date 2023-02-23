package dao;

import entity.Location;
import exception.DaoException;
import util.CreateConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class LocationDaoImpl implements LocationDao {
    private static final LocationDaoImpl INSTANCE = new LocationDaoImpl();

    public static LocationDaoImpl getInstance(){

         return INSTANCE;
    }

    private static final String  SQL_DELETE = "DELETE FROM location where id_location = ? ";
    private static final String  SQL_SAVE = "INSERT INTO location (city) VALUES (?);";
    private static final String  SQL_UPDATE = "UPDATE location SET city = ? WHERE id_location = ?;";
    private static final String  SQL_FIND_ALL= "SELECT * FROM location ;";
    private static final String  SQL_FIND_BY_ID = "SELECT id_location, city FROM location WHERE id_location = ?;";

    @Override
    public List<Location> findAll() throws DaoException {
        List<Location> locations = new ArrayList<>();
        try(Connection connection = CreateConnection.buildConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
        ){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                locations.add(buildLocation(resultSet));
            }

        } catch (SQLException e) {
            System.out.println("method findAll() of LocationDaoImpl was not complete");
        }
        System.out.println("method findAll() of LocationDaoImpl complete");
        return locations;

    }

    @Override
    public Optional<Location> findById(Long id) throws DaoException {
        Location location = null;
        try(Connection connection = CreateConnection.buildConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                location = buildLocation(resultSet);
            }


        } catch (SQLException e) {
            System.out.println("Error in findById of LocationDaoImpl");
        }
        System.out.println("findById of LocationDaoImpl complete");
        return Optional.ofNullable(location) ;
    }

    @Override
    public void update(Location location) throws DaoException {
        try(Connection connection = CreateConnection.buildConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
        ) {
            statement.setString(1,location.getCity());
            statement.setLong(2, location.getId_location() );
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error in update of LocationDaoImpl");
        }
        System.out.println(" update of LocationDaoImpl complete");
    }

    @Override
    public Location save(Location location) throws DaoException {
        try(Connection connection = CreateConnection.buildConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, location.getCity());
            statement.executeUpdate();
            ResultSet generetedKey = statement.getGeneratedKeys();
            while(generetedKey.next()){
                location.setId_location(generetedKey.getLong(1));
            }

        } catch (SQLException e) {
            System.out.println("Error in save() of LocationDaoImpl" );
        }
        System.out.println("save() of LocationDaoImpl complete");
        return location;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try(Connection connection = CreateConnection.buildConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
        ){
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Location buildLocation(ResultSet resultSet){
        Location location = null;
        try {
            location = new Location(resultSet.getLong(1),
                    resultSet.getString(2));
        } catch (SQLException e){
            System.out.println("object Location was not build");

        }
        return location;
    }
}
