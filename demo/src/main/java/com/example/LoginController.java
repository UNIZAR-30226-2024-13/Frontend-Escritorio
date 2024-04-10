package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController implements Initializable{

    @FXML
    private TextField campoUser;

    @FXML
    private TextField campoContrasegna;

    @FXML
    private ImageView imagenLogo;

    @FXML
    private Label errorUser;

    @FXML
    private Label errorPasswd;

    private boolean errorUserVisible = false;

    private boolean errorPasswdVisible = false;
    

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
    private void guardarDatosLogin() throws IOException {
        String user = campoUser.getText();
        String passwd = campoContrasegna.getText();
        if (user.isEmpty()) {
            errorUser.setText("Debes rellenar el campo Nombre de usuario");
            errorPasswd.setVisible(false);
            errorPasswd.setManaged(false);
            errorUser.setVisible(true);
            errorUser.setManaged(true);
        }
        else if (passwd.isEmpty()) {
            errorPasswd.setText("Debes rellenar el campo Contraseña");
            errorUser.setVisible(false);
            errorUser.setManaged(false);
            errorPasswd.setVisible(true);
            errorPasswd.setManaged(true);
        }
        else if (passwd.length() < 8) {
            errorPasswd.setText("La contraseña debe tener más de 8 carácteres");
            errorUser.setVisible(false);
            errorUser.setManaged(false);
            errorPasswd.setVisible(true);
            errorPasswd.setManaged(true);
        }
        else if (!passwd.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+")) {
            errorPasswd.setText("La contraseña debe contener letras y números");
            errorUser.setVisible(false);
            errorUser.setManaged(false);
            errorPasswd.setVisible(true);
            errorPasswd.setManaged(true);
        }
        else{
            // Comprobar si el usuario esta en la BD
            switchToMenuPrincipal();
        }
    }
}
