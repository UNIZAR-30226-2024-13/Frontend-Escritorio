package com.example.juegos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.App;
import com.example.entidades.Carta;

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
    private GridPane cartas;
    
    @FXML
    private GridPane cartasUsuario2;

    @FXML
    private GridPane cartasUsuario3;
    
    @FXML
    private GridPane cartasUsuario4;

    private List<Carta> listaCartas = new ArrayList<>();
   
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            /**
             * TODO: Conectarse al servidor, recibir las manos
             * Iterar sobre las manos y añadir a cada caja el contenido individual
             * Cerrar las conexiones y manejar errores
             */
            int n = 0;
            while (n < 10) { 
                //  Añadir las cartas a la lista, parseando lo recibido
                listaCartas.add(new Carta(n, 2));
                n++;
            }

            // Eliminar lo que hubiera antes y crear botones para cada carta
            n = 0;
            cartas.getChildren().clear();
            for (Carta carta : listaCartas) {
                Button boton = new Button(carta.toString());
                boton.getStyleClass().add(App.estiloCartas);
                boton.setOnAction(event -> {
                    ponerCarta(carta);
                    cartas.getChildren().remove(boton);
                });
                cartas.add(boton, n, 0);
                n++;

                ImageView imagenRev = new ImageView();
                Image imagen = new Image(getClass().getResourceAsStream("/com/example/imgs/reverso.jpg"));
        
                imagenRev.setImage(imagen);
                imagenRev.setFitWidth(40);
                imagenRev.setFitHeight(60);
                
                cartasUsuario2.add(imagenRev, n, 0);
                cartasUsuario3.add(imagenRev, 0, n);
                cartasUsuario4.add(imagenRev, 0, n);
            }
            cartas.setHgap(20);
            cartasUsuario2.setHgap(30);
            cartasUsuario3.setVgap(3);
            cartasUsuario4.setVgap(3);
            
            escaleraOros.setVgap(10);
            escaleraCopas.setVgap(10);
            escaleraEspadas.setVgap(10);
            escaleraBastos.setVgap(10);
        
        } catch (Exception e) {
        } finally{
        }
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("/com/example/vistas/menusPrincipales/menuPrincipal");
    }

    @FXML
    private void pausarPartida() throws IOException {
        // TODO : Iniciar votacion o votar si / no
    }

    private void ponerCarta(Carta carta){
        int fila = carta.getNumero();
        
        Label label = new Label(carta.toString());
        label.getStyleClass().add(App.estiloEscaleras);

        switch (carta.getPalo()) {
            case Carta.OROS:
                escaleraOros.add(label, 0, fila);
                break;
        
            case Carta.COPAS:
                escaleraCopas.add(label, 0, fila);
                break;

            case Carta.ESPADAS:
                escaleraEspadas.add(label, 0, fila);
                break;

            case Carta.BASTOS:
                escaleraBastos.add(label, 0, fila);
                break;
        }
    }
}