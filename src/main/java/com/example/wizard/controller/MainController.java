package com.example.wizard.controller;

import com.example.wizard.StaticViews;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    @FXML
    private AnchorPane testView;



    public void switchContent(Pane root) {
        StackPane stackPane = new StackPane(root);
        StackPane.setAlignment(root, Pos.CENTER);
        testView.getChildren().clear();
        testView.getChildren().add(stackPane);
    }


    public void OnActionNeuPersonBtn(ActionEvent actionEvent) {

    }

    public void OnActionShowPersons(ActionEvent actionEvent) {
    }
}
