package com.mygdx.burghs;

// Klasa Mapa przechowuje obiekty Klasy Pole

import java.io.Serializable;

public final class Mapa implements Serializable{

    private int iloscPolX = 10;
    private int iloscPolY = 10;

    public Pole[][] pola;

    /**
     * Tworzy mapę domyślną 10x10 pól
     */
    public Mapa() {
        this.generujMape(iloscPolX, iloscPolY);        
    }
    
    /**
     * Tworzy mapę z zadeklarowanych wartości ilości pól.
     * @param iloscPolX Ilość pól w osi X
     * @param iloscPolY Ilość pól w osi Y
     */
    public Mapa(int iloscPolX, int iloscPolY) {
        this.iloscPolX = iloscPolX;
        this.iloscPolY = iloscPolY;
        this.generujMape(this.iloscPolX, this.iloscPolY);
    }

    /**
     * Generuje mapę z zadanych ilości pól.
     * @param iloscPolX
     * @param iloscPolY 
     */
    public void generujMape(int iloscPolX, int iloscPolY) {
        this.iloscPolX = iloscPolX;
        this.iloscPolY = iloscPolY;
        pola = new Pole[iloscPolX][iloscPolY];

        for (int i = 0; i < this.iloscPolX; i++) {
            for (int j = 0; j < this.iloscPolY; j++) {
                pola[i][j] = new Pole();
            }
        }
    }

    //SETTERS AND GETTERS
    public int getIloscPolX() {
        return iloscPolX;
    }

    public int getIloscPolY() {
        return iloscPolY;
    }

    public Pole[][] getPola() {
        return pola;
    }

    public void setPola(Pole[][] pola) {
        this.pola = pola;
    }
}
