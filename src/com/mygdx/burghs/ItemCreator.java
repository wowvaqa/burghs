package com.mygdx.burghs;

import enums.DostepneItemki;
import enums.CzesciCiala;

/**
 * Zwraca item
 *
 * @author v
 */
public class ItemCreator {
    
    private final Assets a = new Assets();

    public Item utworzItem(DostepneItemki dostepneItemki) {
        Item item = new Item(a.texLeatherCap);        

        switch (dostepneItemki) {
// GŁOWA ======================================================================            
            case LnianaCzapka:
                item.setNazwa("Lniana Czapka");
                item.setAtak(0);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.glowa);  
                item.getSprite().setTexture(a.texLinenCap);
                break;
            case SkorzanaCzapka:
                item.setNazwa("Skorzana czapka");
                item.setAtak(0);
                item.setObrona(1);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.glowa);
                //item.setSprite(new Sprite(a.texLeatherCap));                             
                break;
// KORPUS ======================================================================                
            case LnianaKoszula:
                item.setNazwa("Lniana Koszula");
                item.setAtak(0);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.praweRamie);
                item.getSprite().setTexture(a.texLinenShirt);
                break;
// BROŃ RĘCE ===================================================================
            case Piesci:
                item.setNazwa("Gole Piesci");
                item.setAtak(0);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.praweRamie);                
                break;
// NOGI ========================================================================
            case LnianeSpodnie:
                item.setNazwa("Lniane Spodnie");
                item.setAtak(0);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.nogi);
                item.getSprite().setTexture(a.texLinenTousers);
                break;
// OBUWIE ======================================================================
            case LnianeButy:
                item.setNazwa("Lniane Obuwie");
                item.setAtak(0);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.stopy);                
                item.getSprite().setTexture(a.texLinenShoes);
                break;
        }
        
        item.setSize(100, 100);
        return item;
    }
}
