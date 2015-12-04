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
    
    static int getObrazenia(Bohater bohaterAtakujacy, Bohater bohaterBroniacy){
        
        Random rnd = new Random();
        System.out.println("Nastąpił atak mf");
        int atak = rnd.nextInt(bohaterAtakujacy.getAtak() + 1);
        int obrona = rnd.nextInt(bohaterBroniacy.getObrona() + 1);
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
           // bohaterBroniacy.remove();            
        }                
        
        return dmg;
    }
    
}
