package com.mygdx.burghs;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import java.util.ArrayList;
import java.util.Random;

public class GameStatus {
    private int actualScreen = 0;                                               // zapamiętuje aktualną scenę która jest wyświetlana    
    private int lastScreen = 0;                                                 // Ostatnia scena która była wyświetlana
    private int predkoscScrollowaniaKamery = 10;                                // współczynnik prędkości dla scrollingu mapy w obiekcie klasy MapScreen
    private int predkoscZoomKamery = 10;                                        // współczynnik prędkości dla zoomu mapy w obiekcie klasy MapScreen
    public float xDlaInterfejsuRuchu = 0;
    public float yDlaInterfejsuRuchu = 0;    
    public int iloscGraczy = 6;                                                 // ilosć graczy
    public int maxIloscBohaterow = 1;                                           // maksymalna ilość bohaterów   
    // informuje czy jakikolwiek z bohaterów został zaznaczony
    private boolean czyZaznaczonoBohatera = false;
    
    // Zwraca nr gracza z tablicy graczy który posiada swoją turę
    private int turaGracza = 0;
    
    //private Skin skin;    
    
    private Item item;
    
    public boolean czyUtworzonoMape = false;                                    // informuje jeżeli TRUE że możliwe jest utworzenie obiektu klasy MapScreen 
    
    public ArrayList<Gracz> gracze = new ArrayList<Gracz>();                    // przechowuje graczy     
    
    // Przechowuje mape z obiektami graczy w celu wychwycenia kolizji
    public Mapa mapa = new Mapa();    

    public int getIloscGraczy() {
        return iloscGraczy;
    }

    /**
     * Zwraca referencje do zaznaczonego bohatera na mapie
     * @return zwaraca referencje do obiketu zaznaczonego bohatera
     */
    public Bohater getBohaterZaznaczony(){
        for (Gracz gracz: this.getGracze()){
            for(Bohater bohater: gracz.getBohaterowie()){
                if (bohater.isZaznaczony()){
                    return bohater;
                }
            }
        }
        return null;
    }

// GETTER AND SETTERS

    public int getTuraGracza() {
        return turaGracza;
    }

    public void setTuraGracza(int turaGracza) {
        this.turaGracza = turaGracza;
    }
    

    public Mapa getMapa() {
        return mapa;
    }    
    
    public void setIloscGraczy(int iloscGraczy) {
        this.iloscGraczy = iloscGraczy;
    }

    public int getActualScreen() {
        return actualScreen;
    }

    public void setActualScreen(int actualScreen) {
        this.actualScreen = actualScreen;
    }
    
    public int getPredkoscScrollowaniaKamery() {
        return predkoscScrollowaniaKamery;
    }

    public void setPredkoscScrollowaniaKamery(int predkoscScrollowaniaKamery) {
        this.predkoscScrollowaniaKamery = predkoscScrollowaniaKamery;
    }

    public int getPredkoscZoomKamery() {
        return predkoscZoomKamery;
    }

    public void setPredkoscZoomKamery(int predkoscZoomKamery) {
        this.predkoscZoomKamery = predkoscZoomKamery;
    }

    public ArrayList<Gracz> getGracze() {
        return gracze;
    }

    public void setGracze(ArrayList<Gracz> gracze) {
        this.gracze = gracze;
    }

    public boolean isCzyZaznaczonoBohatera() {
        return czyZaznaczonoBohatera;
    }

    public void setCzyZaznaczonoBohatera(boolean czyZaznaczonoBohatera) {
        this.czyZaznaczonoBohatera = czyZaznaczonoBohatera;
    }    

    public int getLastScreen() {
        return lastScreen;
    }

    public void setLastScreen(int lastScreen) {
        this.lastScreen = lastScreen;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
