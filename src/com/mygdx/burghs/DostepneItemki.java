package com.mygdx.burghs;

/**
 *  Klasa określa wszystkie dostępne itemki
 * @author v
 */
public enum DostepneItemki {
    Piesci, Spodnie, LnianeButy;
    
    private Assets a;
    
    public Item piesci(){
        Item piesci = new Item(a.btnAttackTex);
        return piesci;
    }
}
