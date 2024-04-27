package com.example.juegos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.App;
import com.example.CartaUno;
import com.example.juegos.VentanaMentirosoController;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class UnoController implements Initializable{

    @FXML
    private GridPane cartasMesa;

    @FXML
    private GridPane cartas;

    @FXML
    private Label jugadaAnterior;

    private int filaCartas = 0;

    private int contCartas = 0;

    private int vieneDePoner = 0;

    @FXML
    private GridPane cartasUsuario2;

    @FXML
    private GridPane cartasUsuario3;
    
    @FXML
    private GridPane cartasUsuario4;

    private List<CartaUno> listaCartas = new ArrayList<>();

    private List<CartaUno> mazoPrueba = new ArrayList<>();

    private Button botonSeleccionado = new Button();

    private CartaUno cartaSeleccionada = new CartaUno();
    
    private List<Integer> columnasCartas = new ArrayList<>();

    private List<Integer> filasCartas = new ArrayList<>();

   
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            /**
             * TODO: Conectarse al servidor, recibir las manos
             * Iterar sobre las manos y añadir a cada caja el contenido individual
             * Cerrar las conexiones y manejar errores
             */
            int n = 0;
            while (n < 8) { 
                //  Añadir las cartas a la lista, parseando lo recibido
                listaCartas.add(new CartaUno(n, 2));
                mazoPrueba.add(new CartaUno(n, 0));
                n++;
            }


            // Eliminar lo que hubiera antes y crear botones para cada carta
            n = 0;
            int m = 0;
            cartas.getChildren().clear();
            cartasMesa.getChildren().clear();
            for (CartaUno carta : listaCartas) {
                Button boton = new Button(carta.toString());
                boton.getStyleClass().add("carta-button");
                boton.setOnAction(event -> {
                    botonSeleccionado = boton;
                    cartaSeleccionada = carta;
                    ponerCarta();
                });
                if (n == 8) {
                    filaCartas++;
                    n=0;
                }
                if (contCartas == listaCartas.size() - 1) {
                    cartasMesa.add(boton, 0, 0);
                }
                else {
                    cartas.add(boton, n, filaCartas);
                    ImageView imagenRev = new ImageView();

                    Image imagen = new Image(getClass().getResourceAsStream("/com/example/imgs/reverso.jpg"));
            
                    imagenRev.setImage(imagen);
                    imagenRev.setFitWidth(40);
                    imagenRev.setFitHeight(60);
    
                    ImageView imagenRev2 = new ImageView();
            
                    imagenRev2.setImage(imagen);
                    imagenRev2.setFitWidth(40);
                    imagenRev2.setFitHeight(60);
    
                    ImageView imagenRev3 = new ImageView();
            
                    imagenRev3.setImage(imagen);
                    imagenRev3.setFitWidth(40);
                    imagenRev3.setFitHeight(60);
    
                    cartasUsuario2.add(imagenRev, m, 0);
                    cartasUsuario3.add(imagenRev2, 0, m);
                    cartasUsuario4.add(imagenRev3, 0, m);
                }
                
                n++;
                m++;
                contCartas++;
            }
            columnasCartas.add(n-1);
            filasCartas.add(filaCartas);
            cartas.setHgap(20);
            cartas.setVgap(20);
            cartasUsuario2.setHgap(30);
            cartasUsuario3.setVgap(3);
            cartasUsuario4.setVgap(3);
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

    private void ponerCarta() { 
        cartasMesa.getChildren().clear();
        Button button = new Button(cartaSeleccionada.toString());
        button.getStyleClass().add("carta-button");
        cartasMesa.add(button, 0, 0);
        cartas.getChildren().remove(botonSeleccionado);
        columnasCartas.add(cartas.getColumnIndex(botonSeleccionado));
        filasCartas.add(cartas.getRowIndex(botonSeleccionado));
        vieneDePoner++;
    }

    @FXML
    private void robarCarta() {
        CartaUno carta = mazoPrueba.get(0);
        mazoPrueba.remove(0);
        Button button = new Button(carta.toString());
        button.getStyleClass().add("carta-button");
        button.setOnAction(event -> {
            botonSeleccionado = button;
            cartaSeleccionada = carta;
            ponerCarta();
        });
        int columna = columnasCartas.get(columnasCartas.size() - 1);
        int fila = filasCartas.get(filasCartas.size() - 1);
        columnasCartas.remove(columnasCartas.size() - 1);
        filasCartas.remove(filasCartas.size() - 1);
        cartas.add(button, columna, fila);
        if (columna == 7) {
            fila++;
            columna = 0;
            columnasCartas.add(columna);
        }
        else {
            if (vieneDePoner <= 0) {
                columnasCartas.add(columna+1);
            }
        }
        if (vieneDePoner <= 0) {
            filasCartas.add(fila);
        }
        else {
            vieneDePoner--;
        }
    }
}