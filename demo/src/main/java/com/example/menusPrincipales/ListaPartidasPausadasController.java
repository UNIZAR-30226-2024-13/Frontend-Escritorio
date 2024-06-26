package com.example.menusPrincipales;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.App;
import com.example.entidades.Partida;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ListaPartidasPausadasController implements Initializable{
    
    @FXML
    private VBox opcionesVBox;

    @FXML
    private TableView<Partida> tablaPartidasPausadas;
    
    @FXML
    private TableColumn<Partida, String> columnaTipoJuego;
    
    @FXML
    private Label labelFichas;
    
    private ObservableList<Partida> partidas;

    private boolean opcionesVisible = false;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        columnaTipoJuego.setCellValueFactory(new PropertyValueFactory<>("id_partida"));
        labelFichas.setText(App.usuario.getDinero() + " Fichas");
        partidas = FXCollections.observableArrayList(App.usuario.getPartidas());
        tablaPartidasPausadas.setItems(partidas);
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
}
