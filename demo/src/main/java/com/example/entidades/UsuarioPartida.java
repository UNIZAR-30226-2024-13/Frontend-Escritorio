package com.example.entidades;

public class UsuarioPartida {
    
    private String IdUsuario;
    private int turnoUsuario;
    private String cartasUsuario;
    
    public UsuarioPartida(String idUsuario, int turnoUsuario, String cartasUsuario) {
        IdUsuario = idUsuario;
        this.turnoUsuario = turnoUsuario;
        this.cartasUsuario = cartasUsuario;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
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
