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
    
    // Zmienne określają czy dane pole jest lokacją startową któregoś z graczy
    private boolean lokacjaStartowaP1 = false;
    private boolean lokacjaStartowaP2 = false;
    private boolean lokacjaStartowaP3 = false;
    private boolean lokacjaStartowaP4 = false;

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

    public boolean isLokacjaStartowaP1() {
        return lokacjaStartowaP1;
    }

    public void setLokacjaStartowaP1(boolean lokacjaStartowaP1) {
        this.lokacjaStartowaP1 = lokacjaStartowaP1;
    }

    public boolean isLokacjaStartowaP2() {
        return lokacjaStartowaP2;
    }

    public void setLokacjaStartowaP2(boolean lokacjaStartowaP2) {
        this.lokacjaStartowaP2 = lokacjaStartowaP2;
    }

    public boolean isLokacjaStartowaP3() {
        return lokacjaStartowaP3;
    }

    public void setLokacjaStartowaP3(boolean lokacjaStartowaP3) {
        this.lokacjaStartowaP3 = lokacjaStartowaP3;
    }

    public boolean isLokacjaStartowaP4() {
        return lokacjaStartowaP4;
    }

    public void setLokacjaStartowaP4(boolean lokacjaStartowaP4) {
        this.lokacjaStartowaP4 = lokacjaStartowaP4;
    }
    
    
}
