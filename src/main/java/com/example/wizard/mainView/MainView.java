package com.example.wizard.mainView;

import com.example.wizard.MainApp;
import com.example.wizard.StaticViews;
import com.example.wizard.controller.MainController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.effects.DepthLevel;
import io.github.palexdev.materialfx.enums.ButtonType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;

public class MainView extends HBox {

    private final GridPane gridPane = new GridPane();
    private AnchorPane content;
    private CostomButton newpersonBtn;
    private Button showlistBtn;
    private Button editpersonBtn;
    private Button backBtn;

    MainApp mainApp = new MainApp();


    public MainView() {
        newpersonBtn = new CostomButton("Neuer Eintag");
        showlistBtn = new CostomButton("Liste anzeigen");
        editpersonBtn = new CostomButton("Person bearbeiten");
        backBtn = new CostomButton("Home");
        content = new AnchorPane();

        this.getChildren().addAll(gridPane, content);

        intiView();
        initContent();
        initializeLayout();
    }
    private Background customBackground(CornerRadii cornerRadii, Color color, Insets insets) {
        BackgroundFill backgroundFill = new BackgroundFill(color, cornerRadii, insets);
        return new Background(backgroundFill);

    }

    private DropShadow customShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(30.0);
        dropShadow.setOffsetX(10.0);
        dropShadow.setOffsetY(10.0);
        dropShadow.setColor(Color.GRAY);

        return dropShadow;
    }

    private void initContent(){

        content.setEffect(customShadow());
        content.setPrefSize(800, 545);





        content.setBackground(customBackground(new CornerRadii(30), Color.web("#fefefe"), Insets.EMPTY));
        // Optional: Setze einen Rand/Margin für den content-Bereich
        HBox.setMargin(content, new Insets(70));


        try {
            Parent fxmlContent = FXMLLoader.load(getClass().getResource(StaticViews.SplashView));
            content.getChildren().add(fxmlContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initializeLayout(){

        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        gridPane.getRowConstraints().add(new RowConstraints(300,300,300));
        gridPane.getRowConstraints().add(new RowConstraints(40, 40, 40));
        gridPane.getRowConstraints().add(new RowConstraints(40, 40, 40));
        gridPane.getRowConstraints().add(new RowConstraints(40, 40, 40));
        gridPane.getRowConstraints().add(new RowConstraints(40, 40, 40));


        gridPane.getColumnConstraints().add(new ColumnConstraints(250, 250, 250));

    }

    private void intiView() {
        gridPane.add(newpersonBtn, 0, 0);
        gridPane.add(showlistBtn, 0, 1);
        gridPane.add(editpersonBtn, 0, 2);
        gridPane.add(backBtn, 0, 3);

        gridPane.setHalignment(newpersonBtn, HPos.CENTER);
        gridPane.setHalignment(showlistBtn, HPos.CENTER);
        gridPane.setHalignment(editpersonBtn, HPos.CENTER);
        gridPane.setHalignment(backBtn, HPos.CENTER);

        gridPane.setBackground(customBackground(new CornerRadii(0.0, 30.0, 30.0, 0.0, false), Color.web("#8b0bcc"), Insets.EMPTY));
        gridPane.setEffect(customShadow());
        newpersonBtn.setOnAction(e -> {

            Parent fxmlContent = null;
            try {
                fxmlContent = FXMLLoader.load(getClass().getResource(StaticViews.WizardView));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            content.getChildren().add(fxmlContent);

        });
    }




}
