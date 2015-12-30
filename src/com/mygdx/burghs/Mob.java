package com.mygdx.burghs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Mob extends Actor {

    private Sprite sprite;    // wygląd
    private Texture icon;

    private int pozX = 0;   // pozycja X na mapie
    private int pozY = 0;   // pozycja Y na mapie

    private final GameStatus gs;
    private final Assets a;

    // Statystyki
    private String imie = null;
    private int atak = 0;
    private int obrona = 0;
    private int hp = 0;
    private int aktualneHp = 0;
    private int szybkosc = 0;
    private int expReward = 0;

    /**
     * 
     * @param textureIcon Tekstura moba
     * @param gs Referencja do obiketu Game Status
     * @param a Referencja do obiektu Assets
     * @param lokaczjaPoczatkowaX Lokacja początkowa X w Stage
     * @param lokaczjaPoczatkowaY Lokacja początkowa Y w Stage
     */
    public Mob(Texture textureIcon, GameStatus gs, Assets a,
            int lokaczjaPoczatkowaX, int lokaczjaPoczatkowaY) {
        this.gs = gs;
        this.a = a;
        this.icon = textureIcon;
        sprite = new Sprite(this.icon);
        this.setSize(sprite.getWidth(), sprite.getHeight());
        this.setPosition(lokaczjaPoczatkowaX, lokaczjaPoczatkowaY);
        
        podepnijTymczasoweStatystyki();        
    }
    
    private void podepnijTymczasoweStatystyki(){
        // tymczasowe statystyki
        this.atak = 5;
        this.obrona = 5;
        this.hp = 3;
        this.aktualneHp = this.hp;
        this.szybkosc = 1;  
        this.expReward = 100;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public int getExpReward() {
        return expReward;
    }

    public void setExpReward(int expReward) {
        this.expReward = expReward;
    }   
    
    public Texture getIcon() {
        return icon;
    }

    public void setIcon(Texture icon) {
        this.icon = icon;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public int getAtak() {
        return atak;
    }

    public void setAtak(int atak) {
        this.atak = atak;
    }

    public int getObrona() {
        return obrona;
    }

    public void setObrona(int obrona) {
        this.obrona = obrona;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAktualneHp() {
        return aktualneHp;
    }

    public void setAktualneHp(int aktualneHp) {
        this.aktualneHp = aktualneHp;
    }

    public int getSzybkosc() {
        return szybkosc;
    }

    public void setSzybkosc(int szybkosc) {
        this.szybkosc = szybkosc;
    }

    public int getPozX() {
        return pozX;
    }

    public void setPozX(int pozX) {
        this.pozX = pozX;
    }

    public int getPozY() {
        return pozY;
    }

    public void setPozY(int pozY) {
        this.pozY = pozY;
    }

}
