package com.mygdx.burghs;

// Klasa Mapa przechowuje obiekty Klasy Pole
public final class Mapa {

    private int iloscPolX = 0;
    private int iloscPolY = 0;

    public Pole[][] pola;

    public Mapa() {
        this.generujMape(10, 10);
    }

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
