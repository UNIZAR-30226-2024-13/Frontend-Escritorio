package com.example.juegos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.App;
import com.example.Carta;

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
    private GridPane escaleraCopas;

    @FXML
    private GridPane escaleraEspadas;

    @FXML
    private GridPane escaleraBastos;

    @FXML
    private Button botonCarta1;

    @FXML
    private Button botonCarta2;

    @FXML
    private Button botonCarta3;

    @FXML
    private Button botonCarta4;

    @FXML
    private Button botonCarta5;

    @FXML
    private Button botonCarta6;

    @FXML
    private Button botonCarta7;

    @FXML
    private Button botonCarta8;

    @FXML
    private Button botonCarta9;

    @FXML
    private Button botonCarta10;

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

    private void ponerCarta(int posicionCartaMano){
        Carta carta = manoUsuario.get(posicionCartaMano); 
        int fila = carta.getNumero();
        
        switch (carta.getPalo()) {
            case Carta.OROS:
                escaleraOros.add(new Label("pene"), 0, fila);
                escaleraOros.add(new ImageView(new Image(getClass().getResourceAsStream("/com/example/imgs/reverso.jpg"))), 1, fila);
                break;
        
            case Carta.COPAS:
                escaleraCopas.add(new Label("pene"), 0, fila);
                escaleraCopas.add(new ImageView(new Image(getClass().getResourceAsStream("/com/example/imgs/reverso.jpg"))), 1, fila);
                break;

            case Carta.ESPADAS:
                escaleraEspadas.add(new Label("pene"), 0, fila);
                escaleraEspadas.add(new ImageView(new Image(getClass().getResourceAsStream("/com/example/imgs/reverso.jpg"))), 1, fila);
                break;

            case Carta.BASTOS:
                escaleraBastos.add(new Label("pene"), 0, fila);
                escaleraBastos.add(new ImageView(new Image(getClass().getResourceAsStream("/com/example/imgs/reverso.jpg"))), 1, fila);
                break;
        }
    }

    @FXML
    private void ponerCarta1(){
        botonCarta1.setVisible(false);
        ponerCarta(0);
    }

    @FXML
    private void ponerCarta2(){
        botonCarta2.setVisible(false);
        ponerCarta(1);
    }

    @FXML
    private void ponerCarta3(){
        botonCarta3.setVisible(false);
        ponerCarta(2);
    }

    @FXML
    private void ponerCarta4(){
        botonCarta4.setVisible(false);
        ponerCarta(3);
    }

    @FXML
    private void ponerCarta5(){
        botonCarta5.setVisible(false);
        ponerCarta(4);
    }

    @FXML
    private void ponerCarta6(){
        botonCarta6.setVisible(false);
        ponerCarta(5);
    }

    @FXML
    private void ponerCarta7(){
        botonCarta7.setVisible(false);
        ponerCarta(6);
    }

    @FXML
    private void ponerCarta8(){
        botonCarta8.setVisible(false);
        ponerCarta(7);
    }

    @FXML
    private void ponerCarta9(){
        botonCarta9.setVisible(false);
        ponerCarta(8);
    }

    @FXML
    private void ponerCarta10(){
        botonCarta10.setVisible(false);
        ponerCarta(9);
    }
}