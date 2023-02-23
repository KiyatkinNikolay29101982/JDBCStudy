package db;

import util.CreateConnection;

import java.sql.*;

public class PrepareMain {
    public static void main(String[] args) {

        try(Connection connection = CreateConnection.buildConnection()) {
            String sql = "INSERT INTO phonebook (last_name, phone ) VALUES(?, ? )";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "Ivaschenko");
            preparedStatement.setInt(2, 4555555);
            preparedStatement.executeUpdate();
            System.out.println("insert complete");

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()){
                System.out.println("id of Ivaschenko = " + resultSet.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
