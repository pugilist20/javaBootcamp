package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Properties;

public class DBConnector {
    private static Properties properties=LoadProperties.loadProperties();
    private static String dbURL=properties.getProperty("db.host");
    private static String dbUsername=properties.getProperty("db.username");
    private static String dbPassword=properties.getProperty("db.password");
    public static HikariDataSource getConnection(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbURL);
        config.setUsername(dbUsername);
        config.setPassword(dbPassword);
        return new HikariDataSource(config);
    }
}
