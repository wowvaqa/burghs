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
    
    static public int getObrazenia(Bohater bohaterAtakujacy, Bohater bohaterBroniacy){
        
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
           // bohaterBroniacy.remove();            
        }                
        
        return dmg;
    }
    
    /**
     * 
     * @param bohaterBroniacy referencja do obiketu bohatera broniącego się
     * @return zwraca wartość obrony dla wszystkich itemków bohatera broniącego się
     */
    static private int getObronaEkwipunkuBohaterBroniacego(Bohater bohaterBroniacy){
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
    static private int getAtakEkwipunkuBohaterAtakujacego(Bohater bohaterAtakujacy){
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
    
}
