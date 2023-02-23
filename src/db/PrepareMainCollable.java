package db;

import util.CreateConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class PrepareMainCollable {

    public static void main(String[] args) {
        try(Connection connection = CreateConnection.buildConnection()) {
            connection.setAutoCommit(true);
            String sql = "{call display_msg(?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.registerOutParameter(1, Types.VARCHAR);
            statement.setString(1, "Hello public");
            statement.execute();
            String rezult = statement.getString(1);
            System.out.println(rezult);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
