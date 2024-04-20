package com.example;

public class Usuario {

    private String nombre;
    private String email;
    private int dinero;
    private String pais;

    public Usuario() {}

    public Usuario(String nombre, String email, int dinero, String pais) {
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
}