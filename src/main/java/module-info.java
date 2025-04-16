module org.example.infocountries {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.infocountries to javafx.fxml;
    exports org.example.infocountries;
}