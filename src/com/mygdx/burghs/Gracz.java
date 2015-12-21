package com.mygdx.burghs;

import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

public class Gracz {

    public ArrayList<Player> bohaterowieOld = new ArrayList<Player>();
    
    private ArrayList<Bohater> bohaterowie = new ArrayList<Bohater>();

    private int gold;                                                           // złoto zgromadzone przez gracza
    
    // Ile tur gracz przebywa bez zamku.
    private int turyBezZamku = 0;
    
    // Informuje czy gracz jest posiadaczem zamku.
    private boolean statusBezZamku = false;
    
    // informuje czy gracz zakończył grę
    private boolean statusGameOver = false;
    
    // kolor gracza
    private Color color;

    public Gracz() {

    }

    //SETTERS AND GETTERS

    /**
     * Pobiera kolor gracza
     * @return 
     */
    public Color getColor() {
        return color;
    }

    /**
     * Ustala kolor dla gracza
     * @param color 
     */
    public void setColor(Color color) {
        this.color = color;
    }    
    
    /**
     * Zwraca ilość złota posiadanego przez gracza
     * @return złoto
     */
    public int getGold() {
        return gold;
    }

    /**
     * Ustala ilość złota należącego do gracza.
     * @param gold 
     */
    public void setGold(int gold) {
        this.gold = gold;
    }

    public ArrayList<Player> getBohaterowieOld() {
        return bohaterowieOld;
    }

    public void setBohaterowieOld(ArrayList<Player> bohaterowie) {
        this.bohaterowieOld = bohaterowie;
    }

    /**
     * Zwraca ArrayList<> z Bohaterami należącymi do gracza.
     * @return ArrayList<> z bohaterami
     */
    public ArrayList<Bohater> getBohaterowie() {
        return bohaterowie;
    }

    /**
     * Pobiera ArrayList<> z bohaterami gracza
     * @param bohaterowie 
     */
    public void setBohaterowie(ArrayList<Bohater> bohaterowie) {
        this.bohaterowie = bohaterowie;
    }

    /**
     * Zwraca ile tur bez zamku ma gracz
     * @return ilość tur bez zamku
     */
    public int getTuryBezZamku() {
        return turyBezZamku;
    }

    /**
     * Ustala ilość tur bez zamku gracza
     * @param turyBezZamku 
     */
    public void setTuryBezZamku(int turyBezZamku) {
        this.turyBezZamku = turyBezZamku;
    }

    /**
     * Zwraca True jeżeli gracz zakończył grę
     * Zwraca False jeżeli gracz nie zakończył gry.
     * @return 
     */
    public boolean isStatusGameOver() {
        return statusGameOver;
    }

    /**
     * Ustala czy gracz uczestniczy w grze(False) lub nie (TRUE)
     * @param statusGameOver 
     */
    public void setStatusGameOver(boolean statusGameOver) {
        this.statusGameOver = statusGameOver;
    }

    /**
     * Zwraca TRUE jeżeli gracz nie posiada zadnego zamku, FALSE jeżeli 
     * posiada
     * @return 
     */
    public boolean isStatusBezZamku() {
        return statusBezZamku;
    }

    /**
     * Ustala czy gracz jest posiadaczem zamku.
     * @param statusBezZamku 
     */
    public void setStatusBezZamku(boolean statusBezZamku) {
        this.statusBezZamku = statusBezZamku;
    }
    
    

}
