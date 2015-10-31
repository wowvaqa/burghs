package com.mygdx.burghs;

// Klasa Pole jest wirtualnym polem przechowującym referencje do obiektu bohatera
public class Pole {
    
    public Player bohaterOld;
    
    private Bohater bohater;

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
    
}
