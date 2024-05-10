package com.example.juegos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.example.App;
import com.example.entidades.CartaFrancesa;
import com.example.entidades.Partida;
import com.example.entidades.Poker;
import com.example.entidades.Usuario;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class PokerController implements Initializable{


    @FXML
    private GridPane primeraCartaMesa;

    @FXML
    private GridPane segundaCartaMesa;

    @FXML
    private GridPane terceraCartaMesa;

    @FXML
    private GridPane cuartaCartaMesa;

    @FXML
    private GridPane quintaCartaMesa;

    @FXML
    private GridPane cartasUsuario2;

    @FXML
    private GridPane cartasUsuario3;
    
    @FXML
    private GridPane cartasUsuario4;

    @FXML
    private GridPane cartas;

    @FXML
    private Button botonApostar;

    @FXML
    private Button botonRetirar;

    @FXML
    private Button botonIgualar;

    @FXML
    private TextField campoApuesta;

    private List<CartaFrancesa> mazo = new ArrayList<>();

    private List<CartaFrancesa> cartasMesa = new ArrayList<>();

    private Poker partida = new Poker();

    private boolean hay_cuarta = false;
   
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recogerPoker();
        App.partidaPasswd = partida.getId();

        int n = 0;
        cartas.getChildren().clear();
        for (CartaFrancesa carta : cartasMesa) {
            if (n == 0) {
                Label label = new Label(carta.toString());
                label.getStyleClass().add(App.estiloEscaleras);
                primeraCartaMesa.add(label, n, 0);
            }
            else if (n == 1) {
                Label label = new Label(carta.toString());
                label.getStyleClass().add(App.estiloEscaleras);
                segundaCartaMesa.add(label, n, 0);
            }
            else if (n == 2) {
                Label label = new Label(carta.toString());
                label.getStyleClass().add(App.estiloEscaleras);
                terceraCartaMesa.add(label, n, 0);
            }
            n++;
        }
        n = 0;
        for (CartaFrancesa cartaMano : mazo) {
            Button boton = new Button(cartaMano.toString());
            boton.getStyleClass().add(App.estiloCartas);
            cartas.add(boton, n, 0);

            Image imagen = new Image(getClass().getResourceAsStream(App.reversoCartas));
            
            ImageView imagenRev = new ImageView(imagen);
            imagenRev.setFitWidth(40);
            imagenRev.setFitHeight(60);

            
            ImageView imagenRev2 = new ImageView(imagen);
            imagenRev2.setFitWidth(40);
            imagenRev2.setFitHeight(60);

            
            ImageView imagenRev3 = new ImageView(imagen);
            imagenRev3.setFitWidth(40);
            imagenRev3.setFitHeight(60);

            cartasUsuario2.add(imagenRev, n, 0);
            cartasUsuario3.add(imagenRev2, 0, n);
            cartasUsuario4.add(imagenRev3, 0, n);
            n++;
        }
        cartas.setHgap(20);
        cartasUsuario2.setHgap(30);
        cartasUsuario3.setVgap(30);
        cartasUsuario4.setVgap(30);
        primeraCartaMesa.setVgap(10);
        segundaCartaMesa.setVgap(10);
        terceraCartaMesa.setVgap(10);
        cuartaCartaMesa.setVgap(10);
        quintaCartaMesa.setVgap(10);
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(5), event -> {
                int otraCarta = cartasMesa.size();
                recogerPoker();
                if (otraCarta > cartasMesa.size()) {
                    ponerCarta();
                }
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        botonApostar.setOnAction(event -> {
            apostar(Integer.parseInt(campoApuesta.getText()));
        });

        botonIgualar.setOnAction(event -> {
            apostar(partida.getUltimaApuesta());
        });

        botonRetirar.setOnAction(event -> {
            apostar(0);
        });
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("/com/example/vistas/menusPrincipales/menuPrincipal");
    }

    @FXML
    private void pausarPartida() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/vistas/juegos/votacion.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 450);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/imgs/logo.jpg")));
        stage.setScene(scene);
        stage.showAndWait();
    }

    private void ponerCarta(){
        CartaFrancesa carta = new CartaFrancesa();
        if (!hay_cuarta) {
            carta = cartasMesa.get(3);
            Label label = new Label(carta.toString());
            label.getStyleClass().add(App.estiloEscaleras);
            cuartaCartaMesa.add(label, 3, 0);
            hay_cuarta = true;
        }
        else {
            carta = cartasMesa.get(4);
            Label label = new Label(carta.toString());
            label.getStyleClass().add(App.estiloEscaleras);
            quintaCartaMesa.add(label, 4, 0);
        }
    }

    private void recogerPoker() {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get(App.ip + "/juegos/getPoker").asJson();
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(apiResponse.getBody().toString());
            // Estas tres lineas no son asi, ahi un se sabe el formato de los datos
            JSONObject datos = (JSONObject) root.get("datos");
            JSONObject datosUsuario = (JSONObject) datos.get("usuario");
            JSONObject datosSesion = (JSONObject) datos.get("sessionToken");

            partida.setId((String) datosUsuario.get("id"));
            partida.setTurno(((Long) datosUsuario.get("turno")).intValue());
            partida.setBote(((Long) datosUsuario.get("bote")).intValue());
            partida.setUltimaApuesta(((Long) datosUsuario.get("ultima_apuesta")).intValue());
            partida.setCartasMesa((String) datosUsuario.get("cartas_mesa"));
            partida.setMazo((String) datosUsuario.get("mazo"));

            mazo = new CartaFrancesa().parseStringCartas(partida.getMazo());
            cartasMesa = new CartaFrancesa().parseStringCartas(partida.getCartasMesa());
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mandarPoker() {
        try {
            JSONObject pokerJson = new JSONObject();
            pokerJson.put("id", partida.getId());
            pokerJson.put("turno", partida.getTurno());
            pokerJson.put("bote", partida.getBote());
            pokerJson.put("ultimaApuesta", partida.getUltimaApuesta());
            pokerJson.put("cartasMesa", partida.getCartasMesa());
            pokerJson.put("mazo", partida.getMazo());
            HttpResponse<JsonNode> response = Unirest.post(App.ip + "/juegos/addPoker")
            .header("Content-Type", "application/json")
            .body(pokerJson.toString())
            .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        } 
    }

    private void apostar(int apuesta) {
        partida.setBote(partida.getBote() + apuesta);
        partida.setUltimaApuesta(apuesta);
        partida.setTurno(partida.getTurno()+1);
        mandarPoker();
    }
}