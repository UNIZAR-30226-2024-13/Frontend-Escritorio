package com.example;

public class Usuario {

    private String id;
    private String nombre;
    private String email;
    private int dinero;
    private String pais;

    public Usuario() {
        this.id = "";
        this.nombre = "";
        this.email = "";
        this.dinero = 100;
        this.pais = "";
    }

    public Usuario(String id, String nombre, String email, int dinero, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.dinero = dinero;
        this.pais = pais;
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
}