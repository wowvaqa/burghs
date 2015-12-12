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
     * @param bohaterAtakujacy  referencja do obiektu bohatera atakującego
     * @param bohaterBroniacy   referencja do obiketu bohatera broniącego się
     * @return Zwraca ilość obrażeń
     */
    static public int getObrazenia(Bohater bohaterAtakujacy, Bohater bohaterBroniacy){
        
        System.out.println("Funkacja Fight.getObrazenia");
        
        Random rnd = new Random();
        System.out.println("Nastąpił atak mf");
        int atak = rnd.nextInt(bohaterAtakujacy.getAtak() + getAtakEkwipunkuBohaterAtakujacego(bohaterAtakujacy) + 1);
        int obrona = rnd.nextInt(bohaterBroniacy.getObrona() + getObronaEkwipunkuBohaterBroniacego(bohaterBroniacy) + 1);
        System.out.println("Siła ataku:  " + atak);
        System.out.println("siła obrony: " + obrona);
        int dmg = atak - obrona;
        if (dmg < 0)
            dmg = 0;
        System.out.println("damage: " + dmg);
        if (dmg > 0){
            bohaterBroniacy.setHp(bohaterBroniacy.getHp() - dmg);
        }
        if (bohaterBroniacy.getHp() <= 0){
            bohaterBroniacy.remove();
            System.out.println("Smierc Bohatera broniacego się");
        }              
        
        bohaterAtakujacy.setPozostaloRuchow(bohaterAtakujacy.getPozostaloRuchow() - 1);
        
        return dmg;
    }
    
    /**
     * Zwraca ilość obrażeń po ataku bohatera na zamek
     * @param bohaterAtakujacy Referencja do obiektu bohatera atakującego
     * @param castle Referencja do obiektu zamku
     * @return Ilosć obrażeń
     */
    static public int getObrazenia(Bohater bohaterAtakujacy, Castle castle){
        
        System.out.println("Funkacja Fight.getObrazenia");
        
        Random rnd = new Random();
        System.out.println("Nastąpił atak na zamek");
        int atak = rnd.nextInt(bohaterAtakujacy.getAtak() + getAtakEkwipunkuBohaterAtakujacego(bohaterAtakujacy) + 1);
        int obrona = rnd.nextInt(castle.getObrona() + 1);
        System.out.println("Siła ataku:  " + atak);
        System.out.println("siła obrony: " + obrona);
        int dmg = atak - obrona;
        if (dmg < 0)
            dmg = 0;
        System.out.println("damage: " + dmg);
        if (dmg > 0){
            castle.setActualHp(castle.getActualHp()- dmg);
        }
        if (castle.getActualHp()<= 0){            
            System.out.println("Zamek nie posiada już obrońców - można go zająć");
        }              
        
        bohaterAtakujacy.setPozostaloRuchow(bohaterAtakujacy.getPozostaloRuchow() - 1);
        
        return dmg;
    }
    
    /**
     * 
     * @param bohaterBroniacy referencja do obiketu bohatera broniącego się
     * @return zwraca wartość obrony dla wszystkich itemków bohatera broniącego się
     */
    static public int getObronaEkwipunkuBohaterBroniacego(Bohater bohaterBroniacy){
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
     * 
     * @param bohaterAtakujacy referencja do obiektu bohatera atakującego
     * @return zwraca wartość ataku dla wszystkich itemków bohatera atakującego
     */
    static public int getAtakEkwipunkuBohaterAtakujacego(Bohater bohaterAtakujacy){
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
     * @param bohaterAtakujacy referencja do obiketu bohatera dla którego 
     * ma zostać zsumowana ilość punktów szybkości
     * @return 
     */
    static public int getSzybkoscEkwipunkuBohatera(Bohater bohaterAtakujacy){
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
