package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.example.entidades.Partida;
import com.example.entidades.Usuario;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class RegistroController implements Initializable {

    @FXML
    private ImageView imagenLogo;

    @FXML
    private TextField campoPaisReg;

    @FXML
    private TextField campoEmailReg;

    @FXML
    private TextField campoUserReg;

    @FXML
    private PasswordField campoContraseñaReg;

    @FXML
    private Label error;
    
    @FXML
    private Label marcaErrorPais;

    @FXML
    private Label marcaErrorEmail;

    @FXML
    private Label marcaErrorUser;

    @FXML
    private Label marcaErrorPasswd;

    private String pais;
    private String email;
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
    private void switchToLogin() throws IOException {
        App.setRoot("/com/example/vistas/login");
    }

    @FXML
    private void switchToMenuPrincipal() throws IOException {
        App.setRoot("/com/example/vistas/menusPrincipales/menuPrincipal");
    }

    @FXML
    private void guardarDatosRegistro() throws IOException {
        pais = campoPaisReg.getText();
        email = campoEmailReg.getText();
        user = campoUserReg.getText();
        passwd = campoContraseñaReg.getText();
        if (pais.isEmpty()) {
            error.setText("Debes rellenar el campo País");
            error.setVisible(true);
            marcaErrorPais.setVisible(true);
            marcaErrorEmail.setVisible(false);
            marcaErrorUser.setVisible(false);
            marcaErrorPasswd.setVisible(false);
        }
        else if (email.isEmpty()) {
            error.setText("Debes rellenar el campo Email");
            error.setVisible(true);
            marcaErrorPais.setVisible(false);
            marcaErrorEmail.setVisible(true);
            marcaErrorUser.setVisible(false);
            marcaErrorPasswd.setVisible(false);
        }
        else if (user.isEmpty()) {
            error.setText("Debes rellenar el campo Nombre de usuario");
            error.setVisible(true);
            marcaErrorPais.setVisible(false);
            marcaErrorEmail.setVisible(false);
            marcaErrorUser.setVisible(true);
            marcaErrorPasswd.setVisible(false);
        }
        else if (passwd.isEmpty()) {
            error.setText("Debes rellenar el campo Contraseña");
            error.setVisible(true);
            marcaErrorPais.setVisible(false);
            marcaErrorEmail.setVisible(false);
            marcaErrorUser.setVisible(false);
            marcaErrorPasswd.setVisible(true);
        }
        else if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            error.setText("Introduce un email válido");
            error.setVisible(true);
            marcaErrorPais.setVisible(false);
            marcaErrorEmail.setVisible(true);
            marcaErrorUser.setVisible(false);
            marcaErrorPasswd.setVisible(false);
        }
        else if (passwd.length() < 8) {
            error.setText("La contraseña debe ser mayor a 7 carácteres");
            error.setVisible(true);
            marcaErrorPais.setVisible(false);
            marcaErrorEmail.setVisible(false);
            marcaErrorUser.setVisible(false);
            marcaErrorPasswd.setVisible(true);
        }
        else if (!passwd.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+")) {
            error.setText("La contraseña debe contener al menos una mayúscula, una minúscula y un dígito");
            error.setVisible(true);
            marcaErrorPais.setVisible(false);
            marcaErrorEmail.setVisible(false);
            marcaErrorUser.setVisible(false);
            marcaErrorPasswd.setVisible(true);
        }
        else{
            if(agnadirUsuario()){
                switchToMenuPrincipal();
            }
            else {
                error.setText("Ya existe un usuario con este nombre");
                error.setVisible(true);
                marcaErrorUser.setVisible(true);
                marcaErrorPasswd.setVisible(false);
                marcaErrorPais.setVisible(false);
                marcaErrorEmail.setVisible(false);
            }
        }
    }

    /**
     * Añade el usuario registrado a la Base de Datos mediante un JSON
     */
    @SuppressWarnings("unchecked")
    private boolean agnadirUsuario() {

        Unirest.setTimeouts(0, 0);

        try {
            JSONObject usuarioJson = new JSONObject();
            usuarioJson.put("nombre", user);
            usuarioJson.put("pais", pais);
            usuarioJson.put("email", email);
        
            JSONObject loginJson = new JSONObject();
            String hashPasswd = BCrypt.hashpw(passwd, BCrypt.gensalt());
            loginJson.put("hashPasswd", hashPasswd); 
        
            JSONObject requestBody = new JSONObject();
            requestBody.put("usuario", usuarioJson);
            requestBody.put("login", loginJson);
        
            HttpResponse<JsonNode> response = Unirest.post(App.ip + "/usuarios/newUsuario")
                    .header("Content-Type", "application/json")
                    .body(requestBody.toString())
                    .asJson();

            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(response.getBody().toString());
            System.out.println(root.toString());
            JSONObject datos = (JSONObject) root.get("datos");
            System.out.println(datos.toString());

            Usuario aux = new Usuario();

            aux.setId((String) datos.get("id"));
            aux.setNombre((String) datos.get("nombre"));
            aux.setEmail((String) datos.get("email"));
            aux.setDinero(((Long) datos.get("fichas")).intValue());
            aux.setPais((String) datos.get("pais"));
            aux.setAmigos((List<Usuario>) datos.get("amigos"));
            aux.setPartidas((List<Partida>) datos.get("partidas"));
            // Usar el objeto usuario como necesites
            App.usuario = aux;
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}