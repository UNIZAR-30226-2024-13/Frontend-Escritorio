package com.example.juegos;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class VentanaMentirosoController implements Initializable {
    @FXML
    private GridPane pane;

    private Stage stage;

    private int botonSeleccionado;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane.setHgap(5);
        for (int i = 1; i < 13; i++) {
            int boton = i;
            Button button = new Button(String.valueOf(boton));
            button.getStyleClass().add("ventana-button");
            button.setOnAction(event -> {
                botonSeleccionado = boton;
                closeWindow();
            });
            pane.add(button,i,0);
        }
    }

    // Método para cerrar la ventana
    private void closeWindow() {
        stage = (Stage) pane.getScene().getWindow(); // Obtiene el Stage de la ventana
        stage.close(); // Cierra la ventana
    }

    public int getBoton () {
        return botonSeleccionado;
    }
}
