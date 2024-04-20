package com.example.menusPrincipales;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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
    private TableColumn columnaNombre;
    
    @FXML
    private TableColumn columnaPais;
    
    @FXML
    private TableColumn columnaDinero;
    
    
    private ObservableList<Usuario> amigos;
    private int posicionTabla;
    private boolean opcionesVisible = false;

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("columnaNombre"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("columnaPais"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("columnaDinero"));
        amigos = FXCollections.observableArrayList();
        tablaAmigos.setItems(amigos);

        try {
            URL url = new URL(App.ip + "");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int respuesta = conn.getResponseCode();
            if(respuesta != 200){
                throw new RuntimeException("Error de codigo " + respuesta);
            }else{
                StringBuilder informacion = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informacion.append(scanner.nextLine());
                }
                scanner.close();

                /** Conectar bien y añadir los amigos */
                tablaAmigos.setItems(amigos);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    private void switchToPerfil() throws IOException {
        App.setRoot("/com/example/vistas/peerfil/perfil");
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
    }

    @FXML
    private void eliminarAmigo(ActionEvent event){
        Usuario amigo = new Usuario();
        /**
         * TODO: Buscar en la BD por nombre y añadirlos
         */
        amigo.setNombre(nombreAmigo.getText());
        amigos.remove(amigo);
    }
}
