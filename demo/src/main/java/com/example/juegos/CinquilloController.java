package com.example.juegos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.App;
import com.example.Carta;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class CinquilloController implements Initializable{


    @FXML
    private GridPane escaleraOros;

    @FXML
    private GridPane escaleraCopas;

    @FXML
    private GridPane escaleraEspadas;

    @FXML
    private GridPane escaleraBastos;

    @FXML
    private List<GridPane> botones;
   
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        botones = new ArrayList<>();
        botones.get(0).addEventFilter(MouseEvent.MOUSE_CLICKED, (patata)->{
            botones.get(0).setVisible(false);
            ponerCarta(0);
        });
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("/com/example/vistas/menusPrincipales/menuPrincipal");
    }

    @FXML
    private void pausarPartida() throws IOException {
        // TODO : Iniciar votacion o votar si / no
    }

    private void ponerCarta(int posicionCartaMano){
        Carta carta = manoUsuario.get(posicionCartaMano);
        int fila = carta.getNumero();
        
        Label label = new Label("" + carta.getNumero());
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: black;");

        switch (carta.getPalo()) {
            case Carta.OROS:
                escaleraOros.add(label, 0, fila);
                escaleraOros.add(new ImageView(new Image(getClass().getResourceAsStream("/com/example/imgs/reverso.jpg"))), 1, fila);
                break;
        
            case Carta.COPAS:
                escaleraCopas.add(label, 0, fila);
                escaleraCopas.add(new ImageView(new Image(getClass().getResourceAsStream("/com/example/imgs/reverso.jpg"))), 1, fila);
                break;

            case Carta.ESPADAS:
                escaleraEspadas.add(label, 0, fila);
                escaleraEspadas.add(new ImageView(new Image(getClass().getResourceAsStream("/com/example/imgs/reverso.jpg"))), 1, fila);
                break;

            case Carta.BASTOS:
                escaleraBastos.add(label, 0, fila);
                escaleraBastos.add(new ImageView(new Image(getClass().getResourceAsStream("/com/example/imgs/reverso.jpg"))), 1, fila);
                break;
        }
    }
}