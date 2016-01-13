package com.mygdx.burghs;

import com.badlogic.gdx.Game;
import enums.DostepneItemki;
import enums.CzesciCiala;
import enums.TypItemu;
import java.util.ArrayList;

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
                item.setTypItemu(TypItemu.Pancerz);
                break;
            case LnianaCzapka:
                item.setNazwa("Lniana Czapka");
                item.setAtak(0);
                item.setObrona(1);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.glowa);
                item.getSprite().setTexture(a.texLinenCap);
                item.setTypItemu(TypItemu.Pancerz);
                break;
            case SkorzanaCzapka:
                item.setNazwa("Skorzana czapka");
                item.setAtak(0);
                item.setObrona(2);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.glowa);
                item.getSprite().setTexture(a.texLeatherCap);
                item.setTypItemu(TypItemu.Pancerz);
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
                item.setTypItemu(TypItemu.Pancerz);
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
                item.setTypItemu(TypItemu.Bron);
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
                item.setTypItemu(TypItemu.Bron);
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
                item.setTypItemu(TypItemu.Bron);
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
                item.setTypItemu(TypItemu.Pancerz);
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
                item.setTypItemu(TypItemu.Bron);
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
                item.setTypItemu(TypItemu.Pancerz);
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
                item.setTypItemu(TypItemu.Pancerz);
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
                item.setTypItemu(TypItemu.Pancerz);
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
                item.setTypItemu(TypItemu.Pancerz);
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
                item.setTypItemu(TypItemu.Pancerz);
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
                item.setTypItemu(TypItemu.Other);
                item.setGold(100);
                break;
// MIKSTURY ====================================================================
            case PotionZdrowie:
                item.setNazwa("Potion + 5");
                item.setLevel(0);
                item.setAtak(0);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.other);
                item.getSprite().setTexture(a.texHelthPotion);
                item.setTypItemu(TypItemu.Mikstura);                
                item.setOpis("Mikstura leczaca +5 HP.");
                item.setItemNazwa(DostepneItemki.PotionZdrowie);
                item.dzialania = new ArrayList<DzialanieItema>();
                item.dzialania.add(new DzialanieItema());
                break;
                
                case PotionSzybkosc:
                item.setNazwa("Szybkosc + 2");
                item.setLevel(0);
                item.setAtak(0);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.other);
                item.getSprite().setTexture(a.texSpeedPotion);
                item.setTypItemu(TypItemu.Mikstura);                
                item.setOpis("Mikstura odnawia 2 punkty akcji.");
                item.setItemNazwa(DostepneItemki.PotionSzybkosc);
                item.dzialania = new ArrayList<DzialanieItema>();
                item.dzialania.add(new DzialanieItema());
                break;
        }

        item.setSize(100, 100);
        return item;
    }
}
