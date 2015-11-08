package com.mygdx.burghs;

import enums.CzesciCiala;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/** 
 *  Klasa definiuje Item: Modyfikatory do statystyk oraz wygląd
 * @author v
 */
public class Item extends Actor{
    
    private String nazwa;
    
    // ikona
    private Texture iconTex;
    
    // na której części ciała można nośić item
    private CzesciCiala czescCiala;
    
    // statystyka itemka
    private int atak = 0;
    private int obrona = 0;
    private int hp = 0;
    private int szybkosc = 0;
    
    public Item(){
        
    }    
    
    // Setters and Getters

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
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

    public Texture getIconTex() {
        return iconTex;
    }

    public void setIconTex(Texture iconTex) {
        this.iconTex = iconTex;
    }

    public CzesciCiala getCzescCiala() {
        return czescCiala;
    }

    public void setCzescCiala(CzesciCiala czescCiala) {
        this.czescCiala = czescCiala;
    }
    
    @Override
    public void act(float delta) {
        super.act(delta); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha); //To change body of generated methods, choose Tools | Templates.
    }
}
