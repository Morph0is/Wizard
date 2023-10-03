module com.example.wizard {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.wizard to javafx.fxml;
    exports com.example.wizard;
}