package com.example.juegos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.App;

import com.example.CartaController;
import com.example.ReversoCartaController;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class CinquilloController implements Initializable{

    @FXML
    private VBox opcionesVBox;

    @FXML
    private CartaController cincoOrosController;

    @FXML
    private Label etiquetaMensajeAbandono;

    @FXML
    private GridPane unoOros;

    @FXML
    private GridPane dosOros;
    
    @FXML
    private GridPane tresOros;

    @FXML
    private GridPane cuatroOros;

    @FXML
    private GridPane cincoOros;

    @FXML
    private GridPane seisOros;

    @FXML
    private GridPane sieteOros;

    @FXML
    private GridPane sotaOros;

    @FXML
    private GridPane caballoOros;

    @FXML
    private GridPane reyOros;

    private boolean opcionesVisible = false;
    private CartaController cartaController = new CartaController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("/com/example/vistas/menusPrincipales/menuPrincipal");
    }

    /**
     * Hace visible una etiqueta con un mensaje, pasados 5 segundos la vuelve a ocultar
     * @throws IOException
     */
    @FXML
    private void mostrarMensajeAbandono() throws IOException {
        etiquetaMensajeAbandono.setManaged(true);
        etiquetaMensajeAbandono.setVisible(true);

        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                etiquetaMensajeAbandono.setManaged(false);
                etiquetaMensajeAbandono.setVisible(false);
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    
    @FXML
    private void mostrarOcultarOpciones(ActionEvent event) {
        opcionesVisible = !opcionesVisible;
        opcionesVBox.setManaged(opcionesVisible);
        opcionesVBox.setVisible(opcionesVisible);
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

    @FXML
    public void ponerCarta(ActionEvent event) {
        cincoOros.setVisible(true);
    }
}
