package com.example.menusPrincipales;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.example.App;
import com.example.entidades.Partida;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class MenuCrearController implements Initializable{
   
    @FXML
    private Button botonPublica;
    
    @FXML
    private Button botonPrivada;

    @FXML
    private VBox opcionesVBox;
    
    @FXML
    private Label labelFichas;

    private boolean opcionesVisible = false;
    private boolean passwdVisible = false;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        labelFichas.setText(App.usuario.getDinero() + " Fichas");
    }

    @FXML
    private void switchToPerfil() throws IOException {
        App.setRoot("/com/example/vistas/perfil/perfil");
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("/com/example/vistas/menusPrincipales/menuPrincipal");
    }
    
    @FXML
    private void mostrarOcultarOpciones(ActionEvent event) {
        opcionesVisible = !opcionesVisible;
        opcionesVBox.setManaged(opcionesVisible);
        opcionesVBox.setVisible(opcionesVisible);
    }
    
    @FXML
    private void partidaPrivada() throws IOException {
        passwdVisible = true;
        botonPublica.getStyleClass().remove("button-clicked");
        botonPrivada.getStyleClass().add("button-clicked");
    }

    @FXML
    private void partidaPublica() throws IOException {
        passwdVisible = false;
        botonPublica.getStyleClass().add("button-clicked");
        botonPrivada.getStyleClass().remove("button-clicked");
    }

    @FXML
    private void crearCinquillo() throws IOException {
        if(agnadirPartida("/juegos/cinquillo/addCinquillo")){
            App.setRoot("/com/example/vistas/juegos/crearCinquillo");
        }
    }

    @FXML
    private void crearMentiroso() throws IOException {
        if(agnadirPartida("/juegos/mentiroso/addMentiroso")){
            App.setRoot("/com/example/vistas/juegos/crearMentiroso");
        }
    }

    @FXML
    private void crearPoker() throws IOException {
        if(agnadirPartida("/juegos/poker/addPoker")){
            App.setRoot("/com/example/vistas/juegos/crearPoker");
        }
    }

    @FXML
    private void crearBlackJack() throws IOException {
        if(agnadirPartida("/juegos/blackJack/addBlackJack")){
            App.setRoot("/com/example/vistas/juegos/crearBlackJack");
        }
    }

    @FXML
    private void crearUNO() throws IOException {
        if(agnadirPartida("/juegos/uno/addUNO")){
            App.setRoot("/com/example/vistas/juegos/crearUNO");
        }
    }

    /**
     * Añade la partida creada a la Base de Datos mediante un JSON
     * @param apiJuego - Ruta de acceso con la terminacion para la llamada a la API
     * @return - True si se crea la partida de forma correcta
     */
    private boolean agnadirPartida(String apiJuego) {
        String llamada = App.ip + apiJuego;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.post(llamada)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("usuarioSesion", App.usuario.getNombre())
                .field("sessionToken", App.tokenSesion)
                .field("esPrivada", passwdVisible)
                .asJson();
                
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(apiResponse.getBody().toString());
            JSONObject datos = (JSONObject) root.get("datos");

            Partida partida = new Partida();
            partida.setId((String) datos.get("id"));
            partida.setTurno(((Long) datos.get("turno")).intValue());
            partida.setActiva((boolean) datos.get("activa"));
            partida.setPrivada((boolean) datos.get("privada"));
            
            App.partida = partida;
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}
