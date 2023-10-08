package com.example.wizard.controller;

import com.example.wizard.helper.Colors;
import com.example.wizard.model.WizardModel;
import com.example.wizard.helper.DatabaseHandler;
import com.example.wizard.helper.SqlStatement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;

/*
Todo : In Diesem File können wir das Data-binding machen mit (Bidirectional).
 Auch werden wir hier die Eingabedaten der Maske Validieren.
 Reihenfolge ist folgender massen:
 - public class Controller implements Initializable         ###Erledigt
 - @FXML  Initialisierungen der Variablen, aus der Maske.   ###Erledigt
 - public  void initialize()                                ###Erledigt
 - Data-binding                                             ###Erledigt
 - Validierung  der Daten und  Steuerung  der Buttons
 */

/**
 * In dieser Klasse wird die View mit dem Model mit Data-binding verbunden.
 * Hier finden auch alle Validierungen statt bevor sie in die Datenbank zugelassen werden.
 */

    public class WizardController {

    @FXML
    private TextField secondNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField birthdayField;
    @FXML
    private TextField ahvNummberField;
    @FXML
    private TextField regionField;
    @FXML
    private TextField childrenField;
    @FXML
    private TextField malecheckField;
    @FXML
    private CheckBox femaleCheckbox;
    @FXML
    private Label messageLabel;

    WizardModel wizardModel = new WizardModel();
    DatabaseHandler databaseHandler = new DatabaseHandler();


    /**
     * Todo: Databinding ist erledigt.
     *  Methoden müssen noch mit dem Databinding verbunden werden.
     *  muss noch check box einbauen zum testen.
     *  aktuell ist ein Test - Textfield im Einsatz.
     */

    //Databinding
    public void initialize() {
        firstNameField.textProperty().bindBidirectional(wizardModel.firstnameFieldProperty());
        secondNameField.textProperty().bindBidirectional(wizardModel.lastNameFieldProperty());
        birthdayField.textProperty().bindBidirectional(wizardModel.birthDayProperty());
        ahvNummberField.textProperty().bindBidirectional(wizardModel.ahvNumberProperty());
        regionField.textProperty().bindBidirectional(wizardModel.regionProperty());
        childrenField.textProperty().bindBidirectional(wizardModel.childrenFieldProperty());
        malecheckField.textProperty().bindBidirectional(wizardModel.maleCheckFieldProperty());
        //messageLabel.textProperty().bindBidirectional(wizardModel.maleCheckFieldProperty());
        //messageLabel.textProperty().bind(wizardModel.messageLabelFieldProperty());
    }

    //Holt alle Datensätze aus der Datenbank
    public void showWizards() {

        try {
            PreparedStatement preparedStatement = databaseHandler.conn.prepareStatement(SqlStatement.SELECT_ALL.getQuery());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String vorname = resultSet.getString("vorname");
                String name = resultSet.getString("name");
                String geburtsdatum = resultSet.getString("geburtsdatum");
                String ahvnr = resultSet.getString("ahvnr");
                String region = resultSet.getString("region");
                int kinder = resultSet.getInt("kinder");
                boolean geschlecht = resultSet.getBoolean(String.valueOf("geschlecht"));

                System.out.println("Name: " + name + ", Vorname: " + vorname + ", Geburtsdatum: " + geburtsdatum +
                        ", AHV Nummer: " + ahvnr + ", Region: " + region + ", Kinder: " + kinder + ", Ist Männlich: " + geschlecht);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    //Neue Person einfügen
    public void insertPerson() {

        try {
            PreparedStatement preparedStatement = databaseHandler.conn.prepareStatement(SqlStatement.EINFUEGEN.getQuery());
            preparedStatement.setString(1, secondNameField.getText());
            preparedStatement.setString(2, firstNameField.getText());

            /*
            //Datum wird formatiert und dann übergeben
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date parseDate = simpleDateFormat.parse( "1980-02-23");
            java.sql.Date sqlDate = new java.sql.Date(parseDate.getTime());
            preparedStatement.setDate(3, sqlDate);
             */

            //zweite Variante um Datum zu übergeben
            Date sqlDate = Date.valueOf(birthdayField.getText());  //Format = "yyyy-mm-dd"
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setString(4, ahvNummberField.getText());
            preparedStatement.setString(5, regionField.getText());
            String childrenFieldString = childrenField.getText();  // angenommen, dass childrenField ein String ist
            int childrenFieldInt = Integer.parseInt(childrenFieldString);
            preparedStatement.setInt(6, childrenFieldInt);

            boolean maleCheckFieldBoolean = Boolean.parseBoolean(malecheckField.getText());
            preparedStatement.setBoolean(7, maleCheckFieldBoolean);

            //Ausführung des Statements.
            int rowsInserted = preparedStatement.executeUpdate(); // SQL-Statement ausführen
            if (rowsInserted > 0) {

               // messageLabel.setText("Eine Person wurde gerade zudefuegt");
               // messageLabel.setStyle("-fx-text-fill: red;");
                messageService("Es wurd ein neuer Datensatz erstellt", String.valueOf(Colors.GREEN));
                System.out.println("Ein neuer Datensatz wurde erfolgreich eingefügt.");
            }
        } catch (SQLException e) {
            System.out.println("Fehler! Da ist etwas schief gelaufen: " + e.getMessage());
        }
    }

    public void onActionsubmitBtn(ActionEvent actionEvent) {
        insertPerson();
    }

    public void messageService(String text, String color) {

        messageLabel.setText(text);
        messageLabel.setStyle(color);
    }
}

