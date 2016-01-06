package com.mygdx.burghs;

import com.badlogic.gdx.Game;
import enums.DostepneItemki;
import enums.CzesciCiala;

/**
 * Zwraca item
 *
 * @author v
 */
public class ItemCreator {

    private final GameStatus gs;
    private Game g;
    private Assets a;

    public ItemCreator(GameStatus gs) {
        this.gs = gs;
    }

    public Item utworzItem(DostepneItemki dostepneItemki, Assets a, Game g) {
        this.a = a;
        this.g = g;
        Item item = new Item(a.texFist, this.a, this.gs, this.g);

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
                item.setLevel(1);
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
                item.setLevel(1);
                item.setAtak(1);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.rece);
                item.getSprite().setTexture(a.texStick);
                break;
            case Miecz:
                item.setNazwa("Miecz");
                item.setLevel(2);
                item.setAtak(2);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.rece);
                item.getSprite().setTexture(a.texSword);
                break;
            case Tarcza:
                item.setNazwa("Tarcza");
                item.setLevel(1);
                item.setAtak(0);
                item.setObrona(1);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.rece);
                item.getSprite().setTexture(a.texShield);
                break;
            case Luk:
                item.setNazwa("Luk");
                item.setLevel(1);
                item.setAtak(0);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setZasieg(1);
                item.setCzescCiala(CzesciCiala.rece);
                item.getSprite().setTexture(a.texBow);
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
                item.setLevel(1);
                item.setAtak(0);
                item.setObrona(1);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.nogi);
                item.getSprite().setTexture(a.texLinenTousers);
                break;
            case SkorzaneSpodnie:
                item.setNazwa("Skorzane Spodnie");
                item.setLevel(2);
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
                item.setLevel(1);
                item.setAtak(0);
                item.setObrona(0);
                item.setSzybkosc(1);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.stopy);
                item.getSprite().setTexture(a.texLeatherShoes);
                break;
// INNE ========================================================================     
            case Gold:
                item.setNazwa("Zloto");
                item.setLevel(1);
                item.setAtak(0);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.gold);
                item.getSprite().setTexture(a.texGold);
                item.setGold(100);
                break;
        }

        item.setSize(100, 100);
        return item;
    }
}
