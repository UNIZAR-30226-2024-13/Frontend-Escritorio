package com.example.juegos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.App;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class CinquilloController implements Initializable{


    @FXML
    private GridPane escaleraOros;

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
        escaleraOros.setVisible(true);
        escaleraOros.add(new Label("pene"), 0, 0);
        escaleraOros.add(new Label("pene"), 0, 1);
        escaleraOros.add(new Label("pene"), 0, 2);
        escaleraOros.add(new Label("pene"), 0, 3);
        escaleraOros.add(new Label("pene"), 0, 4);
        escaleraOros.add(new Label("pene"), 0, 5);
        escaleraOros.add(new ImageView(new Image(getClass().getResourceAsStream("/com/example/imgs/reverso.jpg"))), 1, 0);
    }
}