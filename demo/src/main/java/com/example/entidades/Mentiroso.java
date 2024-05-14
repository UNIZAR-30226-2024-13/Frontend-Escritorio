package com.example.entidades;

public class Mentiroso {
    
    private String id;
    private int turno;
    private String cartasMesa;
    private int numeroActual;
    private int cartasUltimaJugada;

    public Mentiroso() {}

    public Mentiroso(String id, int turno, String cartasMesa, int numeroActual, int cartasUltimaJugada) {
        this.id = id;
        this.turno = turno;
        this.cartasMesa = cartasMesa;
        this.numeroActual = numeroActual;
        this.cartasUltimaJugada = cartasUltimaJugada;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public String getCartasMesa() {
        return cartasMesa;
    }

    public void setCartasMesa(String cartasMesa) {
        this.cartasMesa = cartasMesa;
    }

    public int getNumeroActual() {
        return numeroActual;
    }

    public void setNumeroActual(int numeroActual) {
        this.numeroActual = numeroActual;
    }

    public int getCartasUltimaJugada() {
        return cartasUltimaJugada;
    }

    public void setCartasUltimaJugada(int cartasUltimaJugada) {
        this.cartasUltimaJugada = cartasUltimaJugada;
    }
    
}
