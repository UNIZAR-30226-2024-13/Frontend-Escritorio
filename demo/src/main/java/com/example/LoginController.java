package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        App.setRoot("/com/example/vistas/menusPrincipales/menuPrincipal");
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
            if(comprobarLogin()){
                switchToMenuPrincipal();
            } else {
                errorPasswd.setText("Los datos introducidos no coinciden con lo esperado");
                errorUser.setVisible(true);
                marcaErrorUser.setVisible(true);
                errorPasswd.setVisible(true);
                marcaErrorPasswd.setVisible(true);
            }
        }
    }

    /**
     * Compara los datos introducidos en las casillas de texto con los recibidos de la base de datos
     * @return - Devuelve verdadero si los datos coinciden
     */
    private boolean comprobarLogin(){
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get(App.ip + "").asJson();
            App.usuario = new Gson().fromJson(apiResponse.getBody().toString(), Usuario.class); 
            return true;
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}