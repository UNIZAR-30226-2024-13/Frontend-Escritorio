package com.example.juegos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.App;
import com.example.CartaController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class CinquilloController implements Initializable{

    @FXML
    private CartaController controller ;

    @FXML
    private Button botonCarta1;

    @FXML
    private GridPane cincoOros;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("/com/example/vistas/menusPrincipales/menuPrincipal");
    }

    @FXML
    private void pausarPartida() throws IOException {
        // TODO : Iniciar votacion o votar si / no
    }

    public void configurarCarta() {
        controller = obtenerControladorCarta();
        Image imagen = new Image(getClass().getResourceAsStream("/com/example/imgs/reverso.jpg"));
        controller.setNumero("1");
        controller.setImagen(imagen);
    }

    @SuppressWarnings("exports")
    @FXML
    public void ponerCarta(ActionEvent event) {
        configurarCarta();
        botonCarta1.setVisible(false);
        cincoOros.setVisible(true);
    }

    private CartaController obtenerControladorCarta() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vistas/carta.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loader.getController();
    }
}