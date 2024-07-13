package edu.school21.chat.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
    public static void loadProperties(Properties properties){
        try {
            FileInputStream fis = new FileInputStream("src\\main\\resources\\config.properties");
            properties.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
