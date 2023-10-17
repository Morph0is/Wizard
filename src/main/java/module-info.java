module com.example.wizard {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires MaterialFX;


    opens com.example.wizard.controller to javafx.fxml;
    opens com.example.wizard to javafx.graphics;
}