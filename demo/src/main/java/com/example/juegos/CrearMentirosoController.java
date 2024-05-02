package com.example.juegos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CrearMentirosoController implements Initializable {
    
    @FXML
    private Button botonPublica;
    
    @FXML
    private Button botonPrivada;

    @FXML
    private VBox opcionesVBox;

    @FXML
    private Label etiquetaPasswd;
    
    @FXML
    private Label labelFichas;
    
    private boolean opcionesVisible = false;
    private boolean passwdVisible = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelFichas.setText(App.usuario.getDinero() + " Fichas");
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
        App.setRoot("/com/example/vistas/juegos/mentiroso");
    }

    @FXML
    private void cancelar() throws IOException {
        // TODO: Eliminar la partida de la BD
        App.setRoot("/com/example/vistas/menusPrincipales/menuPrincipal");
    }
}
