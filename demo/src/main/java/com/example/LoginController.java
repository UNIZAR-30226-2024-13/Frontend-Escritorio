package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.example.entidades.Partida;
import com.example.entidades.Usuario;
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
    @SuppressWarnings("unchecked")
    private boolean comprobarLogin(){
        try {
            String hashPasswd = BCrypt.hashpw(passwd, App.salt);
            JSONObject usuarioJson = new JSONObject();
            usuarioJson.put("usuario", user);
            usuarioJson.put("hashPasswd", hashPasswd);

            HttpResponse<JsonNode> response = Unirest.post(App.ip + "/usuarios/login")
                    .header("Content-Type", "application/json")
                    .body(usuarioJson.toString())
                    .asJson();
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(response.getBody().toString());
            JSONObject datos = (JSONObject) root.get("datos");
            JSONObject datosUsuario = (JSONObject) datos.get("usuario");
            JSONObject datosSesion = (JSONObject) datos.get("sessionToken");

            Usuario aux = new Usuario();

            aux.setId((String) datosUsuario.get("id"));
            aux.setNombre((String) datosUsuario.get("nombre"));
            aux.setEmail((String) datosUsuario.get("email"));
            aux.setDinero(((Long) datosUsuario.get("fichas")).intValue());
            aux.setPais((String) datosUsuario.get("pais"));

            JSONArray jsonArray = (JSONArray)datosUsuario.get("amigos");
            if(jsonArray != null && !jsonArray.isEmpty()){
                List<Usuario> listaAux = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    String nombreAmigo = (String) jsonObject.get("nombre");
                    Usuario amigoAux = new Usuario();
                    amigoAux.setNombre(nombreAmigo);
                    listaAux.add(amigoAux);
                }
                aux.setAmigos(listaAux);
            }

            jsonArray = (JSONArray)datos.get("partidas");
            if(jsonArray != null && !jsonArray.isEmpty()){
                List<Partida> listaPAux = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    String iPartida = (String) jsonObject.get("partidas");
                    Partida partidaAux = new Partida();
                    partidaAux.setId(iPartida);
                    listaPAux.add(partidaAux);
                }
                aux.setPartidas(listaPAux);
            }
            App.usuario = aux;

            App.tokenSesion = (String) datosSesion.get("sessionToken");

            return true;
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}