package com.example.wizard.controller;

import com.example.wizard.MainApp;
import com.example.wizard.StaticViews;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import static com.example.wizard.MainApp.switchToView;


public class MainController {
    @FXML
    private AnchorPane content;

    public void loadInitialContent(Parent initialContent) {
        content.getChildren().clear();
        content.getChildren().add(initialContent);
    }

    public void switchContent(Parent newContent) {
        content.getChildren().clear();
        content.getChildren().add(newContent);
    }

    WizardController wizardController = new WizardController();


    public void OnActionNeuPersonBtn(ActionEvent actionEvent) {
        wizardController.selectedPerson = null;
        switchToView(StaticViews.WizardView);
    }

    public void OnActionShowPersons(ActionEvent actionEvent) {
        switchToView(StaticViews.listviewPersons);
    }
    public void OnActionReturnBtn(ActionEvent actionEvent) {
        switchToView(StaticViews.StartView);

    }

    public void OnActionBackbtn(ActionEvent actionEvent) {
        wizardController.selectedPerson = null;
        switchToView(StaticViews.SplashView);
    }
}
