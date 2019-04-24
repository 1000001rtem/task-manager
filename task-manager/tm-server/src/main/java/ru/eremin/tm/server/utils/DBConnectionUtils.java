package ru.eremin.tm.server.utils;


import com.sun.org.apache.bcel.internal.util.ClassPath;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @autor av.eremin on 24.04.2019.
 */

public final class DBConnectionUtils {

    @SneakyThrows
    public static Connection getConnection() {
        Properties properties = getProperties();
        Class.forName(properties.getProperty("jdbc.driver"));
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        return DriverManager.getConnection(url, username, password);
    }

    private static Properties getProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(DBConnectionUtils.class.getClassLoader().getResourceAsStream("db.properties"));
        return properties;
    }
}
