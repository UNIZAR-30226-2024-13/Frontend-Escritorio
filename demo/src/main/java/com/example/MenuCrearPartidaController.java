package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;


public class MenuCrearPartidaController implements Initializable{

    @FXML
    private VBox opcionesVBox;

    private boolean opcionesVisible = false;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    @FXML
    private void switchToAmigos() throws IOException {
        App.setRoot("/com/example/vistas/menuAmigos");
    }

    @FXML
    private void switchToMenuCrearPartida() throws IOException {
        App.setRoot("/com/example/vistas/menuCrearPartida");
    }
    
    @FXML
    private void switchToMenuUnirse() throws IOException {
        App.setRoot("/com/example/vistas/menuUnirsePartida");
    }

    @FXML
    private void switchToMenuPartidas() throws IOException {
        App.setRoot("/com/example/vistas/menuPartidasPausadas");
    }

    @FXML
    private void switchToPerfil() throws IOException {
        App.setRoot("/com/example/vistas/perfil");
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("/com/example/vistas/menuPrincipal");
    }
    
    @FXML
    private void mostrarOcultarOpciones(ActionEvent event) {
        opcionesVisible = !opcionesVisible;
        opcionesVBox.setManaged(opcionesVisible);
        opcionesVBox.setVisible(opcionesVisible);
    }
}
