package com.example.juegos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.App;

import com.example.Carta;
import com.example.ReversoCartaController;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class PokerController implements Initializable{

    @FXML
    private VBox opcionesVBox;

    @FXML
    private Carta cincoOrosController;

    @FXML
    private Label etiquetaMensajeAbandono;

    @FXML
    private VBox cuartaCentral;

    @FXML
    private VBox quintaCentral;

    private boolean opcionesVisible = false;

    private boolean cuartaCarta = true;

    @FXML
    private Carta cartaController = new Carta();

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

    public void configurarCarta() throws IOException {
        Image imagen = new Image(getClass().getResourceAsStream("/com/example/imgs/reverso.jpg"));
        cartaController.setNumero("1");
        cartaController.setImagen(imagen);
    }

    @FXML
    public void ponerCarta(ActionEvent event) {
        if (cuartaCarta) {
            cuartaCentral.setVisible(true);
            cuartaCentral.setManaged(true);
            cuartaCarta = false;
        }
        else {
            quintaCentral.setVisible(true);
            quintaCentral.setManaged(true);
        }
    }
}
