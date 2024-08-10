package edu.school21.App.Database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import edu.school21.App.Database.LoadProperties;
import java.util.Properties;

public class DBConnector {
    private static final Properties properties= LoadProperties.loadProperties();
    private static final String dbURL=properties.getProperty("db.host");
    private static final String dbUsername=properties.getProperty("db.username");
    private static final String dbPassword=properties.getProperty("db.password");
    public static HikariDataSource getConnection(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbURL);
        config.setUsername(dbUsername);
        config.setPassword(dbPassword);
        return new HikariDataSource(config);
    }
}