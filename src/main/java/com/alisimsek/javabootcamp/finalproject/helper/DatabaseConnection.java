package com.alisimsek.javabootcamp.finalproject.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private Connection connection = null;

    public DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(Config.DB_URL, Config.DB_USERNAME, Config.DB_PASSWORD);
            System.out.println("Database'e bağlandı!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
