package com.example.wizard.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
Todo: Hier werden ausschliesslich alle SQL Statements erstellt in Variablen die dann  in der Controller klasse übergeben werden.
 Alle methoden die zur steuerung der Maske  dienen die sich in dieser Klasse befinden müssen in den Controller überführt werden.
 Die Verbindungsdaten werden wir in wenn ich herausgefunden habe wo, auslagern.
 */

/**
 * In dieser Klasse befinden sich det Verbindungsstring und alle benötigten Queries dieses Programms
 */

public class DatabaseHandler {

    private final String url = "jdbc:mysql://bnfpbzfl49ffy485x4gh-mysql.services.clever-cloud.com:3306/bnfpbzfl49ffy485x4gh";
    private final String user = "u3jf91cj3vfi46hw";
    private final String password = "wTiD2blNRmBU0IwrVwEG";
    public Connection conn;


    // Verbindungsobjekt für die Datenbank
    public DatabaseHandler() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Die Verbindung zur Datenbank war erfolgreich!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error! Etwas stimmt nicht mit der Datenbankverbindung!");
        }
    }
}

