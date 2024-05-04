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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PokerController implements Initializable{


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
    private GridPane cartasUsuario2;

    @FXML
    private GridPane cartasUsuario3;
    
    @FXML
    private GridPane cartasUsuario4;

    @FXML
    private GridPane cartas;

    private List<CartaFrancesa> listaCartas = new ArrayList<>();

    private boolean hay_cuarta = false;
   
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

            // Eliminar lo que hubiera antes y crear botones para cada carta
            n = 0;
            cartas.getChildren().clear();
            for (CartaFrancesa carta : listaCartas) {
                if (n == 2) {
                    Label label = new Label(carta.toString());
                    label.getStyleClass().add("cartas-escaleras");
                    primeraCartaMesa.add(label, 0, 0);
                }
                else if (n == 3) {
                    Label label = new Label(carta.toString());
                    label.getStyleClass().add("cartas-escaleras");
                    segundaCartaMesa.add(label, 1, 0);
                }
                else if (n == 4) {
                    Label label = new Label(carta.toString());
                    label.getStyleClass().add("cartas-escaleras");
                    terceraCartaMesa.add(label, 2, 0);
                }
                else if (n == 0 || n == 1) {
                    Button boton = new Button(carta.toString());
                    boton.getStyleClass().add("carta-button");
                    cartas.add(boton, n, 0);

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

                    cartasUsuario2.add(imagenRev, n, 0);
                    cartasUsuario3.add(imagenRev2, 0, n);
                    cartasUsuario4.add(imagenRev3, 0, n);
                }
                n++;
            }
            cartas.setHgap(20);
            cartasUsuario2.setHgap(30);
            cartasUsuario3.setVgap(30);
            cartasUsuario4.setVgap(30);
            primeraCartaMesa.setVgap(10);
            segundaCartaMesa.setVgap(10);
            terceraCartaMesa.setVgap(10);
            cuartaCartaMesa.setVgap(10);
            quintaCartaMesa.setVgap(10);
        
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
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/vistas/juegos/votacion.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 450);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/imgs/logo.jpg")));
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void ponerCarta(){
        CartaFrancesa carta = new CartaFrancesa();
        if (!hay_cuarta) {
            carta = listaCartas.get(5);
            Label label = new Label(carta.toString());
            label.getStyleClass().add("cartas-escaleras");
            cuartaCartaMesa.add(label, 3, 0);
            hay_cuarta = true;
        }
        else {
            carta = listaCartas.get(6);
            Label label = new Label(carta.toString());
            label.getStyleClass().add("cartas-escaleras");
            quintaCartaMesa.add(label, 4, 0);
        }
    }
}