package com.mygdx.burghs;

import com.badlogic.gdx.graphics.Texture;

public class Player extends Mob {   
    
    // do którego gracza należy player
    private int gracz = 0;
    //private int tagBohatera = 0;
    
    // lokacja początkowa bohatera
    private int lokacjaPoczatkowaX = 0;
    private int lokacjaPoczatkowaY = 0;
    
    // lokacja bohatera w obiekcie klasy Mapa
    private int pozXnaMapie; 
    private int pozYnaMapie;
    
    // zmienna przechowuje status bohatera
    // Jeśli zaznaczony = true, wtedy na mapie ikona boh. zmieni się na zaznaczoną
    // oraz uniemożliwi ponowne zaznaczenie bohatera.
    private boolean zaznaczony = false;

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public Player(Texture textureIcon, Texture textureIconZaznaczona, GameStatus gs, int x, int y, int pozXnaMapie, int pozYnaMapie) {
        super(textureIcon, textureIconZaznaczona, gs, x, y);        
        this.pozXnaMapie = pozXnaMapie;
        this.pozYnaMapie = pozYnaMapie;
    }
    
    // Zmienia teksturę bohatera na zaznaczoną
    public void zaznaczBohatera(){
        super.getSprite().setTexture(super.getIconZaznaczony());
    }
    
    // Zmienia teksturę bohatera na nie zaznaczoną
    public void odznaczBohatera(){
        super.getSprite().setTexture(super.getIcon());
    }
    
    public void moveNorth(){
        super.setPosition(this.getX(), this.getY() + 100);
        this.pozYnaMapie += 1;
        super.gs.getMapa().pola[this.pozXnaMapie][this.pozYnaMapie -1].bohaterOld = null;
        super.gs.getMapa().pola[this.pozXnaMapie][this.pozYnaMapie].bohaterOld = this;
        pokazujPozycjeNaMapie();
    }
    
    public void moveEast(){
        super.setPosition(this.getX()+ 100, this.getY());
        this.pozXnaMapie += 1;
        super.gs.getMapa().pola[this.pozXnaMapie - 1][this.pozYnaMapie].bohaterOld = null;
        super.gs.getMapa().pola[this.pozXnaMapie][this.pozYnaMapie].bohaterOld = this;
        pokazujPozycjeNaMapie();
    }    
    
    public void moveNorthEast(){
        super.setPosition(this.getX() + 100, this.getY() + 100);        
        this.pozXnaMapie += 1;
        this.pozYnaMapie += 1;
        super.gs.getMapa().pola[this.pozXnaMapie - 1][this.pozYnaMapie -1].bohaterOld = null;
        super.gs.getMapa().pola[this.pozXnaMapie][this.pozYnaMapie].bohaterOld = this;
        pokazujPozycjeNaMapie();
    }
    
    public void moveSouth(){
        super.setPosition(this.getX(), this.getY() - 100);
        this.pozYnaMapie -= 1;
        super.gs.getMapa().pola[this.pozXnaMapie][this.pozYnaMapie +1].bohaterOld = null;
        super.gs.getMapa().pola[this.pozXnaMapie][this.pozYnaMapie].bohaterOld = this;
        pokazujPozycjeNaMapie();
    }
    
    public void moveSouthEast(){
        super.setPosition(this.getX() + 100, this.getY() - 100);        
        this.pozXnaMapie += 1;
        this.pozYnaMapie -= 1;
        super.gs.getMapa().pola[this.pozXnaMapie - 1][this.pozYnaMapie +1].bohaterOld = null;
        super.gs.getMapa().pola[this.pozXnaMapie][this.pozYnaMapie].bohaterOld = this;
        pokazujPozycjeNaMapie();
    }
    
    public void moveWest(){
        super.setPosition(this.getX()- 100, this.getY());
        this.pozXnaMapie -= 1;
        super.gs.getMapa().pola[this.pozXnaMapie + 1][this.pozYnaMapie].bohaterOld = null;
        super.gs.getMapa().pola[this.pozXnaMapie][this.pozYnaMapie].bohaterOld = this;
        pokazujPozycjeNaMapie();
    }
    
    public void moveSouthWest(){
        super.setPosition(this.getX() - 100, this.getY() - 100);        
        this.pozXnaMapie -= 1;
        this.pozYnaMapie -= 1;
        super.gs.getMapa().pola[this.pozXnaMapie + 1][this.pozYnaMapie +1].bohaterOld = null;
        super.gs.getMapa().pola[this.pozXnaMapie][this.pozYnaMapie].bohaterOld = this;
        pokazujPozycjeNaMapie();
    }
    
    public void moveNorthWest(){
        super.setPosition(this.getX() - 100, this.getY() + 100);        
        this.pozXnaMapie -= 1;
        this.pozYnaMapie += 1;
        super.gs.getMapa().pola[this.pozXnaMapie + 1][this.pozYnaMapie -1].bohaterOld = null;
        super.gs.getMapa().pola[this.pozXnaMapie][this.pozYnaMapie].bohaterOld = this;
        pokazujPozycjeNaMapie();
    }
    
    // Metoda zmienia teksturę na zaznaczoną
    public void zaznaczBohatera(Texture tex){
        super.getSprite().setTexture(tex);
    }
    
    private void pokazujPozycjeNaMapie(){
        if (gs.getMapa().pola[this.pozXnaMapie][this.pozYnaMapie].bohaterOld != null){
            System.out.println("Lok. " + this.pozXnaMapie + " " + this.pozYnaMapie);
        }
    }

    // SETTERS AND GETTERS

    public int getPozXnaMapie() {
        return pozXnaMapie;
    }

    public void setPozXnaMapie(int pozXnaMapie) {
        this.pozXnaMapie = pozXnaMapie;
    }

    public int getPozYnaMapie() {
        return pozYnaMapie;
    }

    public void setPozYnaMapie(int pozYnaMapie) {
        this.pozYnaMapie = pozYnaMapie;
    }    

    public int getLokacjaPoczatkowaX() {
        return lokacjaPoczatkowaX;
    }

    public void setLokacjaPoczatkowaX(int lokacjaPoczatkowaX) {
        this.lokacjaPoczatkowaX = lokacjaPoczatkowaX;
    }

    public int getLokacjaPoczatkowaY() {
        return lokacjaPoczatkowaY;
    }

    public void setLokacjaPoczatkowaY(int lokacjaPoczatkowaY) {
        this.lokacjaPoczatkowaY = lokacjaPoczatkowaY;
    }
    
    
    public boolean isZaznaczony() {
        return zaznaczony;
    }

    public void setZaznaczony(boolean zaznaczony) {
        this.zaznaczony = zaznaczony;
    }

    public int getGracz() {
        return gracz;
    }

    public void setGracz(int gracz) {
        this.gracz = gracz;
    }
    
}
