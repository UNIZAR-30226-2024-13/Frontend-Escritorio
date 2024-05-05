package com.example.entidades;

public class Mentiroso {
    
    private String id;
    private String cartasMesa;
    private int numero;
    private int ultimasCartas;

    public Mentiroso() {}

    public Mentiroso(String id, String cartasMesa, int numero, int ultimasCartas) {
        this.id = id;
        this.cartasMesa = cartasMesa;
        this.numero = numero;
        this.ultimasCartas = ultimasCartas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCartasMesa() {
        return cartasMesa;
    }

    public void setCartasMesa(String cartasMesa) {
        this.cartasMesa = cartasMesa;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getUltimasCartas() {
        return ultimasCartas;
    }

    public void setUltimasCartas(int ultimasCartas) {
        this.ultimasCartas = ultimasCartas;
    }   
}
