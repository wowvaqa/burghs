package com.mygdx.burghs;

/**
 * Zwraca item
 *
 * @author v
 */
public class ItemCreator {

    public static Item utworzItem(DostepneItemki dostepneItemki) {
        Item item = new Item();

        switch (dostepneItemki) {
            case LnianaCzapka:
                item.setNazwa("Lniana Czapka");
                item.setAtak(0);
                item.setObrona(0);
                item.setSzybkosc(0);
                item.setHp(0);
                item.setCzescCiala(CzesciCiala.praweRamie);
                break;
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
