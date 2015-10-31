package com.mygdx.burghs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Mob extends Actor {

    private Sprite sprite;    // wygląd
    private Texture icon;
    private Texture iconZaznaczony;

    private int pozX = 0;   // pozycja X na mapie
    private int pozY = 0;   // pozycja Y na mapie

    public GameStatus gs;

    // Statystyki
    private String imie = null;
    private int atak = 0;
    private int obrona = 0;
    private int hp = 0;
    private int szybkosc = 0;

    public Mob(Texture textureIcon, Texture textureIconZaznaczona, GameStatus gs,
            int lokaczjaPoczatkowaX, int lokaczjaPoczatkowaY) {
        this.gs = gs;
        this.icon = textureIcon;
        this.iconZaznaczony = textureIconZaznaczona;
        sprite = new Sprite(this.icon);
        this.setSize(sprite.getWidth(), sprite.getHeight());
        this.setPosition(lokaczjaPoczatkowaX, lokaczjaPoczatkowaY);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public GameStatus getGs() {
        return gs;
    }

    public void setGs(GameStatus gs) {
        this.gs = gs;
    }

    public Texture getIconZaznaczony() {
        return iconZaznaczony;
    }

    public void setIconZaznaczony(Texture iconZaznaczony) {
        this.iconZaznaczony = iconZaznaczony;
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
