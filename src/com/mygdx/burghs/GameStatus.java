package com.mygdx.burghs;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import java.util.ArrayList;
import java.util.Random;

public class GameStatus {
    private int actualScreen = 0;                                               // zapamiętuje aktualną scenę która jest wyświetlana
    private int predkoscScrollowaniaKamery = 10;                                // współczynnik prędkości dla scrollingu mapy w obiekcie klasy MapScreen
    private int predkoscZoomKamery = 10;                                        // współczynnik prędkości dla zoomu mapy w obiekcie klasy MapScreen
    public float xDlaInterfejsuRuchu = 0;
    public float yDlaInterfejsuRuchu = 0;    
    public int iloscGraczy = 6;                                                 // ilosć graczy
    public int maxIloscBohaterow = 1;                                           // maksymalna ilość bohaterów   
    
    // Zwraca nr gracza z tablicy graczy który posiada swoją turę
    private int turaGracza = 0;
    
    private Skin skin;
       
    
    public boolean czyUtworzonoMape = false;                                    // informuje jeżeli TRUE że możliwe jest utworzenie obiektu klasy MapScreen 
    
    public ArrayList<Gracz> gracze = new ArrayList<Gracz>();                    // przechowuje graczy     
    
    // Przechowuje mape z obiektami graczy w celu wychwycenia kolizji
    public Mapa mapa = new Mapa();    

    public int getIloscGraczy() {
        return iloscGraczy;
    }
    
    public void atak(Bohater bohaterAtakujacy, Bohater bohaterBroniacy){
        Random rnd = new Random();
        System.out.println("Nastąpił atak mf");
        int atak = rnd.nextInt(bohaterAtakujacy.getAtak() + 1);
        int obrona = rnd.nextInt(bohaterBroniacy.getObrona() + 1);
        System.out.println("Siła ataku:  " + atak);
        System.out.println("siła obrony: " + obrona);
        int dmg = atak - obrona;
        if (dmg < 0)
            dmg = 0;
        System.out.println("damage: " + dmg);
        if (dmg > 0){
            bohaterBroniacy.setHp(bohaterBroniacy.getHp() - dmg);
        }
        if (bohaterBroniacy.getHp() <= 0){
           // bohaterBroniacy.remove();            
        }
        
        System.out.println("Test brancha");
        
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
}
