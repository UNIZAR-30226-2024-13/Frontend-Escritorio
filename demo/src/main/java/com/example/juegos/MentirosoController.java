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
import com.example.entidades.CartaFrancesa;
import com.example.entidades.Mentiroso;
import com.example.entidades.Poker;
import com.example.entidades.Usuario;
import com.example.entidades.UsuarioPartida;
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

    private List<UsuarioPartida> usuarios = new ArrayList<>();

    private List<Button> botonesSeleccionados = new ArrayList<>();

    private List<Carta> cartasSeleccionadas = new ArrayList<>();

    private List<Integer> columnasCartas = new ArrayList<>();

    private List<Integer> filasCartas = new ArrayList<>();

    private JSONArray usuarioArray = new JSONArray();

    private Mentiroso partida = new Mentiroso();

   
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recogerMentiroso();
        App.partidaPasswd = partida.getId();

        int n = 0;
        int m = 0;
        cartas.getChildren().clear();
        // Aqui ten cuidado que puede ser erroneo ya que doy por hecho que el primer usuario de la lista
        // es el que esta jugando pero no se si alex lo pondrá así
        String cartasU = usuarios.get(0).getCartasUsuario();
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
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(5), event -> {
                int turno = partida.getTurno();
                recogerMentiroso();
                if (turno != partida.getTurno() && turno != 0) {
                    actualizarVista();
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
            cartasMesa.add(button, botones, 0);
            cartas.getChildren().remove(botonesSeleccionados.get(botones));
            columnasCartas.add(cartas.getColumnIndex(botonesSeleccionados.get(botones)));
            filasCartas.add(cartas.getRowIndex(botonesSeleccionados.get(botones)));
            botones++;
        } 
        if (primerTurno) {
            pedirNumero();
            primerTurno = false;
        }
        jugadaAnterior.setText("Ha tirado " + cartasSeleccionadas.size() + " cartas del número " + numeroAJugar);
        partida.setCartasUltimaJugada(cartasSeleccionadas.size());
        mandarMentiroso();     
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
        mandarMentiroso();
    }

    private void recogerMentiroso() {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get(App.ip + "/juegos/getMentiroso" + partida.getId()).asJson();
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(apiResponse.getBody().toString());
            // Estas tres lineas no son asi, ahi un se sabe el formato de los datos
            JSONObject datos = (JSONObject) root.get("datos");

            partida.setId((String) datos.get("id"));
            partida.setTurno(((Long) datos.get("turno")).intValue());
            partida.setUsuarioGanador(((Long) datos.get("usuarioGanador")).intValue());
            partida.setActiva((Boolean) datos.get("activa"));
            partida.setPrivada((Boolean) datos.get("privada"));
            partida.setNumeroActual(((Long) datos.get("numero")).intValue());
            partida.setCartasMesa((String) datos.get("cartasMesa"));
            partida.setCartasUltimaJugada(((Long) datos.get("cartasUltimaJugada")).intValue());

            usuarioArray = (JSONArray) datos.get("guarda");
            usuarios.clear();
            for (Object object : usuarioArray) {
                JSONObject infoUsuario = (JSONObject) object;
                UsuarioPartida usuario = new UsuarioPartida((String) infoUsuario.get("idUsuario"),
                                                            (String) infoUsuario.get("idPartida"),
                                                            (((Long) infoUsuario.get("turnoUsuario")).intValue()),
                                                            (String) infoUsuario.get("cartasUsuario"));
                usuarios.add(usuario);
            }
            listaCartasMesa = new Carta().parseStringCartas(partida.getCartasMesa());
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mandarMentiroso() {
        try {
            JSONObject mentirosoJson = new JSONObject();
            mentirosoJson.put("ID", partida.getId());
            mentirosoJson.put("turno", partida.getTurno());
            mentirosoJson.put("numero_actual", partida.getNumeroActual());
            mentirosoJson.put("usuarioGanador", partida.getUsuarioGanador());
            mentirosoJson.put("activa", partida.getActiva());
            mentirosoJson.put("privada", partida.getPrivada());
            mentirosoJson.put("ultimas_cartas", partida.getCartasUltimaJugada());
            mentirosoJson.put("cartas_mesa", partida.getCartasMesa());
            HttpResponse<JsonNode> response = Unirest.post(App.ip + "/juegos/addMentiroso")
            .header("Content-Type", "application/json")
            .body(mentirosoJson.toString())
            .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        } 
    }

    private void actualizarVista() {
        for (UsuarioPartida usuario : usuarios) {
            int turno = usuario.getTurnoUsuario();
            String cartas = usuario.getCartasUsuario();
            int numCartas = new Carta().parseStringCartas(cartas).size();
            if ( turno == partida.getTurno()) {
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
    }
}