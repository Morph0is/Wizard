package com.example.wizard.controller;

import com.example.wizard.MainApp;
import com.example.wizard.StaticViews;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import static com.example.wizard.MainApp.switchToView;

public class MainController {
    @FXML
    private AnchorPane content;




    public void switchContent(Pane root) {
        StackPane stackPane = new StackPane(root);
        StackPane.setAlignment(root, Pos.CENTER);
        content.getChildren().clear();
        content.getChildren().add(stackPane);
    }


    public void OnActionNeuPersonBtn(ActionEvent actionEvent) {
        switchToView(StaticViews.WizardView);
    }

    public void OnActionShowPersons(ActionEvent actionEvent) {
    }

    public void OnActionBackbtn(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(StaticViews.StartView));
    }
}
