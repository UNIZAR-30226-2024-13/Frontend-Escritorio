module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires json.simple;
    requires java.net.http;
    requires unirest.java;
    requires com.google.gson;

    opens com.example to javafx.fxml, com.google.gson;
    opens com.example.juegos to javafx.fxml, com.google.gson;
    opens com.example.menusPrincipales to javafx.fxml, com.google.gson;
    opens com.example.perfil to javafx.fxml, com.google.gson;
    opens com.example.entidades to javafx.fxml, com.google.gson;
    
    exports com.example;
    exports com.example.juegos;
    exports com.example.menusPrincipales;
    exports com.example.perfil;
    exports com.example.entidades;
}
