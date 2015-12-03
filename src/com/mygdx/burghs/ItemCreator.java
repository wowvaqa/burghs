package com.mygdx.burghs;

import enums.DostepneItemki;
import enums.CzesciCiala;

/**
 * Zwraca item
 *
 * @author v
 */
public class ItemCreator {

    private GameStatus gs;

    public ItemCreator(GameStatus gs) {
        this.gs = gs;
    }

    private Assets a;

    public Item utworzItem(DostepneItemki dostepneItemki, Assets a) {
        this.a = a;
        Item item = new Item(a.texFist, this.a, this.gs);

        switch (dostepneItemki) {
// GŁOWA ======================================================================         
            case Glowa:
                item.setNazwa("Gola Glowa");
                item.setAtak(0);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.glowa);
                item.getSprite().setTexture(a.texHead);
                break;
            case LnianaCzapka:
                item.setNazwa("Lniana Czapka");
                item.setAtak(0);
                item.setObrona(1);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.glowa);
                item.getSprite().setTexture(a.texLinenCap);
                break;
            case SkorzanaCzapka:
                item.setNazwa("Skorzana czapka");
                item.setAtak(0);
                item.setObrona(2);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.glowa);
                item.getSprite().setTexture(a.texLeatherCap);
                break;
// KORPUS ======================================================================                
            case LnianaKoszula:
                item.setNazwa("Lniana Koszula");
                item.setAtak(0);
                item.setObrona(1);
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
                item.setCzescCiala(CzesciCiala.rece);
                item.getSprite().setTexture(a.texFist);
                break;
            case Kij:
                item.setNazwa("Kij");
                item.setAtak(1);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.rece);
                item.getSprite().setTexture(a.texStick);
                break;
            case Miecz:
                item.setNazwa("Miecz");
                item.setAtak(2);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.rece);
                item.getSprite().setTexture(a.texSword);
                break;
            case Tarcza:
                item.setNazwa("Tarcza");
                item.setAtak(0);
                item.setObrona(2);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.rece);
                item.getSprite().setTexture(a.texShield);
                break;
// NOGI ========================================================================
            case Nogi:
                item.setNazwa("Gole Nogi");
                item.setAtak(0);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.nogi);
                item.getSprite().setTexture(a.texLegs);
                break;
            case LnianeSpodnie:
                item.setNazwa("Lniane Spodnie");
                item.setAtak(0);
                item.setObrona(1);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.nogi);
                item.getSprite().setTexture(a.texLinenTousers);
                break;
            case SkorzaneSpodnie:
                item.setNazwa("Skorzane Spodnie");
                item.setAtak(0);
                item.setObrona(2);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.nogi);
                item.getSprite().setTexture(a.texLeatherTousers);
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
            case SkorzaneButy:
                item.setNazwa("Skorzane Buty");
                item.setAtak(0);
                item.setObrona(0);
                item.setSzybkosc(1);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.stopy);
                item.getSprite().setTexture(a.texLeatherShoes);
                break;
        }

        item.setSize(100, 100);
        return item;
    }
}
