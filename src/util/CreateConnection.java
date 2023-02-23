package util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class CreateConnection {
        private static final Properties PROPERTIES = new Properties();

        private static final String DATABASE_URL;


        static {
            try {
                PROPERTIES.load(new FileReader("resourses\\dataBase.properties"));
                String className = (String) PROPERTIES.get("db.driver");
                Class.forName(className);
                DATABASE_URL = (String) PROPERTIES.get("db.url");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }

        public static Connection buildConnection()  {
            try {
                return DriverManager.getConnection(DATABASE_URL, PROPERTIES);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
}
