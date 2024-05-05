package com.example.entidades;

public class BlackJack {
    
    private String id;
    private String mazo;
    private String cartasBanca;

    public BlackJack() {}

    public BlackJack(String id, String mazo, String cartasBanca) {
        this.id = id;
        this.mazo = mazo;
        this.cartasBanca = cartasBanca;
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

    public String getCartasBanca() {
        return cartasBanca;
    }

    public void setCartasBanca(String cartasBanca) {
        this.cartasBanca = cartasBanca;
    }
}
