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
    private AnchorPane content; // Angenommen, das ist Ihr 'content' Bereich

    public void loadInitialContent(Parent initialContent) {
        content.getChildren().clear();
        content.getChildren().add(initialContent);
    }

    public void switchContent(Parent newContent) {
        content.getChildren().clear();
        content.getChildren().add(newContent);
    }





    public void OnActionNeuPersonBtn(ActionEvent actionEvent) {
        switchToView(StaticViews.WizardView);
    }

    public void OnActionShowPersons(ActionEvent actionEvent) {
    }

    public void OnActionBackbtn(ActionEvent actionEvent) {
        switchToView(StaticViews.SplashView);
    }
}
