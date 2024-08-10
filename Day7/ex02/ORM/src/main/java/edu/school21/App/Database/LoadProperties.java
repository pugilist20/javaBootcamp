package edu.school21.App.Database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
    public static Properties loadProperties(){
        try {
            FileInputStream fis = new FileInputStream("src\\main\\resources\\config.properties");
            Properties prop = new Properties(); prop.load(fis);
            return prop;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}