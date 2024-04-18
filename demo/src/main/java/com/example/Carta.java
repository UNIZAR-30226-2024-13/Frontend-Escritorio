package com.example;

public class Carta {

    public final static int OROS = 0;
    public final static int COPAS = 1;
    public final static int ESPADAS = 2;
    public final static int BASTOS = 3;

    private int numero;
    private int palo;
    
    public Carta() {}

    public Carta(int numero, int palo) {
        this.numero = numero;
        this.palo = palo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPalo() {
        return palo;
    }

    public void setPalo(int palo) {
        this.palo = palo;
    }
}
