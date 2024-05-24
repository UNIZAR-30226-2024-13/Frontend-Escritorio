package com.example.juegos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.example.App;
import com.example.entidades.Partida;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
public class UnirMentirosoController implements Initializable{

    @FXML
    private VBox opcionesVBox;

    @FXML
    private TextField campoPrivadas;

    @FXML
    private TableView<Partida> tablaPartidas;
    
    @FXML
    private TableColumn<Partida, String> columnaId;
    
    @FXML
    private TableColumn<Partida, String> columnaJugadores;
    
    @FXML
    private Label labelFichas;
    
    private ObservableList<Partida> partidas;
    private boolean opcionesVisible = false;

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        labelFichas.setText(App.usuario.getDinero() + " Fichas");
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaJugadores.setCellValueFactory(new PropertyValueFactory<>("jugadores"));
        colocarPartidas();
    }

    private void colocarPartidas(){
        List<Partida> partidasBD = new ArrayList<Partida>();

        try {
            HttpResponse<JsonNode> response = Unirest.get(App.ip + "/juegos/mentiroso/getMentirosos?usuarioSesion=" + App.usuario.getNombre() +
                                                 "&sessionToken=" + App.tokenSesion).asJson();
            
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(response.getBody().toString());
            JSONArray datos = (JSONArray) root.get("datos");
            for (int i = 0; i < datos.size(); i++) {
                JSONObject partida = (JSONObject) datos.get(i);
                Partida partidaAux = new Partida();
                partidaAux.setId((String) partida.get("id"));
                
                JSONArray guarda = (JSONArray) partida.get("guarda");
                partidaAux.setJugadores(guarda.size());
                
                partidasBD.add(partidaAux);
            }
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        partidas = FXCollections.observableArrayList(partidasBD);
        tablaPartidas.setItems(partidas);
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
    private void unirPartida() throws IOException {
        String id = tablaPartidas.getSelectionModel().getSelectedItem().getId();
        try {
            HttpResponse<JsonNode> response = Unirest.post(App.ip + "/juegos/mentiroso/" + id + "/addUsuario")
                .field("nombreUsuario", App.usuario.getNombre())
                .field("usuarioSesion", App.usuario.getNombre())
                .field("sessionToken", App.tokenSesion)
                .asJson();

            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(response.getBody().toString());
            boolean estado = (boolean) root.get("status");
            if(estado){
                App.partida.setId(id);
                App.setRoot("/com/example/vistas/juegos/mentiroso");
            }
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    private void unirPartidaPrivada() throws IOException {
        try {
            HttpResponse<JsonNode> response = Unirest.post(App.ip + "/juegos/mentiroso/" + campoPrivadas.getText() + "/addUsuario")
                .field("nombreUsuario", App.usuario.getNombre())
                .field("usuarioSesion", App.usuario.getNombre())
                .field("sessionToken", App.tokenSesion)
                .asJson();

            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(response.getBody().toString());
            boolean estado = (boolean) root.get("status");
            if(estado){
                App.partida.setId(campoPrivadas.getText());
                App.setRoot("/com/example/vistas/juegos/mentiroso");
            }
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelar() throws IOException {
        App.setRoot("/com/example/vistas/menusPrincipales/menuPrincipal");
    }
}
