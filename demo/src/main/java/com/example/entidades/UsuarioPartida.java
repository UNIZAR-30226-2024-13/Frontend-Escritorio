package com.example.entidades;

public class UsuarioPartida {
    
    private String IdUsuario;
    private String IdPartida;
    private int turnoUsuario;
    private String cartasUsuario;
    
    public UsuarioPartida(String idUsuario, String idPartida, int turnoUsuario, String cartasUsuario) {
        IdUsuario = idUsuario;
        IdPartida = idPartida;
        this.turnoUsuario = turnoUsuario;
        this.cartasUsuario = cartasUsuario;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getIdPartida() {
        return IdPartida;
    }

    public void setIdPartida(String idPartida) {
        IdPartida = idPartida;
    }

    public int getTurnoUsuario() {
        return turnoUsuario;
    }

    public void setTurnoUsuario(int turnoUsuario) {
        this.turnoUsuario = turnoUsuario;
    }

    public String getCartasUsuario() {
        return cartasUsuario;
    }

    public void setCartasUsuario(String cartasUsuario) {
        this.cartasUsuario = cartasUsuario;
    }
}
