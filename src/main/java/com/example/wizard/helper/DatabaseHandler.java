package com.example.wizard.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    public DatabaseHandler() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Holt alle Datensätze aus der "wizard"-Tabelle und gibt sie als ResultSet zurück.
     *
     * @return ResultSet der Datensätze aus der "wizard"-Tabelle.
     */
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
     */
    public void showWizards() {
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
    }
}

