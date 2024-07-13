package edu.school21.chat.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLLogic {

    public static Connection getConnection(){
        String dbURL;
        String dbUsername;
        String dbPassword;

        Properties properties=new Properties();
        LoadProperties.loadProperties(properties);
        dbURL=properties.getProperty("db.host");
        dbUsername=properties.getProperty("db.username");
        dbPassword=properties.getProperty("db.password");
        Connection connection;
        try{
            connection= DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
