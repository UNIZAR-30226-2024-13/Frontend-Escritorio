package com.example.juegos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.App;
import com.example.CartaController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class CinquilloController implements Initializable{

    @FXML
    private CartaController cincoOrosController;

    @FXML
    private Button botonCarta1;

    @FXML
    private GridPane cincoOros;

    private CartaController cartaController = new CartaController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("/com/example/vistas/menusPrincipales/menuPrincipal");
    }

    @FXML
    private void pausarPartida() throws IOException {
        // TODO : Iniciar votacion o votar si / no
    }

    public void configurarCarta() {
        Image imagen = new Image(getClass().getResourceAsStream("/resources/com/example/imgs/reverso.jpg"));
        cartaController.setNumero("1");
        cartaController.setImagen(imagen);
    }

    @SuppressWarnings("exports")
    @FXML
    public void ponerCarta(ActionEvent event) {
        botonCarta1.setVisible(false);
        cincoOros.setVisible(true);
    }
}
