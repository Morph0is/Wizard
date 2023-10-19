package com.example.wizard;

import com.example.wizard.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private static MainController mainController;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(StaticViews.StartView));
        fxmlLoader.load();
        mainController = fxmlLoader.getController();
        Scene scene = new Scene(fxmlLoader.getRoot());
        stage.setTitle("WIZARD");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/images/Logo3.png")));
        stage.show();


        try {
            Parent initialContent = FXMLLoader.load(getClass().getResource(StaticViews.SplashView));
            mainController.loadInitialContent(initialContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
