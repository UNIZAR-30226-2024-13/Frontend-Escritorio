package com.example.juegos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.App;
import com.example.CartaController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class CinquilloController implements Initializable{


    @FXML
    private GridPane cincoOros;

    @FXML
    private Button botonCarta1;


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


    @FXML
    private void ponerCarta(){
        cincoOros.setVisible(true);
        cincoOros.add(new Label("pene"), 0, 0);
        cincoOros.add(new ImageView(new Image(getClass().getResourceAsStream("/com/example/imgs/reverso.jpg"))), 0, 1);
    }
}