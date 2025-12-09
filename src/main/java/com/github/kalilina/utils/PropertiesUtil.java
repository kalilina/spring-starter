package com.github.kalilina.utils;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();
    private static final String PROPERTIES_PATH = "application.properties"; // classpath:application.properties

    static {
        loadProperties();
    }

    private void loadProperties() {
        try(InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH)) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
