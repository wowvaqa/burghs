package com.mygdx.burghs;

// Klasa Pole jest wirtualnym polem przechowującym referencje do obiektu bohatera
public class Pole {
    
    public Player bohaterOld;
    
    private Bohater bohater;
    
    private TresureBox tresureBox = null;

    public Pole() {

    }

    public enum TypPola {

        TRAWA, GORY;
    }
    
    // Setters and Getters

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
}
