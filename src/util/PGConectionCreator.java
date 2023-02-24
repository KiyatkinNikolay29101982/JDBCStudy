package util;

import org.postgresql.ds.PGConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class PGConectionCreator {
    private PGConnectionPoolDataSource dataSource;

    public static Connection getConnection() throws SQLException {
            Connection connection = PGDataSourseFactory.createPGDataSource().getConnection();


        return connection;
    }
}
