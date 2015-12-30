package com.mygdx.burghs;

// Klasa Pole jest wirtualnym polem przechowującym referencje do obiektu bohatera
public class Pole {
    
    private Bohater bohater;
    
    private TresureBox tresureBox = null;
    
    private Castle castle = null;
    
    private Mob mob = null;

    public Pole() {

    }

    public enum TypPola {

        TRAWA, GORY;
    }
    
    // Setters and Getters

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
