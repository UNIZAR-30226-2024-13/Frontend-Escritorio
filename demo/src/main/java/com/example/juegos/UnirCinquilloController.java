package com.example.juegos;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.example.App;
import com.example.Partida;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
public class UnirCinquilloController implements Initializable{
    
    @FXML
    private Button botonPublica;
    
    @FXML
    private Button botonPrivada;

    @FXML
    private VBox opcionesVBox;

    @FXML
    private Label etiquetaPasswd;

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
    private boolean passwdVisible = false;

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        labelFichas.setText(App.usuario.getDinero() + " Fichas");
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id_partida"));
        columnaJugadores.setCellValueFactory(new PropertyValueFactory<>("jugadores"));
        partidas = FXCollections.observableArrayList();
        Partida partida = new Partida("Partida_cinquillo_1", 4);
        partidas.add(partida);
        tablaPartidas.setItems(partidas);

        Partida partida2 = new Partida("Partida_cinquillo_2", 4);
        partidas.add(partida2);
        tablaPartidas.setItems(partidas);

        /** Conectar bien y mostrar los amigos que ya estan en la BD*/
        try {
            URL url = new URL(App.ip + "/usuarios/getUsuario?value="+ "" + "&tipo=byNombre");
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

                //TODO:  Añadir la info de la BD
                //amigos.add();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
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
        etiquetaPasswd.setManaged(passwdVisible);
        etiquetaPasswd.setVisible(passwdVisible);
        botonPublica.getStyleClass().remove("button-clicked");
        botonPrivada.getStyleClass().add("button-clicked");
    }

    @FXML
    private void partidaPublica() throws IOException {
        passwdVisible = false;
        etiquetaPasswd.setManaged(passwdVisible);
        etiquetaPasswd.setVisible(passwdVisible);
        botonPublica.getStyleClass().add("button-clicked");
        botonPrivada.getStyleClass().remove("button-clicked");
    }

    @FXML
    private void crearPartida() throws IOException {
        App.setRoot("/com/example/vistas/juegos/cinquillo");
    }

    @FXML
    private void cancelar() throws IOException {
        // TODO: Eliminar la partida de la BD
        App.setRoot("/com/example/vistas/menusPrincipales/menuPrincipal");
    }
}
