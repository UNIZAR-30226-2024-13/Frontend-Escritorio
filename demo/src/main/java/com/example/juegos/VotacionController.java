package com.example.juegos;

import com.example.App;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;


public class VotacionController implements Initializable {

    @FXML
    private Label votacionLabel;
    
    @FXML
    private Label votacionCumplida;

    @FXML
    private Button botonSalir;

    private int votosRealizados = 1;
    private int totalVotos = 4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Iniciar el hilo de actualización
        iniciarHiloDeActualizacion();
    }

    private void iniciarHiloDeActualizacion() {
        votacionLabel.setText("Han votado: " + votosRealizados + "/" + totalVotos);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(2), event -> {
                    if (votosRealizados == 3) {
                        votacionCumplida.setText("No se ha completado la votación");
                        botonSalir.setVisible(true);
                    }
                    // Actualizar la etiqueta cada 2 segundos
                    else if (votosRealizados < totalVotos) {
                        votosRealizados++;
                        votacionLabel.setText("Han votado: " + votosRealizados + "/" + totalVotos);
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("/com/example/vistas/juegos/poker");
    }
}