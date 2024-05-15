package com.example.entidades;

public class Mentiroso {
    
    private String id;
    private int turno;
    private String cartasMesa;
    private int numeroActual;
    private int cartasUltimaJugada;
    private int usuarioGanador;
    private boolean activa;
    private boolean privada;

    public Mentiroso() {}

    public Mentiroso(String id, int turno, String cartasMesa, int numeroActual, int cartasUltimaJugada,
            int usuarioGanador, boolean activa, boolean privada) {
        this.id = id;
        this.turno = turno;
        this.cartasMesa = cartasMesa;
        this.numeroActual = numeroActual;
        this.cartasUltimaJugada = cartasUltimaJugada;
        this.usuarioGanador = usuarioGanador;
        this.activa = activa;
        this.privada = privada;
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

    public int getUsuarioGanador() {
        return usuarioGanador;
    }

    public void setUsuarioGanador(int usuarioGanador) {
        this.usuarioGanador = usuarioGanador;
    }

    public boolean getActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public boolean getPrivada() {
        return privada;
    }

    public void setPrivada(boolean privada) {
        this.privada = privada;
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
