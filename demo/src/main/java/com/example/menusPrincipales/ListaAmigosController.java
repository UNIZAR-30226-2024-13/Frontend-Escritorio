package com.example.menusPrincipales;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.example.App;
import com.example.Usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    
    
    private ObservableList<Usuario> amigos;
    private boolean opcionesVisible = false;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        amigos = FXCollections.observableArrayList();

        /** Conectar bien y mostrar los amigos que ya estan en la BD*/
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
    private void agregarAmigo(ActionEvent event){
        Usuario amigo = new Usuario();
        /**
         * TODO: Buscar en la BD por nombre y añadirlos
         */
        amigo.setNombre(nombreAmigo.getText());
        amigos.add(amigo);
        tablaAmigos.setItems(amigos);
    }

    @FXML
    private void eliminarAmigo(ActionEvent event){
        Usuario amigo = new Usuario();
        /**
         * TODO: Buscar en la BD por nombre y eliminarlo
         */
        amigo.setNombre(nombreAmigo.getText());
        List<Usuario> listaAmigos = amigos;
        for (Usuario usuario : listaAmigos) {
            if (amigo.getNombre().equals(usuario.getNombre())) {
                amigos.remove(usuario);
            }
        }
        tablaAmigos.setItems(amigos);
    }
}
