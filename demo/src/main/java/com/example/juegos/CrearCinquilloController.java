package com.example.juegos;

import java.io.IOException;

import com.example.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CrearCinquilloController {
    
    @FXML
    private Button botonPublica;
    
    @FXML
    private Button botonPrivada;

    @FXML
    private VBox opcionesVBox;

    @FXML
    private Label etiquetaPasswd;
    
    private boolean opcionesVisible = false;
    private boolean passwdVisible = false;

    @FXML
    private void switchToPerfil() throws IOException {
        App.setRoot("/com/example/vistas/perfil/perfil");
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("/com/example/vistas/menusPrincipales/menuPrincipal");
    }
    
    @FXML
    private void mostrarOcultarOpciones(ActionEvent event) {
        opcionesVisible = !opcionesVisible;
        opcionesVBox.setManaged(opcionesVisible);
        opcionesVBox.setVisible(opcionesVisible);
    }
    
    @FXML
    private void partidaPrivada() throws IOException {
        passwdVisible = true;
        etiquetaPasswd.setManaged(passwdVisible);
        etiquetaPasswd.setVisible(passwdVisible);
        botonPublica.getStyleClass().remove("button-clicked");
        botonPrivada.getStyleClass().add("button-clicked");
    }

    @FXML
    private void partidaPublica() throws IOException {
        passwdVisible = false;
        etiquetaPasswd.setManaged(passwdVisible);
        etiquetaPasswd.setVisible(passwdVisible);
        botonPublica.getStyleClass().add("button-clicked");
        botonPrivada.getStyleClass().remove("button-clicked");
    }

    @FXML
    private void crearPartida() throws IOException {
        App.setRoot("/com/example/vistas/juegos/cinquillo");
    }

    @FXML
    private void cancelar() throws IOException {
        // TODO: Eliminar la partida de la BD
        App.setRoot("/com/example/vistas/menusPrincipales/menuPrincipal");
    }
}
