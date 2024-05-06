package com.example.entidades;

import java.util.ArrayList;
import java.util.List;

public class CartaFrancesa {

    public static final int PICAS = 0;
    public static final int DIAMANTES = 1;
    public static final int TREBOLES = 2;
    public static final int CORAZONES = 3;

    private int numero;
    private int palo;
    
    public CartaFrancesa() {}

    public CartaFrancesa(int numero, int palo) {
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
            case PICAS:
                return "picas";
            case DIAMANTES:
                return "diamantes";
            case TREBOLES:
                return "treboles";
            case CORAZONES:
                return "corazones";
            default:
                return "";
        } 
    }

    /**
     * 
     * @param cadenaCartas, contiene la cadena de carecteres recibida desde la Base de Datos
     * @return retVal, contiene la lista de Carta que representa la baraja de juego
     */
    public List<CartaFrancesa> parseStringCartas(String cadenaCartas){
        List<CartaFrancesa> retVal = new ArrayList<>();
        String[] cartas_mazo = cadenaCartas.split(";");
        for (String carta : cartas_mazo){
            String[] parte_carta = carta.split(",");
            int parte = 0;
            CartaFrancesa carta2 = new CartaFrancesa();
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
