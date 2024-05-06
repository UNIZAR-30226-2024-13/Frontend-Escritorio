package com.example.entidades;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 
     * @param cadenaCartas, contiene la cadena de carecteres recibida desde la Base de Datos
     * @return retVal, contiene la lista de Carta que representa la baraja de juego
     */
    public List<Carta> parseStringCartas(String cadenaCartas){
        List<Carta> retVal = new ArrayList<>();
        String[] cartas_mazo = cadenaCartas.split(";");
        for (String carta : cartas_mazo){
            String[] parte_carta = carta.split(",");
            int parte = 0;
            Carta carta2 = new Carta();
            for (String info_carta : parte_carta){
                if (parte == 0) {
                    int color = Integer.parseInt(info_carta);
                    carta2.setPalo(color);
                    parte = 1;
                }
                else{
                    int numero = Integer.parseInt(info_carta);
                    carta2.setNumero(numero);
                }
            }
            retVal.add(carta2);
        }
        return retVal;
    }
}
