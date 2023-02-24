package util;

import org.postgresql.ds.PGConnectionPoolDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PGDataSourseFactory {
    public static PGConnectionPoolDataSource createPGDataSource(){
        PGConnectionPoolDataSource dataSource = null;
        Properties properties = new Properties();

        try(InputStream inputStream = new FileInputStream("resourses\\database.propertirs")){
            properties.load(inputStream);
            dataSource = new PGConnectionPoolDataSource();
            dataSource.setURL(properties.getProperty("db.url"));
            dataSource.setUser(properties.getProperty("user"));
            dataSource.setPassword(properties.getProperty("password"));

        }catch(IOException e){
            throw new RuntimeException();
        }

        return dataSource;
    }
}
