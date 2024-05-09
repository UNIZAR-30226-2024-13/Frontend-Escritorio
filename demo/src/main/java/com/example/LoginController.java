package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.JSONObject;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.example.entidades.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
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
    
    private String user;

    private String passwd;

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
        user = campoUser.getText();
        passwd = campoContrasegna.getText();
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
            String hashPasswd = BCrypt.hashpw(passwd, BCrypt.gensalt());
            System.out.println(hashPasswd);
            JSONObject usuarioJson = new JSONObject();
            usuarioJson.put("usuario", user);
            usuarioJson.put("hashPasswd", hashPasswd);

            HttpResponse<JsonNode> response = Unirest.post(App.ip + "/usuarios/login")
            .header("Content-Type", "application/json")
            .body(usuarioJson.toString())
            .asJson();
            JsonNode datosNode = response.getBody();
            System.out.println(datosNode.toString());
            Gson gson = new Gson();
            App.usuario = gson.fromJson(response.getBody().toString(), Usuario.class);
            System.out.println(App.usuario.getNombre());
            return true;
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}