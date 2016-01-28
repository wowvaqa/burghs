/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.burghs;

import enums.Spells;

/**
 * Kalsa tworzy zaklęcia
 *
 * @author v
 */
public class SpellCreator {

    private final Assets a;
    private final GameStatus gs;

    public SpellCreator(Assets a, GameStatus gs) {
        this.a = a;
        this.gs = gs;
    }
    
    /**
     * Tworzy zaklęcie
     * @param spells Rodzaj zaklęcia z enum Spells
     * @return Spell Actor
     */
    public SpellActor utworzSpell(Spells spells, Bohater bohater){
        
        SpellActor spell = new SpellActor(a.texFist, 0, 0, bohater, a, gs);
        
        switch (spells){
            case FireBall:
                spell.getSprite().setTexture(a.texSpellFireBall);
                break;
            case Frozen:
                spell.getSprite().setTexture(a.texSpellFreez);
                break;
        }
        
        spell.setSize(50, 50);
        
        return spell;
    }
}
