package dao;

import entity.Abonent;

import exception.DaoException;
import util.CreateConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AbobnentDaoImpl implements AbonentDao{
    private static final AbobnentDaoImpl INSTANCE = new AbobnentDaoImpl();

    public static AbobnentDaoImpl getInstance(){
        return INSTANCE;
    }

    private static final String  SQL_DELETE = "DELETE FROM phonebook where id_phonebook = ?";
    private static final String  SQL_SAVE = "INSERT INTO phonebook(last_name, phone) VALUES (?, ?);";
    private static final String  SQL_UPDATE = "UPDATE phonebook SET last_name = ?, phone = ? WHERE id_phonebook = ?;";
    private static final String  SQL_FIND_ALL= "SELECT * FROM phonebook ; ";
    private static final String  SQL_FIND_BY_ID = "SELECT id_phonebook, last_name, phone FROM phonebook WHERE id_phonebook = ?;";
    private static final String SQL_FIND_BY_Name = "SELECT id_phonebook, last_name, phone FROM phonebook WHERE last_name LIKE ? ";

    @Override
    public List<Abonent> findAll() throws DaoException {
        List<Abonent> abonents = null;
        try(Connection connection = CreateConnection.buildConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
        ) {
            ResultSet resultSet = statement.executeQuery();
            abonents = new ArrayList<>();
            while(resultSet.next()){
                abonents.add(buildAbonent(resultSet));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("command abonents was complete");
        return abonents;
    }

    @Override
    public Optional<Abonent> findById(Long id) throws DaoException {
        try(Connection connection = CreateConnection.buildConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            ) {
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            Abonent abobnent = null;
            while (resultSet.next()) {

                abobnent = buildAbonent(resultSet);
            }
            return  Optional.ofNullable(abobnent);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Abonent abonent) throws DaoException {
        try(Connection connection = CreateConnection.buildConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
            ) {
            statement.setString(1,abonent.getName());
            statement.setInt(2, abonent.getPhone());
            statement.setLong(3 ,abonent.getId_phonebook());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Abonent save(Abonent abonent) throws DaoException {
        try(Connection connection = CreateConnection.buildConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1,abonent.getName());
            statement.setInt(2,abonent.getPhone());
            statement.executeUpdate();
            ResultSet generateKeys = statement.getGeneratedKeys();

            if(generateKeys.next()){
                abonent.setId_phonebook(generateKeys.getLong("id_phonebook"));
            }

            return abonent;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try(Connection connection = CreateConnection.buildConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            ) {
            statement.setLong(1,id);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Abonent> findByName(String name) throws DaoException {
        List<Abonent> abonents = new ArrayList<>();
        try(Connection connection = CreateConnection.buildConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_Name);
        ) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                abonents.add(buildAbonent(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return abonents;
    }



    private Abonent buildAbonent (ResultSet resultSet) throws SQLException {
        return new Abonent(
                resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getInt(3)
        );

    }
}
