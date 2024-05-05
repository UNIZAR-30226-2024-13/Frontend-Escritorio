package com.example.menusPrincipales;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.App;
import com.example.Usuario;
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

public class ListaAmigosController implements Initializable{

    @FXML
    private VBox opcionesVBox;

    @FXML
    private TextField nombreAmigo;

    @FXML
    private TableView<Usuario> tablaAmigos;
    
    @FXML
    private TableColumn<Usuario, String> columnaNombre;
    
    @FXML
    private Label labelFichas;
    
    private ObservableList<Usuario> amigos;
    private boolean opcionesVisible = false;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        labelFichas.setText(App.usuario.getDinero() + " Fichas");
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        amigos = FXCollections.observableArrayList(App.usuario.getAmigos());
        tablaAmigos.setItems(amigos);
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
    private boolean agregarAmigo(ActionEvent event){
        Usuario amigo = new Usuario();
        amigo.setNombre(nombreAmigo.getText());

        try {
            HttpResponse<JsonNode> response = Unirest.post(App.ip + "/usuarios/addAmigo?idUsuario=" + "" + "&idAmigo=")
                .field("idUsuario", App.usuario.getId())
                .field("idAmigo", amigo.getNombre())
                .asJson();

            HttpResponse<JsonNode> responseAmigo = Unirest.post(App.ip + "/usuarios/addAmigo?idUsuario=" + "" + "&idAmigo=")
                .field("idUsuario", amigo.getNombre())
                .field("idAmigo", App.usuario.getId())
                .asJson();

            amigos.add(amigo);
            tablaAmigos.setItems(amigos);
            return true;
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private boolean eliminarAmigo(ActionEvent event){
        Usuario amigo = new Usuario();
        amigo.setNombre(nombreAmigo.getText());

        try {
            HttpResponse<JsonNode> response = Unirest.post(App.ip + "/usuarios/deleteAmigo?idUsuario="+ "" + "&idAmigo=")
                .field("idUsuario", App.usuario.getId())
                .field("idAmigo", amigo.getNombre())
                .asJson();

            HttpResponse<JsonNode> responseAmigo = Unirest.delete(App.ip + "/usuarios/deleteAmigo?idUsuario="+ "" + "&idAmigo=")
                .field("idUsuario", amigo.getNombre())
                .field("idAmigo", App.usuario.getId())
                .asJson();

            amigos.add(amigo);
            tablaAmigos.setItems(amigos);
            return true;
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}
