module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.fasterxml.jackson.databind;

    opens com.example to javafx.fxml;
    opens com.example.juegos to javafx.fxml;
    opens com.example.menusPrincipales to javafx.fxml;
    opens com.example.perfil to javafx.fxml;
    
    exports com.example;
    exports com.example.juegos;
    exports com.example.menusPrincipales;
    exports com.example.perfil;
}
