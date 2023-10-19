package com.example.wizard.controller;

import com.example.wizard.StaticViews;
import com.example.wizard.helper.Colors;
import com.example.wizard.helper.DatabaseHandler;
import com.example.wizard.helper.SqlStatement;
import com.example.wizard.model.Person;
import com.example.wizard.model.WizardModel;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
    private CheckBox maleCheckBox;
    @FXML
    private CheckBox femaleCheckBox;
    @FXML
    private Label messageLabel;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    private static Person selectedPerson;

    WizardModel wizardModel = new WizardModel();
    DatabaseHandler databaseHandler = new DatabaseHandler();


    /**
     * Todo: Databinding ist erledigt.
     *  Methoden müssen noch mit dem Databinding verbunden werden.
     *  muss noch check box einbauen zum testen.
     *  aktuell ist ein Test - Textfield im Einsatz.
     */

    //databinding
    public void initialize() {
        firstNameField.textProperty().bindBidirectional(wizardModel.firstnameFieldProperty());
        secondNameField.textProperty().bindBidirectional(wizardModel.lastNameFieldProperty());
        birthdayField.textProperty().bindBidirectional(wizardModel.birthDayProperty());
        ahvNummberField.textProperty().bindBidirectional(wizardModel.ahvNumberProperty());
        regionField.textProperty().bindBidirectional(wizardModel.regionProperty());
        childrenField.textProperty().bindBidirectional(wizardModel.childrenFieldProperty());


        femaleCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                maleCheckBox.setSelected(false);
            }
        });

        maleCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                femaleCheckBox.setSelected(false);
            }
        });
        loadSelectedPersonData();
        formatBirthdayField();
    }

    public static void setSelectedPerson(Person person) {
        selectedPerson = person;
    }

    public void loadSelectedPersonData() {
        if (selectedPerson != null) {
            secondNameField.setText(selectedPerson.getName());
            firstNameField.setText(selectedPerson.getVorname());
            birthdayField.setText(selectedPerson.getGebDatum());
            ahvNummberField.setText(selectedPerson.getAhvNr());
            regionField.setText(selectedPerson.getRegion());
            childrenField.setText(String.valueOf(selectedPerson.getKinder()));

            if ("männlich".equals(selectedPerson.getGeschlecht())) {
                maleCheckBox.setSelected(true);
            } else {
                femaleCheckBox.setSelected(true);
            }
        }
    }

    public void formatBirthdayField() {
        String unformattedDate = birthdayField.getText();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Format von java.sql.Date
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd.MM.yyyy"); // Gewünschtes Format

        try {
            java.util.Date date = simpleDateFormat.parse(unformattedDate);
            String formattedDate = targetFormat.format(date);
            birthdayField.setText(formattedDate);
        } catch (ParseException e) {
            System.out.println("Fehler beim Parsen des Datums: " + e.getMessage());
        }
    }




    public void onActionsubmitBtn(ActionEvent event) {
        String firstName = wizardModel.getFirstnameField();
        String lastName = wizardModel.getLastNameField();
        String birthday = wizardModel.getBirthDay();
        String ahvNumber = wizardModel.getAhvNumber();
        String region = wizardModel.getRegion();
        String children = wizardModel.getChildrenField();


        if (firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty() ||
                birthday == null || birthday.isEmpty() ||
                ahvNumber == null || ahvNumber.isEmpty() ||
                region == null || region.isEmpty() ||
                children == null || children.isEmpty()) {
            messageService("Bitte füllen Sie alle Felder aus.", Colors.RED);

        } else {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                simpleDateFormat.parse(birthday);
            } catch (ParseException e) {
                messageService("Ungültiges Datumsformat. Verwenden Sie das Format 'dd.MM.yyyy'.", Colors.RED);
                return;
            }


            insertPerson();
            messageService("Anmeldung erfolgreich!", Colors.GREEN);
        }
    }


    /**
     * Methode um eine neue Person zu erstellen
     */
    public void insertPerson() {

        try {
            PreparedStatement preparedStatement = databaseHandler.getConnection().prepareStatement(SqlStatement.EINFUEGEN.getQuery());
            preparedStatement.setString(1, secondNameField.getText());
            preparedStatement.setString(2, firstNameField.getText());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            try {
                java.util.Date parseDate = simpleDateFormat.parse(birthdayField.getText());
                java.sql.Date sqlDate = new java.sql.Date(parseDate.getTime());
                preparedStatement.setDate(3, sqlDate);
            } catch (ParseException e) {
                messageService("Das Datum muss in dd.mm.yyyy Format eingegeben werden.", Colors.GREEN);
                System.out.println("Das Datum ist im falschen Format: " + e.getMessage());
                return;
            }

            preparedStatement.setString(4, ahvNummberField.getText());
            preparedStatement.setString(5, regionField.getText());
            String childrenFieldString = childrenField.getText();  // angenommen, dass childrenField ein String ist
            int childrenFieldInt = Integer.parseInt(childrenFieldString);
            preparedStatement.setInt(6, childrenFieldInt);

            boolean gender = maleCheckBox.isSelected(); // true für männlich, false für weiblich
            preparedStatement.setBoolean(7, gender);



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
            boolean gender = maleCheckBox.isSelected(); // true für männlich, false für weiblich
            preparedStatement.setBoolean(7, gender);
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

    //Diese Methode dient dazu einen Nachricht direkt auf die Visu auszugeben mit der entsprechenden Farbe.
    public void messageService(String message, Colors color) {
        messageLabel.setText(message);
        messageLabel.setStyle(color.c);
    }






}

