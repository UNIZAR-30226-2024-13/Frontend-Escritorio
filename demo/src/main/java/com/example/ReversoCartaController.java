package com.example;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ReversoCartaController implements Initializable {

    @FXML
    private Label numeroCarta;

    @FXML
    private ImageView imagenReversoCarta;
        
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @SuppressWarnings("exports")
    public void setImagen(Image imagen) {
        imagenReversoCarta.setImage(imagen);
    }
}
