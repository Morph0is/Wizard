package com.example.wizard.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseHandler {

    private String url;
    private String user;
    private String password;

    // Verbindungsobjekt für die Datenbank
    public Connection conn;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Ups, es ist ein Fehler aufgetretten");
        }
    }

    public DatabaseHandler() {
        // Lädt die Datenbankkonfiguration aus der .properties-Datei
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/database-config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.url = properties.getProperty("url");
        this.user = properties.getProperty("user");
        this.password = properties.getProperty("password");
    }

    public Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }
}
