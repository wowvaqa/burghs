package com.mygdx.burghs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import enums.DostepneItemki;

/**
 * Zaklecia
 *
 * @author v
 */
public class SpellActor extends DefaultActor {

    private Assets a;
    private GameStatus gs;
    private Bohater bohater;
    private DostepneItemki spell;

    private int zasieg = 0;

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
     * @param spell Opisuje jakim czarem jest obiekt
     */
    public SpellActor(Texture tekstura, int x, int y, Bohater bohater, DostepneItemki spell, Assets a, GameStatus gs) {
        super(tekstura, x, y);
        this.bohater = bohater;
        this.dodajListnera();
        this.spell = spell;
        this.a = a;
        this.gs = gs;

    }

    private void dodajListnera() {
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Kliknięto w czar");
                SpellCaster spellCaster = new SpellCaster(bohater, a, gs, zasieg);
            }
        });
    }
}
