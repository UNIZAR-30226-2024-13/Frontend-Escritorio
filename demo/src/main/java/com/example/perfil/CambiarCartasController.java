package com.example.perfil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CambiarCartasController implements Initializable{

    @FXML
    private VBox opcionesVBox;
    
    @FXML
    private Label labelFichas;

    private boolean opcionesVisible = false;
    
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
    private void cambiarAmarillo() {
        App.estiloCartas = "carta-button";
        App.estiloEscaleras = "cartas-escaleras";
    }

    @FXML
    private void cambiarAzul() {
        App.estiloCartas = "carta-button-azul";
        App.estiloEscaleras = "cartas-escaleras-azul";
    }

    @FXML
    private void cambiarNaranja() {
        App.estiloCartas = "carta-button-naranja";
        App.estiloEscaleras = "cartas-escaleras-naranja";
    }

    @FXML
    private void cambiarMorado() {
        App.estiloCartas = "carta-button-morado";
        App.estiloEscaleras = "cartas-escaleras-morado";
    }

    @FXML
    private void mostrarOcultarOpciones(ActionEvent event) {
        opcionesVisible = !opcionesVisible;
        opcionesVBox.setManaged(opcionesVisible);
        opcionesVBox.setVisible(opcionesVisible);
    }
}
