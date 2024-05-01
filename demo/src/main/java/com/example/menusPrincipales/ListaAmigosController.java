package com.example.menusPrincipales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.json.simple.JSONObject;

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
        try {
            URL url = new URL(App.host + "/usuarios/getUsuario?value="+ "" + "&tipo=byNombre");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true); 
            // Leer la respuesta del servidor
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder amigoString = new StringBuilder();
                String amigoLine;
                while ((amigoLine = br.readLine()) != null) {
                    amigoString.append(amigoLine.trim());
                    Usuario amigo = new Usuario();
                    /**
                     * TODO: Buscar en la BD por nombre y añadirlos
                     */
                    amigo.setNombre(amigoLine);
                    amigos.add(amigo);
                }
                System.out.println("Amigo de la lista: " + amigoString.toString());
            }
                //TODO:  Añadir la info de la BD
            //amigos.add();
        } catch (MalformedURLException e) {
            // Manejar la excepción de URL mal formada
            e.printStackTrace();
        } catch (IOException e) {
            // Manejar la excepción de entrada/salida, que incluye la conexión rechazada
            e.printStackTrace();
        } catch (Exception e) {
            // Manejar otras excepciones no previstas
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
    private void agregarAmigo(ActionEvent event){
        Usuario amigo = new Usuario();
        amigo.setNombre(nombreAmigo.getText());
        try {
            URL url = new URL(App.host + "/usuarios/addAmigo?idUsuario="+ "" + "&idAmigo=");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true); 
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject parametros = new JSONObject();

            parametros.put("idUsuario", App.Usuario.getId());
            parametros.put("idAmigo", amigo.getNombre());

            try (OutputStream os = conn.getOutputStream()) {
                String jsonString = parametros.toJSONString();
                byte[] input = jsonString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }    
            amigos.add(amigo);
            tablaAmigos.setItems(amigos);

        } catch (MalformedURLException e) {
            // Manejar la excepción de URL mal formada
            e.printStackTrace();
        } catch (IOException e) {
            // Manejar la excepción de entrada/salida, que incluye la conexión rechazada
            e.printStackTrace();
        } catch (Exception e) {
            // Manejar otras excepciones no previstas
        }
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
