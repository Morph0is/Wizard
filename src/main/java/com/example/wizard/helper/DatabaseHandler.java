package com.example.wizard.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

    // Verbindungs-URL, Benutzername und Passwort für die Datenbank
    private final String url = "jdbc:mysql://bnfpbzfl49ffy485x4gh-mysql.services.clever-cloud.com:3306/bnfpbzfl49ffy485x4gh";
    private final String user = "u3jf91cj3vfi46hw";
    private final String password = "wTiD2blNRmBU0IwrVwEG";

    // Verbindungsobjekt für die Datenbank
    public Connection conn;

    /**
     * Konstruktor der Klasse. Stellt eine Verbindung zur Datenbank her.
     */
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load MySQL Driver");
        }
    }

    /**
     * Stellt eine Verbindung zur Datenbank her und gibt diese zurück.
     * @return Connection-Objekt für die Datenbank
     * @throws SQLException wenn die Verbindung fehlschlägt
     */
    public Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }

    public ResultSet getWizards() {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery("SELECT * FROM wizard");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Zeigt alle Informationen der "wizard"-Datensätze aus der Datenbank an.
     * Liest das ResultSet aus getWizards() und gibt die Daten in der Konsole aus.
     *
     * @return
     */
    public List<String> showWizards() {
        List<String> wizardList = new ArrayList<>();
        ResultSet result = getWizards();
        if (result != null) {
            try {
                while (result.next()) {
                    String name = result.getString("name");
                    String vorname = result.getString("vorname");
                    String geburtsdatum = result.getString("geburtsdatum");
                    String ahvnr = result.getString("ahvnr");
                    String region = result.getString("region");
                    int kinder = result.getInt("kinder");
                    boolean geschlecht = result.getBoolean("geschlecht");

                    System.out.println("Name: " + name + ", Vorname: " + vorname + ", Geburtsdatum: " + geburtsdatum +
                            ", AHV Nummer: " + ahvnr + ", Region: " + region + ", Kinder: " + kinder + ", Ist Männlich: " + geschlecht);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return wizardList;
    }
}

