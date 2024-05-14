package com.example.menusPrincipales;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.App;
import com.example.entidades.Partida;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class MenuCrearController implements Initializable{

    @FXML
    private VBox opcionesVBox;
    
    @FXML
    private Label labelFichas;

    private boolean opcionesVisible = false;

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
            HttpResponse<JsonNode> apiResponse = Unirest.post(llamada).asJson();
            Gson gson = new Gson();
            Partida partida = gson.fromJson(apiResponse.getBody().toString(), Partida.class);
            App.partidaPasswd = partida.getId();
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}
