package com.mygdx.burghs;

import enums.DostepneItemki;
import enums.CzesciCiala;

/**
 * Zwraca item
 *
 * @author v
 */
public class ItemCreator {

    public static Item utworzItem(DostepneItemki dostepneItemki) {
        Item item = new Item();

        switch (dostepneItemki) {
// GŁOWA ======================================================================            
            case LnianaCzapka:
                item.setNazwa("Lniana Czapka");
                item.setAtak(0);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.glowa);
                break;
            case SkorzanaCzapka:
                item.setNazwa("Skorzana czapka");
                item.setAtak(0);
                item.setObrona(1);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.glowa);
                break;
// KORPUS ======================================================================                
            case LnianaKoszula:
                item.setNazwa("Lniana Koszula");
                item.setAtak(0);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.praweRamie);
                break;
            case Piesci:
                item.setNazwa("Gole Piesci");
                item.setAtak(0);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.praweRamie);
                break;

            case LnianeSpodnie:
                item.setNazwa("Lniane Spodnie");
                item.setAtak(0);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.nogi);
                break;

            case LnianeButy:
                item.setNazwa("Lniane Obuwie");
                item.setAtak(0);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.stopy);
                break;
        }
        return item;
    }
}
