package com.example.menusPrincipales;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;


public class MenuElegirJuegoController implements Initializable{

    @FXML
    private VBox opcionesVBox;

    private boolean opcionesVisible = false;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    @FXML
    private void switchToAmigos() throws IOException {
        App.setRoot("/com/example/vistas/menusPrincipales/menuAmigos");
    }

    @FXML
    private void switchToMenuCrearPartida() throws IOException {
        App.setRoot("/com/example/vistas/menusPrincipales/menuCrearPartida");
    }
    
    @FXML
    private void switchToMenuUnirse() throws IOException {
        App.setRoot("/com/example/vistas/menusPrincipales/menuUnirsePartida");
    }

    @FXML
    private void switchToMenuPartidas() throws IOException {
        App.setRoot("/com/example/vistas/menusPrincipales/menuPartidasPausadas");
    }

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
    private void crearCinquillo() throws IOException {
        // TODO : AÑADIR LA PARTIDA A LA BD PARA TENER EL ID DISPONIBLE PARA USARLO COMO CONTRASEÑA
        App.setRoot("/com/example/vistas/juegos/crearCinquillo");
    }

    @FXML
    private void crearMentiroso() throws IOException {
        // TODO : AÑADIR LA PARTIDA A LA BD PARA TENER EL ID DISPONIBLE PARA USARLO COMO CONTRASEÑA
        App.setRoot("/com/example/vistas/juegos/crearMentiroso");
    }

    @FXML
    private void crearPoker() throws IOException {
        // TODO : AÑADIR LA PARTIDA A LA BD PARA TENER EL ID DISPONIBLE PARA USARLO COMO CONTRASEÑA
        App.setRoot("/com/example/vistas/juegos/crearPoker");
    }

    @FXML
    private void crearBlackJack() throws IOException {
        // TODO : AÑADIR LA PARTIDA A LA BD PARA TENER EL ID DISPONIBLE PARA USARLO COMO CONTRASEÑA
        App.setRoot("/com/example/vistas/juegos/crearBlackJack");
    }

    @FXML
    private void crearUNO() throws IOException {
        // TODO : AÑADIR LA PARTIDA A LA BD PARA TENER EL ID DISPONIBLE PARA USARLO COMO CONTRASEÑA
        App.setRoot("/com/example/vistas/juegos/crearUNO");
    }
}
