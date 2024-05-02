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
    @SuppressWarnings("unchecked")
    private boolean comprobarLogin(){
        try {
            URL url = new URL(App.ip + "");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                
                JSONObject jsonResponse = new JSONObject();
                String id = (String)jsonResponse.get("id");
                String nombre = (String)jsonResponse.get("nombre");
                String email = (String)jsonResponse.get("email");
                int dinero = (Integer)jsonResponse.get("fichas");
                String pais = (String)jsonResponse.get("pais");
                List<Usuario> amigos = (List<Usuario>)jsonResponse.get("amigos");
                List<Partida> partidas = (List<Partida>)jsonResponse.get("partidas");
                
                
                Usuario usuario = new Usuario(id, nombre, email, dinero, pais, amigos, partidas); 
                App.usuario = usuario;

                conn.disconnect();
                return true;
            }
        } catch (IOException e) {
            // Manejar la excepción de entrada/salida
            e.printStackTrace();
        } catch (Exception e) {
            // Manejar otras excepciones no previstas
            e.printStackTrace();
        }

        return false;
    }
}