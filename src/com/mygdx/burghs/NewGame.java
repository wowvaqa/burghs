package com.mygdx.burghs;

import enums.KlasyPostaci;
import static enums.KlasyPostaci.Berserk;
import static enums.KlasyPostaci.Lowca;
import static enums.KlasyPostaci.Obronca;
import static enums.KlasyPostaci.Twierdza;

/**
 * Klasa zarządza zachowanie nowej gry
 *
 * @author v
 */
public class NewGame {

   static public KlasyPostaci klasaPostaciGracz01 = KlasyPostaci.Berserk; 
   static public KlasyPostaci klasaPostaciGracz02 = KlasyPostaci.Berserk;
   static public KlasyPostaci klasaPostaciGracz03 = KlasyPostaci.Berserk;
   static public KlasyPostaci klasaPostaciGracz04 = KlasyPostaci.Berserk;
   
   private static final Assets a = new Assets();
   
//   private static final DefaultActor portretBerserk = new DefaultActor(a.mobHumanTex, 0, 0);
//   private static final DefaultActor portretObronca = new DefaultActor(a.mobDwarfTex, 0, 0);
//   private static final DefaultActor portretLowca = new DefaultActor(a.mobElfTex, 0, 0);
//   private static final DefaultActor portretTwierdza = new DefaultActor(a.mobOrkTex, 0, 0);

    /**
     * Zwiększa ilość graczy
     *
     * @param iloscGraczy
     * @return
     */
    static public int dodajGracza(int iloscGraczy) {
        int tmpIloscGraczy = iloscGraczy;

        tmpIloscGraczy += 1;
        if (tmpIloscGraczy > 4) {
            tmpIloscGraczy = 2;
        }
        return tmpIloscGraczy;
    }

    /**
     * Zmniejsza ilosc graczy
     *
     * @param iloscGraczy
     * @return
     */
    static public int odejmijGracza(int iloscGraczy) {
        iloscGraczy -= 1;
        if (iloscGraczy < 2) {
            iloscGraczy = 4;
        }
        return iloscGraczy;
    }
    
    /**
     * Pobiera klase postaci gracza i zwraca następną z enum-a
     * @param kP klasa postaci
     * @return 
     */
    static public KlasyPostaci nastepnaKlasaPostaci(KlasyPostaci kP){
        switch (kP){
            case Berserk:                
                return KlasyPostaci.Obronca;
            case Obronca:
                return KlasyPostaci.Lowca;
            case Lowca:
                return KlasyPostaci.Twierdza;
            case Twierdza:
                return KlasyPostaci.Berserk;                
        }
        return KlasyPostaci.Berserk;
    }
    
        /**
     * Pobiera klase postaci gracza i zwraca następną z enum-a
     * @param kP klasa postaci
     * @return 
     */
    static public KlasyPostaci poprzedniaKlasaPostaci(KlasyPostaci kP){
        switch (kP){
            case Berserk:                
                return KlasyPostaci.Twierdza;
            case Obronca:
                return KlasyPostaci.Berserk;
            case Lowca:
                return KlasyPostaci.Obronca;
            case Twierdza:
                return KlasyPostaci.Lowca;                
        }
        return KlasyPostaci.Berserk;
    }

    static public DefaultActor pobierzPortret(KlasyPostaci kP){
        switch (kP) {
            case Berserk:
                return new DefaultActor(a.mobHumanTex, 0, 0);
            case Lowca:
                return new DefaultActor(a.mobElfTex, 0, 0);
            case Obronca:
                return new DefaultActor(a.mobDwarfTex, 0, 0);
            case Twierdza:
                return new DefaultActor(a.mobOrkTex, 0, 0);
        }
        return null;
    }
    
    static public String pobierzTytul(KlasyPostaci kP) {                
        switch (kP) {
            case Berserk:
                return "Berserk";                
            case Obronca:
                return "Obronca";
            case Lowca:
                return "Lowca";
            case Twierdza:
                return "Twierdza";
        }
        return "error";
    }
    
    static public int pobierzAtak(KlasyPostaci kP) {                
        switch (kP) {
            case Berserk:
                return 6;                
            case Obronca:
                return 5;
            case Lowca:
                return 5;
            case Twierdza:
                return 5;
        }
        return 0;
    }
    
    static public int pobierzObrone(KlasyPostaci kP) {                
        switch (kP) {
            case Berserk:
                return 5;                
            case Obronca:
                return 6;
            case Lowca:
                return 5;
            case Twierdza:
                return 5;
        }
        return 0;
    }
    
    static public int pobierzSzybkosc(KlasyPostaci kP) {                
        switch (kP) {
            case Berserk:
                return 5;                
            case Obronca:
                return 5;
            case Lowca:
                return 6;
            case Twierdza:
                return 5;
        }
        return 0;
    }
    
    static public int pobierzHp(KlasyPostaci kP) {                
        switch (kP) {
            case Berserk:
                return 10;                
            case Obronca:
                return 10;
            case Lowca:
                return 10;
            case Twierdza:
                return 15;
        }
        return 0;
    }
}
