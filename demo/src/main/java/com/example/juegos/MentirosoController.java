package com.example.juegos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.example.App;
import com.example.entidades.Carta;
import com.example.entidades.Mentiroso;
import com.example.entidades.Usuario;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MentirosoController implements Initializable{

    @FXML
    private GridPane cartasMesa;

    @FXML
    private GridPane cartas;

    @FXML
    private Label jugadaAnterior;

    private int filaCartas = 0;

    private boolean primerTurno = true;

    private int numeroAJugar = 0;

    @FXML
    private GridPane cartasUsuario2;

    @FXML
    private GridPane cartasUsuario3;
    
    @FXML
    private GridPane cartasUsuario4;

    private int botones = 0;

    private List<Carta> listaCartasMesa = new ArrayList<>();

    private List<Usuario> usuarios = new ArrayList<>();

    private List<Button> botonesSeleccionados = new ArrayList<>();

    private List<Carta> cartasSeleccionadas = new ArrayList<>();

    private List<Integer> columnasCartas = new ArrayList<>();

    private List<Integer> filasCartas = new ArrayList<>();

    private JSONArray usuarioArray = new JSONArray();

    private Mentiroso partida = new Mentiroso();

    private Timeline timeline;

   
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeline = new Timeline(
            new KeyFrame(Duration.seconds(5), event -> {
                recogerMentiroso();
                if (usuarios.size() == 4) {
                    inicializar();
                    timeline.stop();
                }
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
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

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("/com/example/vistas/menusPrincipales/menuPrincipal");
    }

    @SuppressWarnings("static-access")
    @FXML
    private void ponerCarta() throws IOException{ 
        while (botones < cartasSeleccionadas.size()) {
            Button button = new Button(cartasSeleccionadas.get(botones).toString());
            button.getStyleClass().add(App.estiloCartas);
            ImageView imagenRev = new ImageView();

            Image imagen = new Image(getClass().getResourceAsStream(App.reversoCartas));
    
            imagenRev.setImage(imagen);
            imagenRev.setFitWidth(40);
            imagenRev.setFitHeight(60);

            cartasMesa.add(imagenRev, botones, 0);
            cartas.getChildren().remove(botonesSeleccionados.get(botones));
            columnasCartas.add(cartas.getColumnIndex(botonesSeleccionados.get(botones)));
            filasCartas.add(cartas.getRowIndex(botonesSeleccionados.get(botones)));
            botones++;
        } 
        jugadaAnterior.setText("Ha tirado " + cartasSeleccionadas.size() + " cartas del número " + numeroAJugar);
        partida.setCartasUltimaJugada(cartasSeleccionadas.size());
        if (primerTurno) {
            pedirNumero();
            primerTurno = false;
            mandarMentiroso("");     
        }
        else {
            mandarMentiroso("mentir");
        }
    }

    private void pedirNumero() throws IOException{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/vistas/juegos/ventanaMentiroso.fxml"));
        Parent root = fxmlLoader.load();
        VentanaMentirosoController controller = fxmlLoader.getController(); // Obtener el controlador
        Scene scene = new Scene(root, 600, 300);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/imgs/logo.jpg")));
        stage.setScene(scene);
        stage.showAndWait();
        numeroAJugar = controller.getBoton();
        partida.setNumeroActual(numeroAJugar);
    }


    @FXML
    private void levantarCarta() {
        for (int i = 0; i < cartasSeleccionadas.size(); i++) {
            Carta carta = cartasSeleccionadas.get(i);
            Button button = new Button(carta.toString());
            button.getStyleClass().add(App.estiloCartas);
            button.setOnAction(event -> {
                button.getStyleClass().remove(App.estiloCartas);
                button.getStyleClass().add("carta-button-clicked");
                botonesSeleccionados.add(button);
                cartasSeleccionadas.add(carta);
            });
            cartas.add(button, columnasCartas.get(i), filasCartas.get(i));
            cartasMesa.getChildren().clear();
        }
        jugadaAnterior.setText("");
        cartasSeleccionadas.clear();
        botonesSeleccionados.clear();
        columnasCartas.clear();
        filasCartas.clear();
        primerTurno = true;
        botones = 0;
        partida.setCartasUltimaJugada(0);
        mandarMentiroso("levantar");
    }

    private void recogerMentiroso() {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get(App.ip + "/juegos/mentiroso/getMentiroso/" + App.partida.getId() + "?usuarioSesion=" + App.usuario.getNombre() + "&sessionToken="+ App.tokenSesion).asJson();
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(apiResponse.getBody().toString());
            JSONObject datos = (JSONObject) root.get("datos");

            partida.setId((String) datos.get("id"));
            partida.setTurno(((Long) datos.get("turno")).intValue());
            partida.setActiva((Boolean) datos.get("activa"));
            partida.setPrivada((Boolean) datos.get("privada"));
            partida.setNumeroActual(((Long) datos.get("numero")).intValue());
            partida.setCartasMesa((String) datos.get("cartasMesa"));
            partida.setCartasUltimaJugada(((Long) datos.get("cartasUltimaJugada")).intValue());

            usuarioArray = (JSONArray) datos.get("guarda");
            usuarios.clear();
            for (Object object : usuarioArray) {
                JSONObject infoUsuario = (JSONObject) object;
                String id = (String) infoUsuario.get("idUsuario");
                int turno = (((Long) infoUsuario.get("turnoEnPartida")).intValue());
                String cartas = (String) infoUsuario.get("cartas");
                
                Usuario usuario = new Usuario();
                usuario.setId(id);
                usuario.setCartas(cartas);
                usuario.setTurno(turno);
                usuarios.add(usuario);
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void mandarMentiroso(String accion) {
        try {
            JSONObject mentirosoJson = new JSONObject();
            mentirosoJson.put("cartas", new Carta().parseCartasString(cartasSeleccionadas));
            mentirosoJson.put("accion", accion);
            mentirosoJson.put("numero", partida.getNumeroActual());
            HttpResponse<JsonNode> response = Unirest.post(App.ip + "/juegos/mentiroso/" + App.partida.getId() + "/jugada?sessionToken=" + App.tokenSesion + "&usuarioSesion=" + App.usuario.getNombre())
            .header("Content-Type", "application/json")
            .body(mentirosoJson.toString())
            .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        } 
    }

    private void actualizarVista() {
        for (Usuario usuario : usuarios) {
            int turno = usuario.getTurno();
            String cartas = usuario.getCartas();
            int numCartas = new Carta().parseStringCartas(cartas).size();
            if (turno == partida.getTurno()) {
                if (turno == 1) {
                    cartasUsuario4.getChildren().clear();
                    for (int m = 0; m < numCartas; m++) {
                        ImageView imagenRev = new ImageView();

                        Image imagen = new Image(getClass().getResourceAsStream(App.reversoCartas));
                
                        imagenRev.setImage(imagen);
                        imagenRev.setFitWidth(40);
                        imagenRev.setFitHeight(60);
                        cartasUsuario4.add(imagenRev, m, 0);
                    }
                }
                else if (turno == 2) {
                    cartasUsuario2.getChildren().clear();
                    for (int m = 0; m < numCartas; m++) {
                        ImageView imagenRev = new ImageView();

                        Image imagen = new Image(getClass().getResourceAsStream(App.reversoCartas));
                
                        imagenRev.setImage(imagen);
                        imagenRev.setFitWidth(40);
                        imagenRev.setFitHeight(60);
                        cartasUsuario2.add(imagenRev, m, 0);
                    }
                }
                else if (turno == 3) {
                    cartasUsuario3.getChildren().clear();
                    for (int m = 0; m < numCartas; m++) {
                        ImageView imagenRev = new ImageView();

                        Image imagen = new Image(getClass().getResourceAsStream(App.reversoCartas));
                
                        imagenRev.setImage(imagen);
                        imagenRev.setFitWidth(40);
                        imagenRev.setFitHeight(60);
                        cartasUsuario3.add(imagenRev, m, 0);
                    }
                }
            }
        }
        listaCartasMesa = new Carta().parseStringCartas(partida.getCartasMesa());
        cartasMesa.getChildren().clear();
        for (int m = 0; m < listaCartasMesa.size(); m++) {
            ImageView imagenRev = new ImageView();

            Image imagen = new Image(getClass().getResourceAsStream(App.reversoCartas));
    
            imagenRev.setImage(imagen);
            imagenRev.setFitWidth(40);
            imagenRev.setFitHeight(60);
            cartasMesa.add(imagenRev, m, 0);
        }
    }

    private void inicializar() {
        int n = 0;
        int m = 0;
        cartas.getChildren().clear();
        String cartasU = "";
        for (Usuario usuario : usuarios) {
            if (App.usuario.getId().equals(usuario.getId())) {
                cartasU = usuario.getCartas();
            }
        }
        List<Carta> listaCartas = new Carta().parseStringCartas(cartasU);
        for (Carta carta : listaCartas) {
            Button boton = new Button(carta.toString());
            boton.getStyleClass().add(App.estiloCartas);
            boton.setOnAction(event -> {
                boton.getStyleClass().remove(App.estiloCartas);
                boton.getStyleClass().add("carta-button-clicked");
                botonesSeleccionados.add(boton);
                cartasSeleccionadas.add(carta);
            });
            if (n == 8) {
                filaCartas++;
                n=0;
            }
            cartas.add(boton, n, filaCartas);

            ImageView imagenRev = new ImageView();

            Image imagen = new Image(getClass().getResourceAsStream(App.reversoCartas));
    
            imagenRev.setImage(imagen);
            imagenRev.setFitWidth(40);
            imagenRev.setFitHeight(60);

            ImageView imagenRev2 = new ImageView();
    
            imagenRev2.setImage(imagen);
            imagenRev2.setFitWidth(40);
            imagenRev2.setFitHeight(60);

            ImageView imagenRev3 = new ImageView();
    
            imagenRev3.setImage(imagen);
            imagenRev3.setFitWidth(40);
            imagenRev3.setFitHeight(60);

            cartasUsuario2.add(imagenRev, m, 0);
            cartasUsuario3.add(imagenRev2, 0, m);
            cartasUsuario4.add(imagenRev3, 0, m);
            n++;
            m++;
        }
        cartas.setHgap(20);
        cartas.setVgap(20);
        cartasUsuario2.setHgap(30);
        cartasUsuario3.setVgap(3);
        cartasUsuario4.setVgap(3);
        cartasMesa.setHgap(10);

        Timeline timeline2 = new Timeline(
            new KeyFrame(Duration.seconds(5), event -> {
                int turno = partida.getTurno();
                recogerMentiroso();
                if (turno != partida.getTurno() && turno != 0) {
                    actualizarVista();
                }
            })
        );
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.play();
    }
}