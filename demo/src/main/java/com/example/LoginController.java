package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController implements Initializable{

    @FXML
    private TextField campoUser;

    @FXML
    private TextField campoContrasegna;

    @FXML
    private ImageView imagenLogo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Cargar la imagen desde el archivo
        Image imagen = new Image(getClass().getResourceAsStream("/com/example/imgs/logo.jpg"));
        
        // Establecer la imagen en el ImageView
        imagenLogo.setImage(imagen);
    }

    @FXML
    private void switchToRegistro() throws IOException {
        App.setRoot("/com/example/vistas/registro");
    }

    @FXML
    private void switchToMenuPrincipal() throws IOException {
        App.setRoot("/com/example/vistas/menuPrincipal");
    }

    @FXML
    private void guardarDatosLogin() {
        String user = campoUser.getText();
        String passwd = campoContrasegna.getText();
        if (user.isEmpty()) {
            // Error campo email vacio
        }
        else if (passwd.isEmpty()) {
            // Error campo contraseña vacio
        }
        else if (passwd.length() < 8) {
            // Error contraseña menor de 8 char 
        }
        else if (passwd.matches("[a-zA-Z0-9]+")) {
            // Error contraseña no alfanumerica
        }
        else{
            // Comprobar si el usuario esta en la BD
        }
    }
}
