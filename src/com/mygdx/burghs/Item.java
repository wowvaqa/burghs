package com.mygdx.burghs;

import com.badlogic.gdx.graphics.Texture;
import enums.CzesciCiala;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Klasa definiuje Item: Modyfikatory do statystyk oraz wygląd
 *
 * @author v
 */
public class Item extends Actor {

    private String nazwa;

    private final Assets a;
    private final GameStatus gs;

    // ikona    
    private Sprite sprite;

    // na której części ciała można nośić item
    private CzesciCiala czescCiala;

    // statystyka itemka
    private int atak = 0;
    private int obrona = 0;
    private int hp = 0;
    private int szybkosc = 0;

    public Item(Texture teksura, final Assets a, final GameStatus gs) {
        this.a = a;
        this.gs = gs;
        sprite = new Sprite(teksura);
        this.setSize(sprite.getWidth(), sprite.getHeight());  
        final Item qpa = this;

        this.addListener(new ClickListener(){
            GameStatus tmpGs = gs;
            //final tmpItem = this;

            @Override
            public void clicked(InputEvent event, float x, float y) {
                tmpGs.setLastScreen(5);
                tmpGs.setActualScreen(6);
                tmpGs.setItem(qpa);
            }            
        });
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

    public CzesciCiala getCzescCiala() {
        return czescCiala;
    }

    public void setCzescCiala(CzesciCiala czescCiala) {
        this.czescCiala = czescCiala;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Assets getA() {
        return a;
    }

    @Override
    public void act(float delta) {
        super.act(delta); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
