package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController implements Initializable{

    @FXML
    private TextField campoUser;

    @FXML
    private PasswordField campoContrasegna;

    @FXML
    private ImageView imagenLogo;

    @FXML
    private Label errorUser;

    @FXML
    private Label errorPasswd;

    @FXML
    private Label marcaErrorUser;

    @FXML
    private Label marcaErrorPasswd;

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
            errorUser.setVisible(true);
            marcaErrorPasswd.setVisible(false);
            marcaErrorUser.setVisible(true);
        }
        else if (passwd.isEmpty()) {
            errorPasswd.setText("Debes rellenar el campo Contraseña");
            errorUser.setVisible(false);
            errorPasswd.setVisible(true);
            marcaErrorPasswd.setVisible(true);
            marcaErrorUser.setVisible(false);
        }
        else{
            // Comprobar si el usuario esta en la BD
            switchToMenuPrincipal();
        }
    }
}
