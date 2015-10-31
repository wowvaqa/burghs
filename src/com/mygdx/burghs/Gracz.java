package com.mygdx.burghs;

import java.util.ArrayList;

public class Gracz {

    public ArrayList<Player> bohaterowieOld = new ArrayList<Player>();
    
    private ArrayList<Bohater> bohaterowie = new ArrayList<Bohater>();

    private int gold;                                                           // złoto zgromadzone przez gracza

    public Gracz() {

    }

    //SETTERS AND GETTERS
    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public ArrayList<Player> getBohaterowieOld() {
        return bohaterowieOld;
    }

    public void setBohaterowieOld(ArrayList<Player> bohaterowie) {
        this.bohaterowieOld = bohaterowie;
    }

    public ArrayList<Bohater> getBohaterowie() {
        return bohaterowie;
    }

    public void setBohaterowie(ArrayList<Bohater> bohaterowie) {
        this.bohaterowie = bohaterowie;
    }
    
    

}
