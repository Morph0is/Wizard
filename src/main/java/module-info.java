module com.example.wizard {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.wizard to javafx.fxml;
    exports com.example.wizard;
}