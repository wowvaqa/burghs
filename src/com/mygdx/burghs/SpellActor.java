package com.mygdx.burghs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import enums.Spells;

/**
 * Zaklecia
 *
 * @author v
 */
public class SpellActor extends DefaultActor {

    private Assets a;
    private GameStatus gs;
    private Bohater bohater;
    private Spells spell;

    private int zasieg = 0;
    private int dmg = 0;

    /**
     * Konstruktor do tworzenia panelu czarów.
     *
     * @param tekstura Tekstura Panelu
     * @param x pozycja X panelu
     * @param y pozycja Y panelu
     */
    public SpellActor(Texture tekstura, int x, int y) {
        super(tekstura, x, y);
    }

    /**
     * Konstruktor Czaru
     *
     * @param tekstura Tekstura czaru
     * @param x Pozycja X czaru
     * @param y Pozycja Y czaru
     * @param bohater Ref. do obikektu bohatera
     * @param a Referencja do obiketu Assets
     * @param gs Referencja do obiketu Game Status
     */
    public SpellActor(Texture tekstura, int x, int y, Bohater bohater, Assets a, GameStatus gs) {
        super(tekstura, x, y);
        this.bohater = bohater;
        this.dodajListnera();
        this.a = a;
        this.gs = gs;
    }

    private void dodajListnera() {
        final SpellActor sA = this;
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Kliknięto w czar");
                SpellCaster spellCaster = new SpellCaster(bohater, a, gs, sA);
            }
        });
    }

    /**
     *
     */
    public static void animateDamage() {

    }

    /**
     * GETTERS AND SETTERS *
     */
    /**
     * Zwraca zasięg czaru.
     *
     * @return
     */
    public int getZasieg() {
        return zasieg;
    }

    /**
     * Ustala zasięg czaru.
     *
     * @param zasieg
     */
    public void setZasieg(int zasieg) {
        this.zasieg = zasieg;
    }

    /**
     * Zwraca wartość obrażeń zaklęcia
     *
     * @return
     */
    public int getDmg() {
        return dmg;
    }

    /**
     * Ustala wartość obrażęń zaklęcia.
     *
     * @param dmg
     */
    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

}
