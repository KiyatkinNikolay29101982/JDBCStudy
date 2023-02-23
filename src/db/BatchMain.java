package db;

import util.CreateConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class BatchMain {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = CreateConnection.buildConnection();
            Statement batchStatement = connection.createStatement();
            batchStatement.addBatch("INSERT INTO phonebook(last_name, phone) VALUES ('Faizulin', 1231231) ");
            batchStatement.addBatch("INSERT INTO phonebook(last_name, phone) VALUES ('Anikina', 3211231) ");
            batchStatement.addBatch("INSERT INTO phonebook(last_name, phone) VALUES ('Surkov', 4444444) ");

            int[] updates = batchStatement.executeBatch();
            System.out.println(Arrays.toString(updates));

            connection.setAutoCommit(false);
        } catch (SQLException e) {
            try {
                if (connection != null) {

                    connection.rollback();
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error");
            }
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }

            } catch (SQLException e) {
                System.out.println("SetCommit = true");
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error close()");
            }

        }
    }

}

