package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {

    public static Connection getConnection() {
        try {
            Properties properties = loadProperties();
            String url = properties.getProperty("url");
            return DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = DatabaseConnectionManager.class.getClassLoader().getResourceAsStream(
                "database.properties")) {
            if (inputStream == null) {
                throw new IllegalArgumentException("database.properties not found in the classpath");
            }
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new DataBaseException(e.getMessage());
        }
    }
}
