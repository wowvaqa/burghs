/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.burghs;

import java.util.Random;

/**
 *
 * @author v
 */
public class Fight {

    /**
     * Zwraca ilość obrażeń po ataku bohatera
     *
     * @param bohaterAtakujacy referencja do obiektu bohatera atakującego
     * @param bohaterBroniacy referencja do obiketu bohatera broniącego się
     * @return Zwraca ilość obrażeń
     */
    static public int getObrazenia(Bohater bohaterAtakujacy, Bohater bohaterBroniacy) {

        System.out.println("Funkacja Fight.getObrazenia");

        Random rnd = new Random();
        System.out.println("Nastąpił atak mf");
        int atak = rnd.nextInt(bohaterAtakujacy.getAtak() + getAtakEkwipunkuBohaterAtakujacego(bohaterAtakujacy)
                + getAtakEfektyBohatera(bohaterAtakujacy) + 1);
        int obrona = rnd.nextInt(bohaterBroniacy.getObrona() + getObronaEkwipunkuBohaterBroniacego(bohaterBroniacy)
                + getObronaEfektyBohatera(bohaterBroniacy) + 1);
        System.out.println("Siła ataku:  " + atak);
        System.out.println("siła obrony: " + obrona);

        if (atak > 0) {
            System.out.println("Nastąpiło trafienie...");
            atak += getDamageEkwipunkuBohateraAtakujacego(bohaterAtakujacy);
            atak += getDamageEfektyBohatera(bohaterAtakujacy);
            
            for (SpellEffects sE: bohaterAtakujacy.getSpellEffects()){
                if (sE.isFightEffect()){
                    sE.dzialanie(sE.getFightEffects(), bohaterAtakujacy, bohaterBroniacy);
                }
            }
            
        } else {
            System.out.println("Pudło...");
        }

        int dmg = atak - (obrona + getArmorEkwipunkuBohateraAtakujacego(bohaterBroniacy)
                + getArmorEfektyBohatera(bohaterBroniacy));

        if (dmg < 0) {
            dmg = 0;
        }
        System.out.println("damage: " + dmg);
        if (dmg > 0) {
            bohaterBroniacy.setActualHp(bohaterBroniacy.getActualHp() - dmg);
        }
        if (bohaterBroniacy.getActualHp() <= 0) {
            bohaterBroniacy.remove();
            System.out.println("Smierc Bohatera broniacego się");
        }

        bohaterAtakujacy.setPozostaloRuchow(bohaterAtakujacy.getPozostaloRuchow() - 1);

        bohaterBroniacy.aktualizujTeksture();

        return dmg;
    }

    /**
     * Zwraca ilość obrażeń po ataku bohatera na zamek
     *
     * @param bohaterAtakujacy Referencja do obiektu bohatera atakującego
     * @param castle Referencja do obiektu zamku
     * @return Ilosć obrażeń
     */
    static public int getObrazenia(Bohater bohaterAtakujacy, Castle castle) {

        System.out.println("Funkacja Fight.getObrazenia");

        Random rnd = new Random();
        System.out.println("Nastąpił atak na zamek");
        int atak = rnd.nextInt(bohaterAtakujacy.getAtak() + getAtakEkwipunkuBohaterAtakujacego(bohaterAtakujacy)
                + getAtakEfektyBohatera(bohaterAtakujacy) + 1);
        int obrona = rnd.nextInt(castle.getObrona() + 1);
        System.out.println("Siła ataku:  " + atak);
        System.out.println("siła obrony: " + obrona);

        if (atak > 0) {
            System.out.println("Nastąpiło trafienie...");
            atak += getDamageEkwipunkuBohateraAtakujacego(bohaterAtakujacy);
            atak += getDamageEfektyBohatera(bohaterAtakujacy);
        } else {
            System.out.println("Pudło...");
        }

        int dmg = atak - obrona;
        if (dmg < 0) {
            dmg = 0;
        }
        System.out.println("damage: " + dmg);
        if (dmg > 0) {
            castle.setActualHp(castle.getActualHp() - dmg);
        }
        if (castle.getActualHp() <= 0) {
            System.out.println("Zamek nie posiada już obrońców - można go zająć");
            castle.setActualHp(0);
        }

        bohaterAtakujacy.setPozostaloRuchow(bohaterAtakujacy.getPozostaloRuchow() - 1);

        return dmg;
    }

    /**
     *
     * @param bohaterAtakujacy Referencja do obiektu bohatera atakującego
     * @param mob Referencja do obiektu moba
     * @return Ilość obrażeń
     */
    static public int getObrazenia(Bohater bohaterAtakujacy, Mob mob) {

        mob.mobZaatakowany(bohaterAtakujacy.getPozXnaMapie(), bohaterAtakujacy.getPozYnaMapie());

        Random rnd = new Random();
        System.out.println("Nastąpił atak na moba");
        int atak = rnd.nextInt(bohaterAtakujacy.getAtak() + getAtakEkwipunkuBohaterAtakujacego(bohaterAtakujacy)
                + getAtakEfektyBohatera(bohaterAtakujacy) + 1);
        int obrona = rnd.nextInt(mob.getObrona() + 1);
        System.out.println("Siła ataku:  " + atak);
        System.out.println("siła obrony: " + obrona);

        if (atak > 0) {
            System.out.println("Nastąpiło trafienie...");
            atak += getDamageEkwipunkuBohateraAtakujacego(bohaterAtakujacy);
            atak += getDamageEfektyBohatera(bohaterAtakujacy);
        } else {
            System.out.println("Pudło...");
        }

        int dmg = atak - obrona;
        if (dmg < 0) {
            dmg = 0;
        }
        System.out.println("damage: " + dmg);
        if (dmg > 0) {
            mob.setAktualneHp(mob.getAktualneHp() - dmg);
        }
        if (mob.getAktualneHp() <= 0) {
            System.out.println("Śmierć moba.");
            mob.setAktualneHp(0);
            bohaterAtakujacy.setExp(bohaterAtakujacy.getExp() + mob.getExpReward());
        }

        bohaterAtakujacy.setPozostaloRuchow(bohaterAtakujacy.getPozostaloRuchow() - 1);

        return dmg;
    }

    /**
     * Zwraca ilość obrażeń po ataku Moba
     *
     * @param mob Referencja do obektu Moba
     * @param bohaterBroniacy referencja do obiketu bohatera broniącego się
     * @return Zwraca ilość obrażeń
     */
    static public int getObrazenia(Mob mob, Bohater bohaterBroniacy) {

        System.out.println("Funkacja Fight.getObrazenia");

        Random rnd = new Random();
        System.out.println("Nastąpił atak mf");
        int atak = rnd.nextInt(mob.getAtak() + 1);
        int obrona = rnd.nextInt(bohaterBroniacy.getObrona() + getObronaEkwipunkuBohaterBroniacego(bohaterBroniacy)
                + getObronaEfektyBohatera(bohaterBroniacy) + 1);
        System.out.println("Siła ataku:  " + atak);
        System.out.println("siła obrony: " + obrona);
        int dmg = atak - (obrona + getArmorEkwipunkuBohateraAtakujacego(bohaterBroniacy)
                + getArmorEfektyBohatera(bohaterBroniacy));
        if (dmg < 0) {
            dmg = 0;
        }
        System.out.println("damage: " + dmg);
        if (dmg > 0) {
            bohaterBroniacy.setActualHp(bohaterBroniacy.getActualHp() - dmg);
        }
        if (bohaterBroniacy.getActualHp() <= 0) {
            bohaterBroniacy.remove();
            System.out.println("Smierc Bohatera broniacego się");
        }

        bohaterBroniacy.aktualizujTeksture();

        return dmg;
    }

    /**
     * Liczy obrażenia dla rzucenia czaru przez bohatera na moba.
     *
     * @param bohaterAtakujacy
     * @param mob
     * @param spell
     * @return
     */
    static public int getSpellObrazenia(Bohater bohaterAtakujacy, Mob mob, SpellActor spell) {
        mob.mobZaatakowany(bohaterAtakujacy.getPozXnaMapie(), bohaterAtakujacy.getPozYnaMapie());

        Random rnd = new Random();
        System.out.println("Nastąpił sepll na moba");
        int damage = rnd.nextInt(bohaterAtakujacy.getMoc() + 1) + spell.getDmg();
        int obrona = rnd.nextInt(mob.getObrona() + 1);
        System.out.println("Siła ataku:  " + damage);
        System.out.println("siła obrony: " + obrona);
        int dmg = damage - obrona;
        if (dmg < 0) {
            dmg = 0;
        }
        System.out.println("damage: " + dmg);
        if (dmg > 0) {
            mob.setAktualneHp(mob.getAktualneHp() - dmg);
        }
        if (mob.getAktualneHp() <= 0) {
            System.out.println("Śmierć moba.");
            mob.setAktualneHp(0);
            bohaterAtakujacy.setExp(bohaterAtakujacy.getExp() + mob.getExpReward());
        }

        bohaterAtakujacy.setPozostaloRuchow(bohaterAtakujacy.getPozostaloRuchow() - 1);
        bohaterAtakujacy.setActualMana(bohaterAtakujacy.getActualMana() - spell.getKoszt());

        return dmg;
    }

    static public int getSpellObrazenia(Bohater bohaterAtakujacy, Bohater bohaterBroniacy, SpellActor spell) {
        System.out.println("Funkacja Fight.getObrazenia");

        Random rnd = new Random();
        int damage = rnd.nextInt(bohaterAtakujacy.getMoc() + 1) + spell.getDmg();
        int obrona = rnd.nextInt(bohaterBroniacy.getObrona() + getObronaEkwipunkuBohaterBroniacego(bohaterBroniacy)
                + getObronaEfektyBohatera(bohaterBroniacy) + 1);
        System.out.println("Siła ataku:  " + damage);
        System.out.println("siła obrony: " + obrona);
        int dmg = damage - (obrona + getArmorEkwipunkuBohateraAtakujacego(bohaterBroniacy)
                + getArmorEfektyBohatera(bohaterBroniacy));
        if (dmg < 0) {
            dmg = 0;
        }
        System.out.println("damage: " + dmg);
        if (dmg > 0) {
            bohaterBroniacy.setActualHp(bohaterBroniacy.getActualHp() - dmg);
        }
        if (bohaterBroniacy.getActualHp() <= 0) {
            bohaterBroniacy.remove();
            System.out.println("Smierc Bohatera broniacego się");
        }

        bohaterAtakujacy.setPozostaloRuchow(bohaterAtakujacy.getPozostaloRuchow() - 1);
        bohaterAtakujacy.setActualMana(bohaterAtakujacy.getActualMana() - spell.getKoszt());

        bohaterBroniacy.aktualizujTeksture();

        return dmg;
    }

    /**
     * Zwraca sume pancerza itemkó bohatera
     *
     * @param bohaterBroniacy
     * @return
     */
    static public int getArmorEkwipunkuBohateraAtakujacego(Bohater bohaterBroniacy) {
        int sumaObrazen = 0;
        sumaObrazen += bohaterBroniacy.getItemGlowa().getArmor();
        sumaObrazen += bohaterBroniacy.getItemKorpus().getArmor();
        sumaObrazen += bohaterBroniacy.getItemLewaReka().getArmor();
        sumaObrazen += bohaterBroniacy.getItemPrawaReka().getArmor();
        sumaObrazen += bohaterBroniacy.getItemNogi().getArmor();
        sumaObrazen += bohaterBroniacy.getItemStopy().getArmor();

        System.out.println("Sumba pancerza itemków: " + sumaObrazen);

        return sumaObrazen;
    }

    /**
     * Zwraca wartość obrażeń ekwipunku zadanego bohatera.
     *
     * @param bohaterAtakujacy
     * @return Wartość obrażeń
     */
    static public int getDamageEkwipunkuBohateraAtakujacego(Bohater bohaterAtakujacy) {
        int sumaObrazen = 0;
        sumaObrazen += bohaterAtakujacy.getItemGlowa().getDmg();
        sumaObrazen += bohaterAtakujacy.getItemKorpus().getDmg();
        sumaObrazen += bohaterAtakujacy.getItemLewaReka().getDmg();
        sumaObrazen += bohaterAtakujacy.getItemPrawaReka().getDmg();
        sumaObrazen += bohaterAtakujacy.getItemNogi().getDmg();
        sumaObrazen += bohaterAtakujacy.getItemStopy().getDmg();

        System.out.println("Sumba obrażeń itemków: " + sumaObrazen);

        return sumaObrazen;
    }

    /**
     *
     * @param bohaterBroniacy referencja do obiketu bohatera broniącego się
     * @return zwraca wartość obrony dla wszystkich itemków bohatera broniącego
     * się
     */
    static public int getObronaEkwipunkuBohaterBroniacego(Bohater bohaterBroniacy) {
        int sumaObrony = 0;
        sumaObrony += bohaterBroniacy.getItemGlowa().getObrona();
        sumaObrony += bohaterBroniacy.getItemKorpus().getObrona();
        sumaObrony += bohaterBroniacy.getItemLewaReka().getObrona();
        sumaObrony += bohaterBroniacy.getItemPrawaReka().getObrona();
        sumaObrony += bohaterBroniacy.getItemNogi().getObrona();
        sumaObrony += bohaterBroniacy.getItemStopy().getObrona();

        System.out.println("Suma obrony itemków: " + sumaObrony);

        return sumaObrony;
    }

    /**
     * Zwraca sume pancerza dla efektów czarów i efektó mikstur.
     *
     * @param bohater
     * @return
     */
    static public int getArmorEfektyBohatera(Bohater bohater) {
        int sumaEfektArmor = 0;
        for (Effect efekty : bohater.getEfekty()) {
            sumaEfektArmor += efekty.getEfektArmor();
        }
        for (SpellEffects sE : bohater.getSpellEffects()) {
            sumaEfektArmor += sE.getEfektArmor();
        }
        
        System.out.println("Suma pancerza dla efektów: " + sumaEfektArmor);
        
        return sumaEfektArmor;
    }

    /**
     * Zwraca sume obrażeń dla efektów czarów i efektó mikstur.
     *
     * @param bohater
     * @return
     */
    static public int getDamageEfektyBohatera(Bohater bohater) {
        int sumaEfektDamage = 0;
        for (Effect efekty : bohater.getEfekty()) {
            sumaEfektDamage += efekty.getEfektDmg();
        }
        for (SpellEffects sE : bohater.getSpellEffects()) {
            sumaEfektDamage += sE.getEfektDmg();
        }
        
        System.out.println("Suma obrażeń dla efektów: " + sumaEfektDamage);
        
        return sumaEfektDamage;
    }

    /**
     * Zwraca wartosć ataku efektów zadanego bohatera
     *
     * @param bohater Referencja do obiektu bohatera
     * @return ilość punktów ataku
     */
    static public int getAtakEfektyBohatera(Bohater bohater) {
        int sumaEfektAtak = 0;
        for (Effect efekty : bohater.getEfekty()) {
            sumaEfektAtak += efekty.getEfektAtak();
        }
        for (SpellEffects sE : bohater.getSpellEffects()) {
            sumaEfektAtak += sE.getEfektAtak();
        }
        return sumaEfektAtak;
    }

    /**
     * Zwraca wartosć obrony efektów zadanego bohatera
     *
     * @param bohater Referencja do obiektu bohatera
     * @return ilość punktów obrony
     */
    static public int getObronaEfektyBohatera(Bohater bohater) {
        int sumaEfektObrona = 0;
        for (Effect efekty : bohater.getEfekty()) {
            sumaEfektObrona += efekty.getEfektObrona();
        }
        for (SpellEffects sE : bohater.getSpellEffects()) {
            sumaEfektObrona += sE.getEfektObrona();
        }
        return sumaEfektObrona;
    }

    /**
     *
     * @param bohaterAtakujacy referencja do obiektu bohatera atakującego
     * @return zwraca wartość ataku dla wszystkich itemków bohatera atakującego
     */
    static public int getAtakEkwipunkuBohaterAtakujacego(Bohater bohaterAtakujacy) {
        int sumaAtaku = 0;
        sumaAtaku += bohaterAtakujacy.getItemGlowa().getAtak();
        sumaAtaku += bohaterAtakujacy.getItemKorpus().getAtak();
        sumaAtaku += bohaterAtakujacy.getItemLewaReka().getAtak();
        sumaAtaku += bohaterAtakujacy.getItemPrawaReka().getAtak();
        sumaAtaku += bohaterAtakujacy.getItemNogi().getAtak();
        sumaAtaku += bohaterAtakujacy.getItemStopy().getAtak();

        System.out.println("Suma ataku itemków: " + sumaAtaku);

        return sumaAtaku;
    }

    /**
     * Zwraca sumę szybkości dla wszsytkich itemków w które wyposażony jest
     * bohater
     *
     * @param bohaterAtakujacy referencja do obiketu bohatera dla którego ma
     * zostać zsumowana ilość punktów szybkości
     * @return
     */
    static public int getSzybkoscEkwipunkuBohatera(Bohater bohaterAtakujacy) {
        int sumaSzybkosci = 0;
        sumaSzybkosci += bohaterAtakujacy.getItemGlowa().getSzybkosc();
        sumaSzybkosci += bohaterAtakujacy.getItemKorpus().getSzybkosc();
        sumaSzybkosci += bohaterAtakujacy.getItemLewaReka().getSzybkosc();
        sumaSzybkosci += bohaterAtakujacy.getItemPrawaReka().getSzybkosc();
        sumaSzybkosci += bohaterAtakujacy.getItemNogi().getSzybkosc();
        sumaSzybkosci += bohaterAtakujacy.getItemStopy().getSzybkosc();

        System.out.println("Suma zwiększenia szybkości: " + sumaSzybkosci);

        return sumaSzybkosci;
    }

}
