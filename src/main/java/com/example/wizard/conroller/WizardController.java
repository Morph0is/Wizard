package com.example.wizard.conroller;

import com.example.wizard.helper.DatabaseHandler;
import com.example.wizard.helper.SqlStatement;

import java.sql.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;

/*
Todo : In Diesem File können wir das Data-binding machen mit (Bidirectional).
 Auch werden wir hier die Eingabedaten der Maske Validieren.
 Reihenfolge ist folgender massen:
 - public class Controller implements Initializable
 - @FXML  Initialisierungen der Variablen, aus der Maske.
 - public  void initialize()
 - Data-binding
 - Validierung  der Daten und  Steuerung  der Buttons
 */

/**
 * In dieser Klasse wird die View mit dem Model mit Data-binding verbunden.
 * Hier finden auch alle Validierungen statt bevor sie in die Datenbank zugelassen werden.
 */

    public class WizardController {

    DatabaseHandler databaseHandler = new DatabaseHandler();

    //Holt alle Datensätze aus der Datenbank
    public void showWizards() {

            try {
                    PreparedStatement preparedStatement = databaseHandler.conn.prepareStatement(SqlStatement.SELECT_ALL.getQuery());
                    ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String vorname = resultSet.getString("vorname");
                    String geburtsdatum = resultSet.getString("geburtsdatum");
                    String ahvnr = resultSet.getString("ahvnr");
                    String region = resultSet.getString("region");
                    int kinder = resultSet.getInt("kinder");
                    boolean geschlecht = resultSet.getBoolean("geschlecht");

                    System.out.println("Name: " + name + ", Vorname: " + vorname + ", Geburtsdatum: " + geburtsdatum +
                            ", AHV Nummer: " + ahvnr + ", Region: " + region + ", Kinder: " + kinder + ", Ist Männlich: " + geschlecht);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }


    //Neue Person einfügen
    public void insertPerson(){
        /**
         * Todo: Hier werden wir dann den Inhalt der übergeben wird austauschen dur den Inhalt(Variablen), die aus der Maske kommen.
         */

        try {
            PreparedStatement preparedStatement = databaseHandler.conn.prepareStatement(SqlStatement.EINFUEGEN.getQuery());
            preparedStatement.setString(1, "Von Anhalt");
            preparedStatement.setString(2, "Markus");
        /*
        //Datum wird formatiert und dann übergeben
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date parseDate = simpleDateFormat.parse( "1980-02-23");
        java.sql.Date sqlDate = new java.sql.Date(parseDate.getTime());
        preparedStatement.setDate(3, sqlDate);

         */
            //zweite Variante um Datum zu übergeben
            Date sqlDate = Date.valueOf("1980-02-23");
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setString(4, "2344.2344.5234.553");
            preparedStatement.setString(5, "Winter");
            preparedStatement.setInt(6, 1);
            preparedStatement.setBoolean(7, true);

            //Ausführung des Statements.
            int rowsInserted = preparedStatement.executeUpdate(); // SQL-Statement ausführen
            if (rowsInserted > 0) {
                System.out.println("Ein neuer Datensatz wurde erfolgreich eingefügt.");
            }
        } catch (SQLException e) {
            System.out.println("Fehler! Da ist etwas schief gelaufen: " + e.getMessage());
            }
        }
    }
