package com.example;

public class Partida {
    private String id_partida;
    private int jugadores;
    private int apuesta;

    public Partida() {
        this.id_partida = "";
        this.jugadores = 0;
        this.apuesta = 0;
    }

    public Partida(String id_partida, int jugadores) {
        this.id_partida = id_partida;
        this.jugadores = jugadores;
    }

    public Partida(String id_partida, int jugadores, int apuesta) {
        this.id_partida = id_partida;
        this.jugadores = jugadores;
        this.apuesta = apuesta;
    }

    public String getId_partida() {
        return id_partida;
    }

    public void setId_partida(String id_partida) {
        this.id_partida = id_partida;
    }

    public int getJugadores() {
        return jugadores;
    }

    public void setJugadores(int jugadores) {
        this.jugadores = jugadores;
    }
    
    public int getApuesta() {
        return apuesta;
    }

    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }
}
