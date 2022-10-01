package com.solotesting.duastana.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private static final String PROPERTIES_PATH = "./src/test/resources/constants.properties";
    private Properties properties;
    private static PropertiesUtil instance;

    private PropertiesUtil() {

    }

    public static PropertiesUtil getInstance() {
        if (instance == null) {
            instance = new PropertiesUtil();
        }
        return instance;
    }

    public Properties getProperties() {
        if (properties == null) {
            synchronized (this) {
                if (properties == null) {
                    properties = new Properties();
                }
            }
        }
        try(FileInputStream fileInputStream = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return properties;
    }

}
