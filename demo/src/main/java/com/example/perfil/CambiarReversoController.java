package com.example.perfil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CambiarReversoController implements Initializable{

    @FXML
    private VBox opcionesVBox;
    
    @FXML
    private Label labelFichas;

    private boolean opcionesVisible = false;
    
    @FXML
    private ImageView previsualizacionImagen;
    
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
    private void cambiarAzul() {
        App.reversoCartas = "/com/example/imgs/reverso_azul.jpg";
        Image imagen = new Image(getClass().getResourceAsStream("/com/example/imgs/reverso_azul.jpg"));
        previsualizacionImagen.setImage(imagen);
    }

    @FXML
    private void cambiarRojo() {
        App.reversoCartas = "/com/example/imgs/reverso_rojo.jpg";
        Image imagen = new Image(getClass().getResourceAsStream("/com/example/imgs/reverso_rojo.jpg"));
        previsualizacionImagen.setImage(imagen);
    }

    @FXML
    private void cambiarNaranja() {
        App.reversoCartas = "/com/example/imgs/reverso_naranja.jpg";
        Image imagen = new Image(getClass().getResourceAsStream("/com/example/imgs/reverso_naranja.jpg"));
        previsualizacionImagen.setImage(imagen);
    }

    @FXML
    private void cambiarMorado() {
        App.reversoCartas = "/com/example/imgs/reverso_morado.jpg";
        Image imagen = new Image(getClass().getResourceAsStream("/com/example/imgs/reverso_morado.jpg"));
        previsualizacionImagen.setImage(imagen);
    }

    @FXML
    private void mostrarOcultarOpciones(ActionEvent event) {
        opcionesVisible = !opcionesVisible;
        opcionesVBox.setManaged(opcionesVisible);
        opcionesVBox.setVisible(opcionesVisible);
    }
}
