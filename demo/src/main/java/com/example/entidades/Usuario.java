package com.example.entidades;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String id;
    private String nombre;
    private String email;
    private int dinero;
    private String pais;
    private List<Usuario> amigos;
    private List<Partida> partidas;

    public Usuario() {
        this.id = "";
        this.nombre = "";
        this.email = "";
        this.dinero = 100;
        this.pais = "";
        this.amigos = new ArrayList<>();
        this.partidas = new ArrayList<>();
    }

    public Usuario(String nombre, String email, int dinero, String pais, List<Usuario> amigos, List<Partida> partidas) {
        this.nombre = nombre;
        this.email = email;
        this.dinero = dinero;
        this.pais = pais;
        this.amigos = amigos;
        this.partidas = partidas;
    }

    public Usuario(String id, String nombre, String email, int dinero, String pais,
                    List<Usuario> amigos, List<Partida> partidas) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.dinero = dinero;
        this.pais = pais;
        this.amigos = amigos;
        this.partidas = partidas;


    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Usuario> amigos) {
        this.amigos = amigos;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }
}