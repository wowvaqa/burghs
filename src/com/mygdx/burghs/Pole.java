package com.mygdx.burghs;

// Klasa Pole jest wirtualnym polem przechowującym referencje do obiektu bohatera

import enums.TypyTerenu;
import java.io.Serializable;

public class Pole implements Serializable{
    
    private boolean movable = true;
    
    private Bohater bohater;
    
    private TresureBox tresureBox = null;
    
    private Castle castle = null;
    
    private Mob mob = null;
    
    private TypyTerenu typTerenu;

    public Pole() {        

    }

    /***************************************************************************
     * Setters and Getters
     **************************************************************************/

    /**
     * Zwraca typ terenu pola
     * @return Typ pola
     */
    public TypyTerenu getTypTerenu() {
        return typTerenu;
    }

    /**
     * Ustala typ terenu pola.
     * @param typTerenu 
     */
    public void setTypTerenu(TypyTerenu typTerenu) {
        this.typTerenu = typTerenu;
    }    
    
    /**
     * Zwraca TRUE jeżeli po polu może poruszczać się bohater.
     * @return 
     */
    public boolean isMovable() {
        return movable;
    }

    /**
     * Ustala czy po polu może poruszać się bohater.
     * @param movable 
     */
    public void setMovable(boolean movable) {
        this.movable = movable;
    }
    
    public Mob getMob() {
        return mob;
    }

    public void setMob(Mob mob) {
        this.mob = mob;
    }    
    
    public Bohater getBohater() {
        return bohater;
    }

    public void setBohater(Bohater bohater) {
        this.bohater = bohater;
    }

    public TresureBox getTresureBox() {
        return tresureBox;
    }

    public void setTresureBox(TresureBox tresureBox) {
        this.tresureBox = tresureBox;
    }

    public Castle getCastle() {
        return castle;
    }

    public void setCastle(Castle castle) {
        this.castle = castle;
    }    
}
