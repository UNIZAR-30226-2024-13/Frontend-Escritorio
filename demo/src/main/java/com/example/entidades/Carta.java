package com.example.entidades;

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

    public String toString() {
        String numero = "";
        switch (this.getNumero()) {
            case 0:
                numero = "As";
                break;
            case 7:
                numero = "Sota";
                break;
            case 8:
                numero = "Caballo";
                break;
            case 9:
                numero = "Rey";
                break;
            default:
                numero = "" + (this.getNumero() + 1);
                break;
        }
        return numero + " de " + paloString(this.getPalo());
    }

    /**
     * Tranforma el palo de la carta en un string
     * @param palo - Representacion en numero entero del palo de la carta
     * @return - Palo de la carta en forma de cadena de caracteres
     */
    public String paloString(int palo){
        switch (palo) {
            case OROS:
                return "oros";
            case COPAS:
                return "copas";
            case ESPADAS:
                return "espadas";
            case BASTOS:
                return "bastos";
            default:
                return "";
        } 
    }
}
