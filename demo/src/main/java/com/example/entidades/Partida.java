package com.example.entidades;

public class Partida {
    private String id;
    private int jugadores;
    private int apuesta;
    private int turno;
    private boolean activa;
    private boolean privada;

    public Partida() {
        this.id = "";
        this.jugadores = 0;
        this.apuesta = 0;
        this.turno = 0;
    }

    public Partida(String id, int jugadores, int apuesta) {
        this.id = id;
        this.jugadores = jugadores;
        this.apuesta = apuesta;
    }

    public Partida(String id, int jugadores, int apuesta, int turno) {
        this.id = id;
        this.jugadores = jugadores;
        this.apuesta = apuesta;
        this.turno = turno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
    
    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public boolean isPrivada() {
        return privada;
    }

    public void setPrivada(boolean privada) {
        this.privada = privada;
    }
}