package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartaController implements Initializable {

    @FXML
    private Label numeroCarta;

    @FXML
    private ImageView imagenPaloCarta;
        
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setNumero(String numero) {
        numeroCarta.setText(numero);
    }

    public void setImagen(Image imagen) {
        imagenPaloCarta.setImage(imagen);
    }
}
