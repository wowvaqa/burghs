package com.mygdx.burghs;

/**
 * Klasa zarządza zachowanie nowej gry
 * @author v
 */
public class NewGame {
    
    /**
     * Zwiększa ilość graczy
     * @param iloscGraczy
     * @return 
     */
    static public int dodajGracza(int iloscGraczy){
        int tmpIloscGraczy = iloscGraczy;
        
        tmpIloscGraczy += 1;
        if (tmpIloscGraczy > 4){
            tmpIloscGraczy = 2;
        }
        return tmpIloscGraczy;
    }
    
    /**
     * Zmniejsza ilosc graczy
     * @param iloscGraczy
     * @return 
     */
    static public int odejmijGracza(int iloscGraczy){
        iloscGraczy -= 1;
        if (iloscGraczy < 2){
            iloscGraczy = 4;
        }
        return iloscGraczy;
    }
    
}
