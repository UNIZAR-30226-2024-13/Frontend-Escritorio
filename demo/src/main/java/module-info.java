module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires json.simple;
    requires java.net.http;
<<<<<<< HEAD
=======
    requires unirest.java;
    requires com.google.gson;
>>>>>>> 013590068d44545fbf313474ba627b45c3c09c65

    opens com.example to javafx.fxml, com.google.gson;
    opens com.example.juegos to javafx.fxml, com.google.gson;
    opens com.example.menusPrincipales to javafx.fxml, com.google.gson;
    opens com.example.perfil to javafx.fxml, com.google.gson;
    
    exports com.example;
    exports com.example.juegos;
    exports com.example.menusPrincipales;
    exports com.example.perfil;
    exports com.example.entidades;
}
