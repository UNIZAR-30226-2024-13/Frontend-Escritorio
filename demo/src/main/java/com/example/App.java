package com.example;

import java.io.IOException;

import com.example.entidades.Usuario;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    //public static final String ip = "http://90.94.101.49:20000/api";
    public static final String ip = "http://localhost:20000/api";
    public static Usuario usuario;
    public static String partidaPasswd;
    public static String tokenSesion = "";
    public static String estiloCartas = "carta-button";
    public static String estiloEscaleras = "cartas-escaleras";
    public static String reversoCartas = "/com/example/imgs/reverso_azul.jpg";


    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/com/example/vistas/login"), 640, 480);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/imgs/logo.jpg")));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}