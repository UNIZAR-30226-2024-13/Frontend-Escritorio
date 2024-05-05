package com.example.entidades;

public class UNO {
    
    private String id;
    private String mazo;
    private int sentido;
    private String ultimaCarta;

    public UNO() {}

    public UNO(String id, String mazo, int sentido, String ultimaCarta) {
        this.id = id;
        this.mazo = mazo;
        this.sentido = sentido;
        this.ultimaCarta = ultimaCarta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMazo() {
        return mazo;
    }

    public void setMazo(String mazo) {
        this.mazo = mazo;
    }

    public int getSentido() {
        return sentido;
    }

    public void setSentido(int sentido) {
        this.sentido = sentido;
    }

    public String getUltimaCarta() {
        return ultimaCarta;
    }

    public void setUltimaCarta(String ultimaCarta) {
        this.ultimaCarta = ultimaCarta;
    }
    
}
