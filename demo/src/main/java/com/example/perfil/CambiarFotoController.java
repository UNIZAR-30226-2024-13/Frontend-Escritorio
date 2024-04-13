package com.example.perfil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

import com.example.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class CambiarFotoController implements Initializable{

    @FXML
    private VBox opcionesVBox;

    @FXML
    private Label nombreImagen;

    @FXML
    private ImageView previsualizacionImagen;

    private File archivoImagen;


    private boolean opcionesVisible = false;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
    private void cargarImagen() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen de Perfil");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.png", "*.jpeg"));

        archivoImagen = fileChooser.showOpenDialog(null);

        if (archivoImagen != null) {

            Image imagen = new Image(archivoImagen.toURI().toString());
            previsualizacionImagen.setImage(imagen);

            // mostramos nombre de la imagen
            nombreImagen.setText(Paths.get(archivoImagen.getAbsolutePath()).getFileName().toString());
        }
    }

    @FXML
    private void guardarImagen() {
        try {
            // definimos carpeta para guardar la imagen
            Path carpetaDestino = Paths.get("/com/example/imgs");

            // obtenemos nombre de la imagen del path de origen
            Path imagenOrigen = Paths.get(archivoImagen.getAbsolutePath());
            Path imagenDestino = carpetaDestino.resolve(imagenOrigen.getFileName());
            System.out.println("destino: " + carpetaDestino);
            System.out.println("origen: " + imagenOrigen);
            System.out.println("imagen destino: " + imagenDestino);
            // hacemos una copia de la imagen
            Files.copy(imagenOrigen, imagenDestino, StandardCopyOption.REPLACE_EXISTING);
            App.setRoot("/com/example/vistas/perfil/perfil");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void mostrarOcultarOpciones(ActionEvent event) {
        opcionesVisible = !opcionesVisible;
        opcionesVBox.setManaged(opcionesVisible);
        opcionesVBox.setVisible(opcionesVisible);
    }
}
