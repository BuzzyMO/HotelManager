package com.example.hotelmanager.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionProviderImpl implements ConnectionProvider{

    private static final Logger logger = LogManager.getLogger(ConnectionProviderImpl.class.getName());

    @Override
    public Connection getConnection() throws SQLException{
        Properties properties = new Properties();
        try{
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException ex){
            logger.fatal("Unable to open or read file", ex);
        }
        String drivers = properties.getProperty("db.drivers");
        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        try {
            Class.forName(drivers);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, username, password);
    }

}
