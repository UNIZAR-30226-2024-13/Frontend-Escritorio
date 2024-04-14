package com.example.juegos;

import java.io.IOException;

import com.example.CartaController;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class CinquilloController {

    @FXML
    private VBox opcionesVBox;

    @FXML
    private Label etiquetaMensajeAbandono;

    private boolean opcionesVisible = false;
    private CartaController cartaController = new CartaController();

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
}
