package com.example.wizard.mainView;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.util.Duration;

public class CostomButton extends MFXButton {

    public CostomButton(String text) {
        super(text);

        // Setzen Sie die Grundfarbe des Buttons
        this.setStyle(  "-fx-background-color: linear-gradient(to right, rgba(66, 234, 121, 1.0), rgba(56, 250, 215, 1.0));" +
                        "-fx-border-radius: 30px; -fx-background-radius: 30px; " +
                        "-fx-effect: dropshadow(gaussian, #565656, 30, 0.2, 10, 10); ");

        // Erstelle FadeTransition für den Button
        /*FadeTransition ft = new FadeTransition(Duration.seconds(1), this);
        ft.setFromValue(1.0);
        ft.setToValue(0.5);

        // Füge Hover-Ereignisse zum Button hinzu
        this.setOnMouseEntered(e -> {
            ft.setRate(1.0);
            ft.play();
        });
        this.setOnMouseExited(e -> {
            ft.setRate(-1.0);
            ft.play();
        }); */

        // Setze die Größe des Buttons
        this.setPrefSize(180, 40);
        
        // Setze den Randabstand des Buttons
        this.setPadding(new Insets(10, 20, 10, 20));

        // Setze den Eckenradius des Buttons
        CornerRadii cornerRadii = new CornerRadii(30);
        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#FF5733"), cornerRadii, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        this.setBackground(background);
    }
}
