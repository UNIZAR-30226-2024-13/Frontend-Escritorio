package com.example.entidades;

public class Poker {
    
    private String id;
    private int bote;
    private int ultimaApuesta;
    private String cartasMesa;
    private String mazo;

    public Poker() {}

    public Poker(String id, int bote, int ultimaApuesta, String cartasMesa, String mazo) {
        this.id = id;
        this.bote = bote;
        this.ultimaApuesta = ultimaApuesta;
        this.cartasMesa = cartasMesa;
        this.mazo = mazo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBote() {
        return bote;
    }

    public void setBote(int bote) {
        this.bote = bote;
    }

    public int getUltimaApuesta() {
        return ultimaApuesta;
    }

    public void setUltimaApuesta(int ultimaApuesta) {
        this.ultimaApuesta = ultimaApuesta;
    }

    public String getCartasMesa() {
        return cartasMesa;
    }

    public void setCartasMesa(String cartasMesa) {
        this.cartasMesa = cartasMesa;
    }

    public String getMazo() {
        return mazo;
    }

    public void setMazo(String mazo) {
        this.mazo = mazo;
    }
}
