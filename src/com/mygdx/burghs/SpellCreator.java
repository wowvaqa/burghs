/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.burghs;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.burghs.Screens.DialogScreen;
import enums.Spells;
import java.util.ArrayList;

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
     *
     * @param spells Rodzaj zaklęcia z enum Spells
     * @param bohater
     * @return Spell Actor
     */
    public SpellActor utworzSpell(Spells spells, Bohater bohater) {

        SpellActor spell = new SpellActor(a.texFist, 0, 0, bohater, a, gs);

        switch (spells) {

            case FireBall:
                spell.getSprite().setTexture(a.texSpellFireBall);
                spell.setDmg(3);
                spell.setKoszt(1);
                spell.setSpellEffects(new ArrayList<SpellEffects>());
                spell.getSpellEffects().add(new SpellEffects());
                spell.getSpellEffects().get(0).setEfektDmg(3);
                spell.setRodzajCzaru(spells);
                break;

            case Frozen:
                spell.getSprite().setTexture(a.texSpellFreez);
                spell.setSpellEffects(new ArrayList<SpellEffects>());
                spell.getSpellEffects().add(new SpellEffects());
                spell.getSpellEffects().get(0).setIkona(new EffectActor(a.texSpellFreez, 0, 0));
                spell.setRodzajCzaru(spells);
                spell.setKoszt(1);
                spell.getSpellEffects().get(0).setOpis("Spowolnienie spowodowane schłodzeniem");
                spell.getSpellEffects().get(0).getIkona().addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        DialogScreen dialogScreen = new DialogScreen("Chlod", a.skin, "Spowolnienie spowodowane schłodzeniem", Assets.stage01MapScreen);
                    }
                });
                break;

            case Rage:
                spell.setSpellWorksOnlyForCaster(true);
                spell.getSprite().setTexture(a.texSpellRage);
                spell.setSpellEffects(new ArrayList<SpellEffects>());
                spell.getSpellEffects().add(new SpellEffects());
                spell.getSpellEffects().get(0).setIkona(new EffectActor(a.texSpellRage, 0, 0));
                spell.setRodzajCzaru(spells);
                spell.setKoszt(1);
                spell.getSpellEffects().get(0).setOpis("Zwiększenie ataku +1 do końca tury");
                spell.getSpellEffects().get(0).getIkona().addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        DialogScreen dialogScreen = new DialogScreen("Gniew", a.skin, "Zwiększenie ataku +1 do końca tury", Assets.stage01MapScreen);
                    }
                });
                break;

            case Haste:
                spell.setSpellWorksOnlyForCaster(true);
                spell.getSprite().setTexture(a.texSpellHaste);
                spell.setKoszt(1);
                spell.setSpellEffects(new ArrayList<SpellEffects>());
                spell.getSpellEffects().add(new SpellEffects());
                spell.setRodzajCzaru(spells);
                break;

            case Cure:
                spell.setSpellWorksOnlyForCaster(true);
                spell.getSprite().setTexture(a.texSpellCure);
                spell.setKoszt(1);
                spell.setSpellEffects(new ArrayList<SpellEffects>());
                spell.getSpellEffects().add(new SpellEffects());
                spell.setRodzajCzaru(spells);
                break;

            case SongOfGlory:
                spell.setSpellWorksOnlyForPlayersHeroes(true);
                spell.getSprite().setTexture(a.texSpellSongOfGlory);
                spell.setKoszt(1);
                spell.setZasieg(5);
                spell.setSpellEffects(new ArrayList<SpellEffects>());
                spell.getSpellEffects().add(new SpellEffects());
                spell.getSpellEffects().get(0).setIkona(new EffectActor(a.texSpellSongOfGlory, 0, 0));
                spell.setRodzajCzaru(spells);
                spell.setKoszt(1);
                spell.getSpellEffects().get(0).setOpis("Zwiększenie ataku +1 do końca tury");
                spell.getSpellEffects().get(0).getIkona().addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        DialogScreen dialogScreen = new DialogScreen("Gniew", a.skin, "Zwiększenie ataku +1 do końca tury", Assets.stage01MapScreen);
                    }
                });
                break;

            case Discouragement:
                spell.setSpellWorksOnlyForPlayersHeroes(true);
                spell.getSprite().setTexture(a.texSpellDiscouragement);
                spell.setKoszt(2);
                spell.setSpellEffects(new ArrayList<SpellEffects>());
                spell.getSpellEffects().add(new SpellEffects());
                spell.setRodzajCzaru(spells);
                break;
            case Fury:
                spell.setSpellWorksOnlyForPlayersHeroes(true);
                spell.getSprite().setTexture(a.texSpellFury);
                spell.setKoszt(2);
                spell.setSpellEffects(new ArrayList<SpellEffects>());
                spell.getSpellEffects().add(new SpellEffects());
                spell.setRodzajCzaru(spells);
                break;
            case Charge:
                spell.setSpellWorksOnlyForPlayersHeroes(true);
                spell.getSprite().setTexture(a.texSpellCharge);
                spell.setKoszt(2);
                spell.setSpellEffects(new ArrayList<SpellEffects>());
                spell.getSpellEffects().add(new SpellEffects());
                spell.setRodzajCzaru(spells);
                break;
            case FinalJudgment:
                spell.setSpellWorksOnlyForPlayersHeroes(true);
                spell.getSprite().setTexture(a.texSpellFinalJudgment);
                spell.setKoszt(2);
                spell.setSpellEffects(new ArrayList<SpellEffects>());
                spell.getSpellEffects().add(new SpellEffects());
                spell.setRodzajCzaru(spells);
                break;
        }

        spell.setSize(50, 50);

        return spell;
    }
}
