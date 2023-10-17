package com.example.wizard;

import com.example.wizard.controller.MainController;
import com.example.wizard.mainView.MainView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private static MainController mainController;
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainView mainView = new MainView();
        primaryStage.setScene(new Scene(mainView));
        primaryStage.setTitle("WIZARD");


        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



    public static void switchToView(String viewName) {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(viewName));
        try {
            mainController.switchContent(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
