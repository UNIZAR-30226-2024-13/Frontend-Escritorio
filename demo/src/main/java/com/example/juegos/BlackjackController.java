package com.example.juegos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.App;
import com.example.Carta;
import com.example.CartaFrancesa;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class BlackjackController implements Initializable{


    @FXML
    private GridPane primeraCartaMesa;

    @FXML
    private GridPane segundaCartaMesa;

    @FXML
    private GridPane terceraCartaMesa;

    @FXML
    private GridPane cuartaCartaMesa;

    @FXML
    private GridPane quintaCartaMesa;

    @FXML
    private GridPane cartas;

    private List<CartaFrancesa> listaCartas = new ArrayList<>();

    private int numCartas= 0;
   
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            /**
             * TODO: Conectarse al servidor, recibir las manos
             * Iterar sobre las manos y añadir a cada caja el contenido individual
             * Cerrar las conexiones y manejar errores
             */
            int n = 0;
            while (n < 7) { 
                //  Añadir las cartas a la lista, parseando lo recibido
                listaCartas.add(new CartaFrancesa(n, 2));
                n++;
            }
            cartas.setHgap(10);
            // Eliminar lo que hubiera antes y crear botones para cada carta
            cartas.getChildren().clear();
            for (CartaFrancesa carta : listaCartas) {
                if (numCartas == 0 || numCartas == 1) {
                    Button boton = new Button(carta.toString());
                    boton.getStyleClass().add("carta-button");
                    cartas.add(boton, numCartas, 0);
                    numCartas++;
                }
            }
        
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
        App.setRoot("/com/example/vistas/juegos/votacion");
    }

    @FXML
    private void ponerCarta(){
        CartaFrancesa carta = new CartaFrancesa();
        carta = listaCartas.get(numCartas);
        Label label = new Label(carta.toString());
        label.getStyleClass().add("carta-button");
        cartas.add(label, numCartas, 0);
        numCartas++;
    }
}