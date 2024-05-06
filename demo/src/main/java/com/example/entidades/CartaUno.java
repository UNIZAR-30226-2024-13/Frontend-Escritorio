package com.example.entidades;

import java.util.ArrayList;
import java.util.List;

public class CartaUno {

    public static final int AMARILLO = 0;
    public static final int AZUL = 1;
    public static final int ROJO = 2;
    public static final int VERDE = 3;

    private int numero;
    private int palo;
    
    public CartaUno() {}

    public CartaUno(int numero, int palo) {
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
            case 10:
                numero = "Prohibido";
                break;
            case 11:
                numero = "Cambio sentido";
                break;
            case 12:
                numero = "+2";
                break;
            case 13:
                numero = "+4";
                return numero;
            case 14:
                numero = "Cambio color";
                return numero;
            default:
                numero = "" + (this.getNumero());
                break;
        }
        return numero + " " + paloString(this.getPalo());
    }

    /**
     * Tranforma el palo de la carta en un string
     * @param palo - Representacion en numero entero del palo de la carta
     * @return - Palo de la carta en forma de cadena de caracteres
     */
    public String paloString(int palo){
        switch (palo) {
            case AMARILLO:
                return "amarillo";
            case AZUL:
                return "azul";
            case ROJO:
                return "rojo";
            case VERDE:
                return "verde";
            default:
                return "";
        } 
    }

    /**
     * 
     * @param cadenaCartas, contiene la cadena de carecteres recibida desde la Base de Datos
     * @return retVal, contiene la lista de Carta que representa la baraja de juego
     */
    public List<CartaUno> parseStringCartas(String cadenaCartas){
        List<CartaUno> retVal = new ArrayList<>();
        String[] cartas_mazo = cadenaCartas.split(";");
        for (String carta : cartas_mazo){
            String[] parte_carta = carta.split(",");
            int parte = 0;
            CartaUno carta2 = new CartaUno();
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
