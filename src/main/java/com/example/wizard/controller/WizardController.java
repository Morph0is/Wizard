package com.example.wizard.controller;

import com.example.wizard.StaticViews;
import com.example.wizard.helper.Colors;
import com.example.wizard.model.WizardModel;
import com.example.wizard.helper.DatabaseHandler;
import com.example.wizard.helper.SqlStatement;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.wizard.MainApp.switchToView;
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
 - Validierung  der Daten und  Steuerung  der Buttons       ***inPROGESS
 - Methoden DELETE und EDIT einfuegen                       ***inPROGRESS
 */

/**
 * In dieser Klasse wird die View mit dem Model mit Data-binding verbunden.
 * Hier finden auch alle Validierungen statt bevor sie in die Datenbank zugelassen werden.
 */
public class WizardController implements Initializable {


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
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private AnchorPane content;
    @FXML
            private Button sendbtn;

    WizardModel wizardModel = new WizardModel();
    DatabaseHandler databaseHandler = new DatabaseHandler();


    /**
     * Todo: Databinding ist erledigt.
     *  Methoden müssen noch mit dem Databinding verbunden werden.
     *  muss noch check box einbauen zum testen.
     *  aktuell ist ein Test - Textfield im Einsatz.
     */


    //databinding
    public void bindToModel() {
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

    public void onActionsubmitBtn(ActionEvent event) {
        String firstName = wizardModel.getFirstnameField();
        String lastName = wizardModel.getLastNameField();
        String birthday = wizardModel.getBirthDay();
        String ahvNumber = wizardModel.getAhvNumber();
        String region = wizardModel.getRegion();
        String children = wizardModel.getChildrenField();
        String malecheck = wizardModel.getMaleCheckField();

        if (firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty() ||
                birthday == null || birthday.isEmpty() ||
                ahvNumber == null || ahvNumber.isEmpty() ||
                region == null || region.isEmpty() ||
                children == null || children.isEmpty() ||
                malecheck == null || malecheck.isEmpty()) {
            messageService("Bitte füllen Sie alle Felder aus.", Colors.RED);

        } else {
            try {
                Date.valueOf(birthday);
            } catch (IllegalArgumentException e) {
                messageService("Ungültiges Datumsformat. Verwenden Sie das Format 'yyyy-mm-dd'.", Colors.RED);
                return;
            }

            insertPerson();
            messageService("Anmeldung erfolgreich!", Colors.GREEN);
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
                System.out.println("Ein neuer Datensatz wurde erfolgreich eingefügt.");

                //verzögerung
                PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
                pause.setOnFinished(e -> switchToView(StaticViews.WizardView));
                pause.play();


            }
        } catch (SQLException e) {
            System.out.println("Fehler! Da ist etwas schief gelaufen: " + e.getMessage());
        }
    }


    public void editPerson(int personID) {
        try {
            PreparedStatement preparedStatement = databaseHandler.conn.prepareStatement(SqlStatement.AKTUALISIEREN.getQuery());
            preparedStatement.setString(1, secondNameField.getText());
            preparedStatement.setString(2, firstNameField.getText());
            Date sqlDate = Date.valueOf(birthdayField.getText());
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setString(4, ahvNummberField.getText());
            preparedStatement.setString(5, regionField.getText());
            String childrenFieldString = childrenField.getText();
            int childrenFieldInt = Integer.parseInt(childrenFieldString);
            preparedStatement.setInt(6, childrenFieldInt);
            boolean maleCheckFieldBoolean = Boolean.parseBoolean(malecheckField.getText());
            preparedStatement.setBoolean(7, maleCheckFieldBoolean);
            preparedStatement.setInt(8, personID); // Annahme: personID ist die eindeutige ID des zu bearbeitenden Datensatzes

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Datensatz erfolgreich aktualisiert.");
            }
        } catch (SQLException e) {
            System.out.println("Fehler! Da ist etwas schief gelaufen: " + e.getMessage());
        }
    }


    public void deletePerson(int personID) {
        try {
            PreparedStatement preparedStatement = databaseHandler.conn.prepareStatement(SqlStatement.LOESCHEN.getQuery());
            preparedStatement.setInt(1, personID); // Annahme: personID ist die eindeutige ID des zu löschenden Datensatzes

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Datensatz erfolgreich gelöscht.");
            }
        } catch (SQLException e) {
            System.out.println("Fehler! Da ist etwas schief gelaufen: " + e.getMessage());
        }
    }

    //Diese Methode dient dazu eine Nachricht direkt auf die Visualisierung auszugeben mit der entsprechenden Farbe.
    public void messageService(String message, Colors color) {
        messageLabel.setText(message);
        messageLabel.setStyle(color.c);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindToModel();
    }
}

