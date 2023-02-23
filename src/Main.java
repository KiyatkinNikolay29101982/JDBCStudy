import util.CreateConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {


            Connection connection = CreateConnection.buildConnection();

            System.out.println("Connection complete");




    }
}