package com.mygdx.burghs;

import enums.AnimsTypes;
import java.util.Random;

/**
 * Klasa definiuje efekty czarów
 *
 * @author v
 */
public class SpellEffects {

    private int dlugoscTrwaniaEfektu = 0;

    private int efektAtak = 0;
    private int efektObrona = 0;
    private int efektSzybkosc = 0;
    private int efektHp = 0;
    private int efektMana = 0;
    private int efektDmg = 0;
    private int efektArmor = 0;

    private int zmianaHp;
    private String opis;

    private DefaultActor ikona;

    /**
     *
     * @param spell Referencja do obiketu czaru
     * @param a Referencja do obiketu Assts
     * @param bohaterCastujacy Referencja do obiketu bohatera rzucającego czar
     * @param obiketBroniacy Referencja do obiektu na którego czar jest rzucany
     * (bohater/mob)
     */
    public void dzialanie(SpellActor spell, Object obiketBroniacy, Bohater bohaterCastujacy, Assets a) {
        switch (spell.getRodzajCzaru()) {

            case FireBall:
                // Zadaje obrażenia
                if (spell.getKoszt() <= bohaterCastujacy.getActualMana()) {
                    AnimActor animActor = new AnimActor(new AnimationCreator().makeAniamtion(AnimsTypes.FireExplosionAnimation));
                    if (obiketBroniacy.getClass() == Bohater.class) {
                        Bohater tmpBoh = (Bohater) obiketBroniacy;
                        a.animujSpellLblDmg(tmpBoh.getX(), tmpBoh.getY(), bohaterCastujacy, tmpBoh, spell);
                        animActor.setPosition(tmpBoh.getX(), tmpBoh.getY());
                    } else if (obiketBroniacy.getClass() == Mob.class) {
                        System.out.println("przeciwnik jest mobem");
                        Mob tmpMob = (Mob) obiketBroniacy;
                        animActor.setPosition(tmpMob.getX(), tmpMob.getY());
                        a.animujSpellLblDmg(tmpMob.getX(), tmpMob.getY(), bohaterCastujacy, tmpMob, spell);
                    }
                    Assets.stage01MapScreen.addActor(animActor);
                } else {
                    System.out.println("Za mało MANY");
                }
                bohaterCastujacy.getSpells().clear();
                break;

            case Frozen:
                // Zmniejsza szybkosć na zadaną ilość tur
                Random rnd = new Random();
                if (spell.getKoszt() <= bohaterCastujacy.getActualMana()) {
                    if (obiketBroniacy.getClass() == Bohater.class) {
                        Bohater tmpBoh = (Bohater) obiketBroniacy;
                        int modSzybkosci = 1 + rnd.nextInt(bohaterCastujacy.getMoc() + 1);
                        this.dlugoscTrwaniaEfektu = modSzybkosci;
                        this.efektSzybkosc = -1 * modSzybkosci;
                        tmpBoh.setPozostaloRuchow(tmpBoh.getPozostaloRuchow() - modSzybkosci);
                        tmpBoh.getSpellEffects().add(this);
                        System.out.println("Efekt zmiany szybkosci: " + this.efektSzybkosc);
                    } else if (obiketBroniacy.getClass() == Mob.class) {
                        System.out.println("przeciwnik jest mobem");
                        Mob tmpMob = (Mob) obiketBroniacy;
                        int modSzybkosci = 1 + rnd.nextInt(bohaterCastujacy.getMoc() + 1);
                        tmpMob.setAktualnaSzybkosc(tmpMob.getAktualnaSzybkosc() - modSzybkosci);
                    }
                } else {
                    System.out.println("Za mało MANY");
                }
                bohaterCastujacy.getSpells().clear();
                break;

            case Rage:
                // Zwiększa atak +1 do końca tury.
                if (spell.getKoszt() <= bohaterCastujacy.getActualMana()) {
                    this.efektAtak = 1;
                    this.dlugoscTrwaniaEfektu = 1;
                    bohaterCastujacy.setActualMana(bohaterCastujacy.getActualMana() - spell.getKoszt());
                    bohaterCastujacy.getSpellEffects().add(this);
                } else {
                    System.out.println("Za mało MANY");
                }
                bohaterCastujacy.getSpells().clear();
                bohaterCastujacy.aktualizujEfektyBohatera();
                break;

            case Haste:
                // Zwiększa aktualną szybkość + 1
                if (spell.getKoszt() <= bohaterCastujacy.getActualMana()) {
                    System.out.println("Czar HASTE");
                    bohaterCastujacy.setActualMana(bohaterCastujacy.getActualMana() - spell.getKoszt());
                    bohaterCastujacy.setPozostaloRuchow(bohaterCastujacy.getPozostaloRuchow() + 1);
                } else {
                    System.out.println("Za mało MANY");
                }
                bohaterCastujacy.getSpells().clear();
                break;

            case Cure:
                // Leczy bohatera o 1
                if (spell.getKoszt() <= bohaterCastujacy.getActualMana()) {
                    System.out.println("Czar CURE");
                    bohaterCastujacy.setActualMana(bohaterCastujacy.getActualMana() - spell.getKoszt());
                    bohaterCastujacy.setActualHp(bohaterCastujacy.getActualHp() + 3);
                    if (bohaterCastujacy.getActualHp() > bohaterCastujacy.getHp()) {
                        bohaterCastujacy.setActualHp(bohaterCastujacy.getHp());
                    }
                    bohaterCastujacy.aktualizujTeksture();
                } else {
                    System.out.println("Za mało MANY");
                }
                bohaterCastujacy.getSpells().clear();
                break;

            case SongOfGlory:
                System.out.println("Czar SONG OF GLORY");
//                if (spell.getKoszt() <= bohaterCastujacy.getActualMana()) {
//                    this.efektAtak = 1;
//                    this.dlugoscTrwaniaEfektu = 1;
//                    bohaterCastujacy.setActualMana(bohaterCastujacy.getActualMana() - spell.getKoszt());
//                    bohaterCastujacy.getSpellEffects().add(this);
//                } else {
//                    System.out.println("Za mało MANY");
//                }
//                bohaterCastujacy.getSpells().clear();
//                bohaterCastujacy.aktualizujEfektyBohatera();
                break;
            case Discouragement:
                break;
            case Fury:
                break;
            case Charge:
                break;
            case FinalJudgment:
                break;
        }
    }

    /**
     *
     * @return
     */
    public int getDlugoscTrwaniaEfektu() {
        return dlugoscTrwaniaEfektu;
    }

    /**
     *
     * @param dlugoscTrwaniaEfektu
     */
    public void setDlugoscTrwaniaEfektu(int dlugoscTrwaniaEfektu) {
        this.dlugoscTrwaniaEfektu = dlugoscTrwaniaEfektu;
    }

    /**
     *
     * @return
     */
    public int getEfektAtak() {
        return efektAtak;
    }

    /**
     *
     * @param efektAtak
     */
    public void setEfektAtak(int efektAtak) {
        this.efektAtak = efektAtak;
    }

    /**
     *
     * @return
     */
    public int getEfektObrona() {
        return efektObrona;
    }

    /**
     *
     * @param efektObrona
     */
    public void setEfektObrona(int efektObrona) {
        this.efektObrona = efektObrona;
    }

    /**
     *
     * @return
     */
    public int getEfektSzybkosc() {
        return efektSzybkosc;
    }

    /**
     *
     * @param efektSzybkosc
     */
    public void setEfektSzybkosc(int efektSzybkosc) {
        this.efektSzybkosc = efektSzybkosc;
    }

    /**
     *
     * @return
     */
    public int getEfektHp() {
        return efektHp;
    }

    /**
     *
     * @param efektHp
     */
    public void setEfektHp(int efektHp) {
        this.efektHp = efektHp;
    }

    /**
     *
     * @return
     */
    public int getEfektMana() {
        return efektMana;
    }

    /**
     *
     * @param efektMana
     */
    public void setEfektMana(int efektMana) {
        this.efektMana = efektMana;
    }

    /**
     *
     * @return
     */
    public int getZmianaHp() {
        return zmianaHp;
    }

    /**
     *
     * @param zmianaHp
     */
    public void setZmianaHp(int zmianaHp) {
        this.zmianaHp = zmianaHp;
    }

    /**
     *
     * @return
     */
    public String getOpis() {
        return opis;
    }

    /**
     *
     * @param opis
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    /**
     *
     * @return
     */
    public DefaultActor getIkona() {
        return ikona;
    }

    /**
     *
     * @param ikona
     */
    public void setIkona(DefaultActor ikona) {
        this.ikona = ikona;
    }

    /**
     * Zwraca efekt zwiększenia obrażeń
     *
     * @return
     */
    public int getEfektDmg() {
        return efektDmg;
    }

    /**
     * Ustala efekt zwiększenia obrażeń
     *
     * @param efektDmg
     */
    public void setEfektDmg(int efektDmg) {
        this.efektDmg = efektDmg;
    }

    /**
     * Zwraca pancerz dla efektu.
     *
     * @return
     */
    public int getEfektArmor() {
        return efektArmor;
    }

    /**
     * Ustala pancerz dla efektu.
     *
     * @param efektArmor
     */
    public void setEfektArmor(int efektArmor) {
        this.efektArmor = efektArmor;
    }

}
