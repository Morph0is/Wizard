package com.example.wizard.controller;

import com.example.wizard.StaticViews;
import com.example.wizard.helper.Colors;
import com.example.wizard.helper.DatabaseHandler;
import com.example.wizard.helper.SqlStatement;
import com.example.wizard.model.Person;
import com.example.wizard.model.Person;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import static com.example.wizard.MainApp.switchToView;

public class TableController {


    @FXML
    private MFXButton deleteBtn;
    @FXML
    private MFXButton editBtn;
    @FXML
    private Label messageLabel;
    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, String> idCol;
    @FXML
    private TableColumn<Person, String> nameCol;
    @FXML
    private TableColumn<Person, String> vornameCol;
    @FXML
    private TableColumn<Person, String> gebDatumCol;
    @FXML
    private TableColumn<Person, String> ahvnrCol;
    @FXML
    private TableColumn<Person, String> regionCol;
    @FXML
    private TableColumn<Person, String> kinderCol;
    @FXML
    private TableColumn<Person, String> geschlechtCol;
    @FXML
    private Label getMessageLabel;




    @FXML
    public void initialize() {

        loadData();

    }

    public void onActionDeleteBtn(ActionEvent actionEvent) {
        Person selectedPerson = tableView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            try {
                int id = selectedPerson.getId();
                DatabaseHandler databaseHandler = new DatabaseHandler();
                PreparedStatement preparedStatement = databaseHandler.getConnection().prepareStatement(SqlStatement.LOESCHEN.getQuery());
                preparedStatement.setInt(1, id); // Annahme: personID ist die eindeutige ID des zu löschenden Datensatzes

                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Datensatz erfolgreich gelöscht.");
                }
                preparedStatement.execute();
                tableView.getItems().remove(selectedPerson);
                tableView.refresh();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            messageLabel.setText("Keine Person ausgewählt.");
        }
    }

    public void onActonEditBtn(ActionEvent actionEvent) {
        Person selectedPerson = tableView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            WizardController.setSelectedPerson(selectedPerson); // Übergeben der ausgewählten Person
            switchToView(StaticViews.WizardView);
            loadData();
        } else {
            messageService("Bitte wähle zuerst eine Person aus.", Colors.RED);

        }
    }




    @FXML
    private void loadData() {
        Task<ObservableList<Person>> task = new Task<ObservableList<Person>>() {
            @Override
            protected ObservableList<Person> call() throws Exception {
                DatabaseHandler databaseHandler = new DatabaseHandler();
                Connection conn = databaseHandler.getConnection();

                String query = SqlStatement.SELECT_ALL.getQuery();
                ObservableList<Person> data = FXCollections.observableArrayList();

                try (Statement statement = conn.createStatement();
                     ResultSet resultSet = statement.executeQuery(query)) {

                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String vorname = resultSet.getString("vorname");
                        String gebDatum = resultSet.getString("geburtsdatum");
                        String ahvNr = resultSet.getString("ahvNr");
                        String region = resultSet.getString("region");
                        int kinder = resultSet.getInt("kinder");
                        String geschlecht = resultSet.getString("geschlecht");

                        Person person = new Person(id, name, vorname, gebDatum, ahvNr, region, kinder, geschlecht);
                        data.add(person);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return data;
            }
        };

        task.setOnSucceeded(e -> {
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            vornameCol.setCellValueFactory(new PropertyValueFactory<>("vorname"));
            gebDatumCol.setCellValueFactory(new PropertyValueFactory<>("gebDatum"));
            ahvnrCol.setCellValueFactory(new PropertyValueFactory<>("ahvNr"));
            regionCol.setCellValueFactory(new PropertyValueFactory<>("region"));
            kinderCol.setCellValueFactory(new PropertyValueFactory<>("kinder"));
            geschlechtCol.setCellValueFactory(new PropertyValueFactory<>("geschlecht"));
            geschlechtCol.setCellFactory(column -> new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        // Ändern Sie den Wert je nachdem, ob er 0 oder 1 ist
                        setText("1".equals(item) ? "Männlich" : "Weiblich");
                    }
                }
            });

            tableView.setItems(task.getValue());
        });

        new Thread(task).start();
    }
    public void messageService(String message, Colors color) {
        messageLabel.setText(message);
        messageLabel.setStyle(color.c);
    }

}

