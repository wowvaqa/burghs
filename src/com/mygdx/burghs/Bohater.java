package com.mygdx.burghs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import enums.DostepneItemki;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.burghs.Screens.DialogScreen;
import enums.KlasyPostaci;
import java.util.ArrayList;

/**
 * Klasa Bohater odpowiada za rysowanie i zachowanie bohatera na mapie.
 *
 * @author v
 */
public class Bohater extends Actor {

    private Sprite sprite;    // wygląd
    private Texture bohaterTex;
    private Texture bohaterCheckTex;
    private Pixmap pixMap;
    private boolean teksturaZaktualizowana = false;

    private int pozX = 0;   // pozycja X na mapie
    private int pozY = 0;   // pozycja Y na mapie

    // ekwipunek bohatera
    private Item itemGlowa = null;
    private Item itemKorpus = null;
    private Item itemNogi = null;
    private Item itemPrawaReka = null;
    private Item itemLewaReka = null;
    private Item itemStopy = null;

    private ArrayList<Item> equipment;

    private final Assets a;
    private final GameStatus gs;
    private final Game g;

    // informuje cz bohater jest zaznaczony
    private boolean zaznaczony = false;

    private boolean otwartaSkrzyniaZeSkarbem = false;

    // lokacja bohatera w obiekcie klasy Mapa
    private int pozXnaMapie;
    private int pozYnaMapie;

    // informuje czy dozwolony jest ruch dla bohatera
    // Bez sprawdzania na mapie poruszał się tylko pierwszy z utworzonych 
    // bohaterów za sprawą funkcji draw która jako pierwsza została wywołana
    // u bohatera który był utworzony jako pierwszy.
    private boolean moveable = false;

    // Statystyki
    private final String imie = null;
    private int atak = 0;
    private int obrona = 0;
    private int hp = 0;
    private int actualHp = 0;
    private int szybkosc = 0;
    private int pozostaloRuchow = 0;
    private int mana = 0;
    private int actualMana = 0;
    // aktualny poziom punktów doświadczenia
    private int exp = 0;
    // punkty potrzebne do uzyskania następnego poziomu
    private int expToNextLevel = 100;
    // pocziom doświadczenia
    private int levelOfExp = 1;
    // klasa bohatera
    public KlasyPostaci klasyPostaci;

    private int przynaleznoscDoGracza;

    /**
     *
     * @param textureIcon Tekstura bohatera wyświetlająca się na mapie
     * @param textureIconZaznaczona Tekstura bohatera wyświetlana kiedy bohater
     * zostanie kliknięty
     * @param lokaczjaPoczatkowaX Lokacja X początkowa bohatera na mapie
     * @param lokaczjaPoczatkowaY Lokacja Y początkowa bohatera na mapie
     * @param a Referencja do obiektu przechowującego Assety
     * @param pozycjaXnaMapie definiuje pozycje X w obiekcie klasy Mapa
     * @param pozycjaYnaMapie definiuje pozycje Y w obiekcie klasy Mapa
     * @param gs Referencja do obiektu klasy GameStatus
     * @param g Referencja do obiektu kalsy Game
     * @param kp Klasa postaci
     */
    public Bohater(Texture textureIcon, Texture textureIconZaznaczona,
            int lokaczjaPoczatkowaX, int lokaczjaPoczatkowaY, Assets a,
            int pozycjaXnaMapie, int pozycjaYnaMapie, GameStatus gs, Game g, KlasyPostaci kp) {

        this.equipment = new ArrayList<Item>();
        this.gs = gs;
        this.pozXnaMapie = pozycjaXnaMapie;
        this.pozYnaMapie = pozycjaYnaMapie;
        this.bohaterTex = textureIcon;
        this.bohaterCheckTex = textureIconZaznaczona;
        this.a = a;
        this.g = g;
        this.pozX = lokaczjaPoczatkowaX;
        this.pozY = lokaczjaPoczatkowaY;
        this.klasyPostaci = kp;

        sprite = new Sprite(this.bohaterTex);
        this.setSize(sprite.getWidth(), sprite.getHeight());
        this.setPosition(this.pozX, this.pozY);

        this.dodajListnera();

        ItemCreator ic = new ItemCreator(this.gs);

        // Utworzenie pdst. zestawu itemków i przypisanie do ekwipunku bohatera
        itemLewaReka = ic.utworzItem(DostepneItemki.Piesci, this.a, this.g);
        itemPrawaReka = ic.utworzItem(DostepneItemki.Piesci, this.a, this.g);
        itemNogi = ic.utworzItem(DostepneItemki.Nogi, this.a, this.g);
        itemStopy = ic.utworzItem(DostepneItemki.LnianeButy, this.a, this.g);
        itemGlowa = ic.utworzItem(DostepneItemki.Glowa, this.a, this.g);
        itemKorpus = ic.utworzItem(DostepneItemki.LnianaKoszula, this.a, this.g);
    }

    // 1. Dodoaje Click Listnera do obiektu klasy Bohater
    // 2. Jeżeli obiekt zostanie kliknięty wtedy włącza przyciski odpowiedzialne
    // za ruch.
    // 3. Zmienia teksture obiketu Bohatera na zaznaczoną
    // 4. Zmiena możliwość ruchu na TRUE
    // 5. Zmienia status bohatera na zaznaczony (wykorzystywane przy 
    // wyświetlaniu statsów, oraz sprawdzaniu czy inny bohater nie został
    // już zaznaczony aby uniemożliwić zaznaczenie dwóch bohaterów na raz.
    // 6. Sprawdza czy kliknięty bohater należy do gracz którego trwa aktualnie 
    // tura.
    private void dodajListnera() {
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean bohaterZaznaczony = false;
                // Sprawdza czy któryś z bohaterów na mapie nie jest już zaznaczony                
                for (Gracz i : gs.getGracze()) {
                    for (Bohater j : i.getBohaterowie()) {
                        if (j.zaznaczony) {
                            j.setZaznaczony(false);
                            j.getSprite().setTexture(j.bohaterTex);
                            Ruch.wylaczPrzyciski();
                            //bohaterZaznaczony = true;
                        }
                    }
                }
                // Jeżeli TRUE wtedy uniemozliwia jego zaznaczenie                
                if (bohaterZaznaczony) {
                    DialogScreen dS = new DialogScreen("Blad", a.skin, "Nie moge zaznaczyc dwoch bohaterow", Assets.stage01MapScreen);
                    System.out.println("Nie mogę zaznaczyc dwóch bohaterów");
                    // Jeżeli FALSE wtedy uruchamia reszte procedur dla bohatera
                } else {
                    // Sprawdza czy bohater gracza porusza się w sowjej turze.
                    if (przynaleznoscDoGracza != gs.getTuraGracza()) {
                        System.out.println("Ten Gracz teraz nie ma swojej tury");
                    } else {
                        // Sprawdza czy bohater posiada jeszcze punkty ruchu.
                        if (pozostaloRuchow < 1) {
                            System.out.println("Bohater nie posiada już ruchu!");
                            sprite.setTexture(bohaterCheckTex);
                            zaznaczony = true;
                            gs.setCzyZaznaczonoBohatera(true);
                            // jeżeli posiada punkty ruchu.
                        } else {
                            if (otwartaSkrzyniaZeSkarbem) {
                                System.out.println("Nie mogę zaznaczyć - otwarta skrzynia ze skarbem");
                            } else {
                                moveable = true;
                                //definiujPrzyciski();
                                sprite.setTexture(bohaterCheckTex);
                                zaznaczony = true;
                                gs.setCzyZaznaczonoBohatera(true);

                                Ruch ruch = new Ruch(gs.getBohaterZaznaczony(), a, gs);
                            }
                        }
                    }
                }
            }
        });
    }

    public void aktualizujKolorTeksturyBohatera() {
        if (!bohaterTex.getTextureData().isPrepared()) {
            bohaterTex.getTextureData().prepare();
        }
        this.setPixMap(bohaterTex.getTextureData().consumePixmap());

        this.getPixMap().setColor(zwrocKolorBohatera(this.przynaleznoscDoGracza));
        this.getPixMap().fillRectangle(80, 5, 15, 15);

        this.setBohaterTex(new Texture(this.getPixMap()));
        this.sprite.setTexture(bohaterTex);

        // aktualizacja tekstury bohatera zaznaczonej
        if (!bohaterCheckTex.getTextureData().isPrepared()) {
            bohaterCheckTex.getTextureData().prepare();
        }
        this.setPixMap(bohaterCheckTex.getTextureData().consumePixmap());

        this.getPixMap().setColor(zwrocKolorBohatera(this.przynaleznoscDoGracza));
        this.getPixMap().fillRectangle(80, 5, 15, 15);

        this.setBohaterCheckTex(new Texture(this.getPixMap()));
    }

    private Color zwrocKolorBohatera(int gracz) {
        switch (gracz) {
            case 0:
                return Color.RED;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.GREEN;
        }
        return null;
    }

    /**
     * Aktualizuje status paska energi bohatera
     */
    public void aktualizujTeksture() {
        // aktualizacja tekxtury bohatera nie zaznaczonej
        if (!bohaterTex.getTextureData().isPrepared()) {
            bohaterTex.getTextureData().prepare();
        }
        this.setPixMap(bohaterTex.getTextureData().consumePixmap());

        this.getPixMap().setColor(Color.RED);
        this.getPixMap().fillRectangle(0, 0, 5, 100);
        this.getPixMap().setColor(Color.WHITE);
        this.getPixMap().fillRectangle(1, 1, 3, 100 - poziomHP());

        this.setBohaterTex(new Texture(this.getPixMap()));
        this.sprite.setTexture(bohaterTex);

        // aktualizacja tekxtury bohatera zaznaczonej
        if (!bohaterCheckTex.getTextureData().isPrepared()) {
            bohaterCheckTex.getTextureData().prepare();
        }
        this.setPixMap(bohaterCheckTex.getTextureData().consumePixmap());

        this.getPixMap().setColor(Color.RED);
        this.getPixMap().fillRectangle(0, 0, 5, 100);
        this.getPixMap().setColor(Color.WHITE);
        this.getPixMap().fillRectangle(1, 1, 3, 100 - poziomHP());

        this.setBohaterCheckTex(new Texture(this.getPixMap()));
    }

    /**
     * Zwraca proporcjonalny poziom zdrowia bohatera do jego maksymalnych
     * punktów życia.
     *
     * @return
     */
    private int poziomHP() {
        //System.out.println(this.getHp());
        //System.out.println(this.hp);

        float poziom = this.actualHp * 100 / this.hp;

        //System.out.println((int) Math.round(poziom));
        return (int) Math.round(poziom);
    }

    @Override
    public void act(float delta) {
        super.act(delta); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (!teksturaZaktualizowana) {
            aktualizujTeksture();
            this.aktualizujKolorTeksturyBohatera();
            teksturaZaktualizowana = true;
        }

        batch.draw(this.sprite, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    /**
     * *************************************************************************
     * Setters and Getters
     **************************************************************************/
     
    /**
     * Zwraca klasę postaci bohatera
     * @return 
     */
    public KlasyPostaci getKlasyPostaci() {
        return this.klasyPostaci;
    }

    /**
     * Ustala klasę postaci dla bohatera
     * @param klasyPostaci
     */
    public void setKlasyPostaci(KlasyPostaci klasyPostaci) {
        this.klasyPostaci = klasyPostaci;
    }

    /**
     *
     * @return
     */
    public Pixmap getPixMap() {
        return pixMap;
    }

    public void setPixMap(Pixmap pixMap) {
        this.pixMap = pixMap;
    }

    public Texture getBohaterTex() {
        return bohaterTex;
    }

    public void setBohaterTex(Texture bohaterTex) {
        this.bohaterTex = bohaterTex;
    }

    public Texture getBohaterCheckTex() {
        return bohaterCheckTex;
    }

    public void setBohaterCheckTex(Texture bohaterCheckTex) {
        this.bohaterCheckTex = bohaterCheckTex;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public int getActualHp() {
        return actualHp;
    }

    public void setActualHp(int actualHp) {
        this.actualHp = actualHp;
    }

    public ArrayList<Item> getEquipment() {
        return equipment;
    }

    public void setEquipment(ArrayList<Item> equipment) {
        this.equipment = equipment;
    }

    /**
     * Zwraca pozycję X obiektu Bohater na w obiekcie klasy Mapa
     *
     * @return
     */
    public int getPozXnaMapie() {
        return pozXnaMapie;
    }

    /**
     * Zwraca do którego gracza z tablicy Graczy przynależy bohater
     *
     * @return
     */
    public int getPrzynaleznoscDoGracza() {
        return przynaleznoscDoGracza;
    }

    /**
     * Ustala do którego gracza z tablicy graczy będzie należał bohater
     *
     * @param przynaleznoscDoGracza
     */
    public void setPrzynaleznoscDoGracza(int przynaleznoscDoGracza) {
        this.przynaleznoscDoGracza = przynaleznoscDoGracza;
    }

    /**
     * Zwraca ilość pozostałych ruchów które może wykonać na mapię bohater
     *
     * @return
     */
    public int getPozostaloRuchow() {
        return pozostaloRuchow;
    }

    /**
     * Ustala ilość ruchów które pozostały bohaterowi na mapie
     *
     * @param pozostaloRuchow
     */
    public void setPozostaloRuchow(int pozostaloRuchow) {
        this.pozostaloRuchow = pozostaloRuchow;
    }

    /**
     * Ustala pozycję X w obiekcie klasy Mapa
     *
     * @param pozXnaMapie
     */
    public void setPozXnaMapie(int pozXnaMapie) {
        this.pozXnaMapie = pozXnaMapie;
    }

    /**
     * Zwraca pozycję Y obiektu Bohater na w obiekcie klasy Mapa
     *
     * @return
     */
    public int getPozYnaMapie() {
        return pozYnaMapie;
    }

    /**
     * Ustala pozycję Y w obiekcie klasy Mapa
     *
     * @param pozYnaMapie
     */
    public void setPozYnaMapie(int pozYnaMapie) {
        this.pozYnaMapie = pozYnaMapie;
    }

    /**
     * Zwraca wartość ataku bohatera
     *
     * @return
     */
    public int getAtak() {
        return atak;
    }

    /**
     * Ustala wartość ataku bohatera
     *
     * @param atak
     */
    public void setAtak(int atak) {
        this.atak = atak;
    }

    /**
     * Zwraca współczynika obrony
     *
     * @return
     */
    public int getObrona() {
        return obrona;
    }

    /**
     * Ustawia współczynik obrony
     *
     * @param obrona
     */
    public void setObrona(int obrona) {
        this.obrona = obrona;
    }

    /**
     * Zwraca wartość współczynnika zdrowia bohatera
     *
     * @return
     */
    public int getHp() {
        return hp;
    }

    /**
     * Ustala wartość współczynnika zdrowia bohatera
     *
     * @param hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Zwraca wartość współczynnika maksymalnej ilośći ruchów bohatera
     *
     * @return
     */
    public int getSzybkosc() {
        return szybkosc;
    }

    /**
     * Ustala wartość współczynnika maksymalnej ilości ruchu bohatera
     *
     * @param szybkosc
     */
    public void setSzybkosc(int szybkosc) {
        this.szybkosc = szybkosc;
    }

    /**
     * Zwraca maksymalny poziom punktów many.
     * @return 
     */
    public int getMana() {
        return mana;
    }

    /**
     * Ustala maksymalny poziom punktów many
     * @param mana 
     */
    public void setMana(int mana) {
        this.mana = mana;
    }

    /**
     * Zwraca ilość punktów many
     * @return 
     */
    public int getActualMana() {
        return actualMana;
    }

    /**
     * Ustala ilość punktów many
     * @param actualMana 
     */
    public void setActualMana(int actualMana) {
        this.actualMana = actualMana;
    }    
    
    /**
     * Sprawdza czy bohater jest zaznaczony
     *
     * @return TRUE jeżeli jest zaznaczony
     */
    public boolean isZaznaczony() {
        return zaznaczony;
    }

    /**
     * Ustala czy bohater jest zaznaczony
     *
     * @param zaznaczony
     */
    public void setZaznaczony(boolean zaznaczony) {
        this.zaznaczony = zaznaczony;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getExpToNextLevel() {
        return expToNextLevel;
    }

    public void setExpToNextLevel(int expToNextLevel) {
        this.expToNextLevel = expToNextLevel;
    }

    public int getLevelOfExp() {
        return levelOfExp;
    }

    public void setLevelOfExp(int levelOfExp) {
        this.levelOfExp = levelOfExp;
    }

    public Item getItemGlowa() {
        return itemGlowa;
    }

    public void setItemGlowa(Item itemGlowa) {
        this.itemGlowa = itemGlowa;
    }

    public Item getItemKorpus() {
        return itemKorpus;
    }

    public void setItemKorpus(Item itemKorpus) {
        this.itemKorpus = itemKorpus;
    }

    public Item getItemNogi() {
        return itemNogi;
    }

    public void setItemNogi(Item itemNogi) {
        this.itemNogi = itemNogi;
    }

    public Item getItemPrawaReka() {
        return itemPrawaReka;
    }

    public void setItemPrawaReka(Item itemPrawaReka) {
        this.itemPrawaReka = itemPrawaReka;
    }

    public Item getItemLewaReka() {
        return itemLewaReka;
    }

    public void setItemLewaReka(Item itemLewaReka) {
        this.itemLewaReka = itemLewaReka;
    }

    public Item getItemStopy() {
        return itemStopy;
    }

    public void setItemStopy(Item itemStopy) {
        this.itemStopy = itemStopy;
    }

    public boolean isOtwartaSkrzyniaZeSkarbem() {
        return otwartaSkrzyniaZeSkarbem;
    }

    public void setOtwartaSkrzyniaZeSkarbem(boolean otwartaSkrzyniaZeSkarbem) {
        this.otwartaSkrzyniaZeSkarbem = otwartaSkrzyniaZeSkarbem;
    }
}
