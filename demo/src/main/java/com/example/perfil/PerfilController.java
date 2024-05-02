package com.example.perfil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PerfilController implements Initializable{

    @FXML
    private VBox opcionesVBox;
    
    @FXML
    private Label labelFichas;

    private boolean opcionesVisible = false;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
    private void switchToCambiarUsuario() throws IOException {
        App.setRoot("/com/example/vistas/perfil/cambiarUsuario");
    }

    @FXML
    private void switchToCambiarContrasegna() throws IOException {
        App.setRoot("/com/example/vistas/perfil/cambiarContrasegna");
    }

    @FXML
    private void switchToCambiarFoto() throws IOException {
        App.setRoot("/com/example/vistas/perfil/cambiarFoto");
    }

    @FXML
    private void mostrarOcultarOpciones(ActionEvent event) {
        opcionesVisible = !opcionesVisible;
        opcionesVBox.setManaged(opcionesVisible);
        opcionesVBox.setVisible(opcionesVisible);
    }
}
