package com.example.juegos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.App;
import com.example.Carta;
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


public class MentirosoController implements Initializable{

    @FXML
    private GridPane cartasMesa;

    @FXML
    private GridPane cartas;

    @FXML
    private Label jugadaAnterior;

    private int filaCartas = 0;

    private int contCartas = 0;

    private boolean primerTurno = true;

    private int numeroAJugar = 0;

    private List<Carta> listaCartas = new ArrayList<>();

    private List<Button> botonesSeleccionados = new ArrayList<>();

    private List<Carta> cartasSeleccionadas = new ArrayList<>();

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
                boton.getStyleClass().add("carta-button");
                boton.setOnAction(event -> {
                    boton.getStyleClass().remove("carta-button");
                    boton.getStyleClass().add("carta-button-clicked");
                    botonesSeleccionados.add(boton);
                    cartasSeleccionadas.add(carta);
                });
                if (contCartas == 8) {
                    filaCartas++;
                    contCartas = 0;
                    n=0;
                }
                cartas.add(boton, n, filaCartas);
                n++;
                contCartas++;
            }
            cartas.setHgap(20);
            cartas.setVgap(20);
            cartasMesa.setHgap(10);
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

    @FXML
    private void ponerCarta() throws IOException{ 
        for (int i = 0; i < cartasSeleccionadas.size(); i++) {
            Button button = new Button(cartasSeleccionadas.get(i).toString());
            button.getStyleClass().add("carta-button");
            cartasMesa.add(button, i, 0);
            cartas.getChildren().remove(botonesSeleccionados.get(i));
            contCartas--;
            columnasCartas.add(cartas.getColumnIndex(botonesSeleccionados.get(i)));
            filasCartas.add(cartas.getRowIndex(botonesSeleccionados.get(i)));
            if (contCartas == 7) {
                filaCartas--;
            }
        } 
        if (primerTurno) {
            pedirNumero();
            primerTurno = false;
        }     
    }

    private void pedirNumero() throws IOException{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/vistas/juegos/ventanaMentiroso.fxml"));
        Parent root = fxmlLoader.load();
        VentanaMentirosoController controller = fxmlLoader.getController(); // Obtener el controlador
        Scene scene = new Scene(root, 600, 300);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/imgs/logo.jpg")));
        stage.setScene(scene);
        stage.showAndWait(); // Esperar hasta que se cierre el nuevo Stage
        numeroAJugar = controller.getBoton(); // Obtener el valor del botón seleccionado
        jugadaAnterior.setText("Pareja de " + numeroAJugar);
        System.out.println("numero a jugar " + numeroAJugar);
    }


    @FXML
    private void levantarCarta() {
        for (int i = 0; i < cartasSeleccionadas.size(); i++) {
            Carta carta = cartasSeleccionadas.get(i);
            Button button = new Button(carta.toString());
            button.getStyleClass().add("carta-button");
            button.setOnAction(event -> {
                button.getStyleClass().remove("carta-button");
                button.getStyleClass().add("carta-button-clicked");
                botonesSeleccionados.add(button);
                cartasSeleccionadas.add(carta);
            });
            if (contCartas == 8) {
                filaCartas++;
                contCartas = 0;
            }
            cartas.add(button, columnasCartas.get(i), filasCartas.get(i));
            cartasMesa.getChildren().clear();
            contCartas++;
        }
        cartasSeleccionadas.clear();
        botonesSeleccionados.clear();
        columnasCartas.clear();
        filasCartas.clear();
    }
}