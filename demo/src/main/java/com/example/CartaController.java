package com.example;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class CartaController implements Initializable {

    @FXML
    private Label numeroCarta;

    @FXML
    private ImageView imagenPaloCarta;

    @FXML
    private GridPane cartaPane;
        
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setNumero(String numero) {
        numeroCarta.setText(numero);
    }

    @SuppressWarnings("exports")
    public void setImagen(Image imagen) {
        imagenPaloCarta.setImage(imagen);
    }

    public void setVisible(boolean visible) {
        cartaPane.setVisible(visible);
    }
}
