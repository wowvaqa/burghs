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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.burghs.Screens.DialogScreen;
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
    // aktualny poziom punktów doświadczenia
    private int exp = 0;
    // punkty potrzebne do uzyskania następnego poziomu
    private int expToNextLevel = 100;
    // pocziom doświadczenia
    private int levelOfExp = 1;

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
     */
    public Bohater(Texture textureIcon, Texture textureIconZaznaczona,
            int lokaczjaPoczatkowaX, int lokaczjaPoczatkowaY, Assets a,
            int pozycjaXnaMapie, int pozycjaYnaMapie, GameStatus gs, Game g) {

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
                            bohaterZaznaczony = true;
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

    // Ustawia widzialnosć przycisków dodanych do obiektu klasy Stage w obiekcie
    // klasy MapScreen na true. 
    // Definiuje położenie przycisków względem bohatera który aktualnie
    // jest zaznaczony
//    private void definiujPrzyciski() {
//
//        // Przycisk Cancel
//        a.btnCancel.setPosition(this.getX() + 50 - (a.btnCancel.getWidth() / 2),
//                this.getY() + 50 - (a.btnCancel.getHeight() / 2));
//        a.btnCancel.setVisible(true);
//
//        // Sprawdzenie czy przycisk nie przekroczy górnej części mapy
//        if (this.pozYnaMapie < gs.mapa.getIloscPolY() - 1) {
//            // Jeżeli w lokacji na północ jest inny bohater wtedy wyświetla się przycisk ataku
//            if (gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie + 1].getBohater() != null
//                    || gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie + 1].getMob() != null
//                    || (gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie + 1].getCastle() != null
//                    && gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie + 1].getCastle().getPrzynaleznoscDoGracza() != gs.getTuraGracza()
//                    && gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie + 1].getCastle().getActualHp() > 0)) {
//                //|| this.sprawdzWarunekAtakuNaZamek() != true){
//                a.btnAtcNorth.setPosition(this.getX() + 50, this.getY() + 150);
//                a.btnAtcNorth.setVisible(true);
//                // Jeżeli nie wyświetla się przycisk ruchu
//            } else {
//                a.btnNorth.setPosition(this.getX() + 50, this.getY() + 150);
//                a.btnNorth.setVisible(true);
//            }
//        }
//
//        // Sprawdzenie czy przycisk SOUTH mieści się w dolnej granicy 
//        // tablicy Mapa
//        if (this.pozYnaMapie > 0) {
//            // Wyświetlenie odpowiedniego przycisku w zależności od możliwości 
//            // ataku lub ruchu.
//            if (gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie - 1].getBohater() != null
//                    || gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie - 1].getMob() != null
//                    || (gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie - 1].getCastle() != null
//                    && gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie - 1].getCastle().getPrzynaleznoscDoGracza() != gs.getTuraGracza()
//                    && gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie - 1].getCastle().getActualHp() > 0)) {
//                a.btnAtcSouth.setPosition(this.getX() + 50, this.getY() - 50);
//                a.btnAtcSouth.setVisible(true);
//            } else {
//                a.btnSouth.setPosition(this.getX() + 50, this.getY() - 50);
//                a.btnSouth.setVisible(true);
//            }
//        }
//
//        if (this.pozXnaMapie < gs.mapa.getIloscPolX() - 1) {
//            if (gs.getMapa().pola[this.pozXnaMapie + 1][pozYnaMapie].getBohater() != null
//                    || gs.getMapa().pola[this.pozXnaMapie + 1][pozYnaMapie].getMob() != null
//                    || (gs.getMapa().pola[this.pozXnaMapie + 1][pozYnaMapie].getCastle() != null
//                    && gs.getMapa().pola[this.pozXnaMapie + 1][pozYnaMapie].getCastle().getPrzynaleznoscDoGracza() != gs.getTuraGracza()
//                    && gs.getMapa().pola[this.pozXnaMapie + 1][pozYnaMapie].getCastle().getActualHp() > 0)) {
//                a.btnAtcEast.setPosition(this.getX() + 150, this.getY() + 50);
//                a.btnAtcEast.setVisible(true);
//            } else {
//                a.btnEast.setPosition(this.getX() + 150, this.getY() + 50);
//                a.btnEast.setVisible(true);
//            }
//        }
//
//        if (this.pozXnaMapie > 0) {
//            if (gs.getMapa().pola[this.pozXnaMapie - 1][pozYnaMapie].getBohater() != null
//                    || gs.getMapa().pola[this.pozXnaMapie - 1][pozYnaMapie].getMob() != null
//                    || (gs.getMapa().pola[this.pozXnaMapie - 1][pozYnaMapie].getCastle() != null
//                    && gs.getMapa().pola[this.pozXnaMapie - 1][pozYnaMapie].getCastle().getPrzynaleznoscDoGracza() != gs.getTuraGracza()
//                    && gs.getMapa().pola[this.pozXnaMapie - 1][pozYnaMapie].getCastle().getActualHp() > 0)) {
//                a.btnAtcWest.setPosition(this.getX() - 50, this.getY() + 50);
//                a.btnAtcWest.setVisible(true);
//            } else {
//                a.btnWest.setPosition(this.getX() - 50, this.getY() + 50);
//                a.btnWest.setVisible(true);
//            }
//        }
//
//        if (this.pozYnaMapie < gs.mapa.getIloscPolY() - 1) {
//            if (this.pozXnaMapie < gs.mapa.getIloscPolX() - 1) {
//                if (gs.getMapa().pola[this.pozXnaMapie + 1][pozYnaMapie + 1].getBohater() != null
//                        || gs.getMapa().pola[this.pozXnaMapie + 1][pozYnaMapie + 1].getMob() != null
//                        || (gs.getMapa().pola[this.pozXnaMapie + 1][pozYnaMapie + 1].getCastle() != null
//                        && gs.getMapa().pola[this.pozXnaMapie + 1][pozYnaMapie + 1].getCastle().getPrzynaleznoscDoGracza() != gs.getTuraGracza()
//                        && gs.getMapa().pola[this.pozXnaMapie + 1][pozYnaMapie + 1].getCastle().getActualHp() > 0)) {
//                    a.btnAtcNorthEast.setPosition(this.getX() + 150, this.getY() + 150);
//                    a.btnAtcNorthEast.setVisible(true);
//                } else {
//                    a.btnNorthEast.setPosition(this.getX() + 150, this.getY() + 150);
//                    a.btnNorthEast.setVisible(true);
//                }
//            }
//        }
//
//        if (this.pozYnaMapie < gs.mapa.getIloscPolY() - 1) {
//            if (this.pozXnaMapie > 0) {
//                if (gs.getMapa().pola[this.pozXnaMapie - 1][pozYnaMapie + 1].getBohater() != null
//                        || gs.getMapa().pola[this.pozXnaMapie - 1][pozYnaMapie + 1].getMob() != null
//                        || (gs.getMapa().pola[this.pozXnaMapie - 1][pozYnaMapie + 1].getCastle() != null
//                        && gs.getMapa().pola[this.pozXnaMapie - 1][pozYnaMapie + 1].getCastle().getPrzynaleznoscDoGracza() != gs.getTuraGracza()
//                        && gs.getMapa().pola[this.pozXnaMapie - 1][pozYnaMapie + 1].getCastle().getActualHp() > 0)) {
//                    a.btnAtcNorthWest.setPosition(this.getX() - 50, this.getY() + 150);
//                    a.btnAtcNorthWest.setVisible(true);
//                } else {
//                    a.btnNorthWest.setPosition(this.getX() - 50, this.getY() + 150);
//                    a.btnNorthWest.setVisible(true);
//                }
//            }
//        }
//
//        if (this.pozYnaMapie > 0) {
//            if (this.pozXnaMapie < gs.mapa.getIloscPolX() - 1) {
//                if (gs.getMapa().pola[this.pozXnaMapie + 1][pozYnaMapie - 1].getBohater() != null
//                        || gs.getMapa().pola[this.pozXnaMapie + 1][pozYnaMapie - 1].getMob() != null
//                        || (gs.getMapa().pola[this.pozXnaMapie + 1][pozYnaMapie - 1].getCastle() != null
//                        && gs.getMapa().pola[this.pozXnaMapie + 1][pozYnaMapie - 1].getCastle().getPrzynaleznoscDoGracza() != gs.getTuraGracza()
//                        && gs.getMapa().pola[this.pozXnaMapie + 1][pozYnaMapie - 1].getCastle().getActualHp() > 0)) {
//                    a.btnAtcSouthEast.setPosition(this.getX() + 150, this.getY() - 50);
//                    a.btnAtcSouthEast.setVisible(true);
//                } else {
//                    a.btnSouthEast.setPosition(this.getX() + 150, this.getY() - 50);
//                    a.btnSouthEast.setVisible(true);
//                }
//            }
//        }
//
//        if (this.pozYnaMapie > 0) {
//            if (this.pozXnaMapie > 0) {
//                if (gs.getMapa().pola[this.pozXnaMapie - 1][pozYnaMapie - 1].getBohater() != null
//                        || gs.getMapa().pola[this.pozXnaMapie - 1][pozYnaMapie - 1].getMob() != null
//                        || (gs.getMapa().pola[this.pozXnaMapie - 1][pozYnaMapie - 1].getCastle() != null
//                        && gs.getMapa().pola[this.pozXnaMapie - 1][pozYnaMapie - 1].getCastle().getPrzynaleznoscDoGracza() != gs.getTuraGracza()
//                        && gs.getMapa().pola[this.pozXnaMapie - 1][pozYnaMapie - 1].getCastle().getActualHp() > 0)) {
//                    a.btnAtcSouthWest.setPosition(this.getX() - 50, this.getY() - 50);
//                    a.btnAtcSouthWest.setVisible(true);
//                } else {
//                    a.btnSouthWest.setPosition(this.getX() - 50, this.getY() - 50);
//                    a.btnSouthWest.setVisible(true);
//                }
//            }
//        }
//    }

//    private void sprawdzPrzyciskAtcNorth() {
//        if (moveable) {
//            if (a.btnAtcNorth.isKlikniety()) {
//                //Sprawdzenie czym jest obiekt który ma zostać zaatakowany
//                if (gs.mapa.pola[this.pozXnaMapie][this.pozYnaMapie + 1].getBohater() != null) {
//                    a.animujLblDmg(a.btnAtcNorth.getX(), a.btnAtcNorth.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie][this.pozYnaMapie + 1].getBohater());
//                } else if (gs.mapa.pola[this.pozXnaMapie][this.pozYnaMapie + 1].getCastle() != null) {
//                    a.animujLblDmg(a.btnAtcNorth.getX(), a.btnAtcNorth.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie][this.pozYnaMapie + 1].getCastle());
//                } else if (gs.mapa.pola[this.pozXnaMapie][this.pozYnaMapie + 1].getMob() != null) {
//                    a.animujLblDmg(a.btnAtcNorth.getX(), a.btnAtcNorth.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie][this.pozYnaMapie + 1].getMob());
//                }
//                usunMartwychBohaterow();
//                usunMartweMoby();
//                this.sprite.setTexture(bohaterTex);
//                wylaczPrzyciski();
//                zaznaczony = false;
//            }
//        }
//    }

//    private void sprawdzPrzyciskAtcSouth() {
//        if (moveable) {
//            if (a.btnAtcSouth.isKlikniety()) {
//                if (gs.mapa.pola[this.pozXnaMapie][this.pozYnaMapie - 1].getBohater() != null) {
//                    a.animujLblDmg(a.btnAtcSouth.getX(), a.btnAtcSouth.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie][this.pozYnaMapie - 1].getBohater());
//                } else if (gs.mapa.pola[this.pozXnaMapie][this.pozYnaMapie - 1].getCastle() != null) {
//                    a.animujLblDmg(a.btnAtcSouth.getX(), a.btnAtcSouth.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie][this.pozYnaMapie - 1].getCastle());
//                } else if (gs.mapa.pola[this.pozXnaMapie][this.pozYnaMapie - 1].getMob() != null) {
//                    a.animujLblDmg(a.btnAtcSouth.getX(), a.btnAtcSouth.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie][this.pozYnaMapie - 1].getMob());
//                }
//                usunMartweMoby();
//                usunMartwychBohaterow();
//                this.sprite.setTexture(bohaterTex);
//                wylaczPrzyciski();
//                zaznaczony = false;
//            }
//        }
//    }

//    private void sprawdzPrzyciskAtcEast() {
//        if (moveable) {
//            if (a.btnAtcEast.isKlikniety()) {
//                if (gs.mapa.pola[this.pozXnaMapie + 1][this.pozYnaMapie].getBohater() != null) {
//                    a.animujLblDmg(a.btnAtcEast.getX(), a.btnAtcEast.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie + 1][this.pozYnaMapie].getBohater());
//                } else if (gs.mapa.pola[this.pozXnaMapie + 1][this.pozYnaMapie].getCastle() != null) {
//                    a.animujLblDmg(a.btnAtcEast.getX(), a.btnAtcEast.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie + 1][this.pozYnaMapie].getCastle());
//                } else if (gs.mapa.pola[this.pozXnaMapie + 1][this.pozYnaMapie].getMob() != null) {
//                    a.animujLblDmg(a.btnAtcEast.getX(), a.btnAtcEast.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie + 1][this.pozYnaMapie].getMob());
//                }
//                usunMartwychBohaterow();
//                usunMartweMoby();
//                this.sprite.setTexture(bohaterTex);
//                wylaczPrzyciski();
//                zaznaczony = false;
//            }
//        }
//    }

//    private void sprawdzPrzyciskAtcWest() {
//        if (moveable) {
//            if (a.btnAtcWest.isKlikniety()) {
//                if (gs.mapa.pola[this.pozXnaMapie - 1][this.pozYnaMapie].getBohater() != null) {
//                    a.animujLblDmg(a.btnAtcWest.getX(), a.btnAtcWest.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie - 1][this.pozYnaMapie].getBohater());
//                } else if (gs.mapa.pola[this.pozXnaMapie - 1][this.pozYnaMapie].getCastle() != null) {
//                    a.animujLblDmg(a.btnAtcWest.getX(), a.btnAtcWest.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie - 1][this.pozYnaMapie].getCastle());
//                } else if (gs.mapa.pola[this.pozXnaMapie - 1][this.pozYnaMapie].getMob() != null) {
//                    a.animujLblDmg(a.btnAtcWest.getX(), a.btnAtcWest.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie - 1][this.pozYnaMapie].getMob());
//                }
//                usunMartweMoby();
//                usunMartwychBohaterow();
//                this.sprite.setTexture(bohaterTex);
//                wylaczPrzyciski();
//                zaznaczony = false;
//            }
//        }
//    }

//    private void sprawdzPrzyciskAtcNorthEast() {
//        if (moveable) {
//            if (a.btnAtcNorthEast.isKlikniety()) {
//                if (gs.mapa.pola[this.pozXnaMapie + 1][this.pozYnaMapie + 1].getBohater() != null) {
//                    a.animujLblDmg(a.btnAtcNorthEast.getX(), a.btnAtcNorthEast.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie + 1][this.pozYnaMapie + 1].getBohater());
//                } else if (gs.mapa.pola[this.pozXnaMapie + 1][this.pozYnaMapie + 1].getCastle() != null) {
//                    a.animujLblDmg(a.btnAtcNorthEast.getX(), a.btnAtcNorthEast.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie + 1][this.pozYnaMapie + 1].getCastle());
//                } else if (gs.mapa.pola[this.pozXnaMapie + 1][this.pozYnaMapie + 1].getMob() != null) {
//                    a.animujLblDmg(a.btnAtcNorthEast.getX(), a.btnAtcNorthEast.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie + 1][this.pozYnaMapie + 1].getMob());
//                }
//                usunMartweMoby();
//                usunMartwychBohaterow();
//                this.sprite.setTexture(bohaterTex);
//                wylaczPrzyciski();
//                zaznaczony = false;
//            }
//        }
//    }

//    private void sprawdzPrzyciskAtcNorthWest() {
//        if (moveable) {
//            if (a.btnAtcNorthWest.isKlikniety()) {
//                if (gs.mapa.pola[this.pozXnaMapie - 1][this.pozYnaMapie + 1].getBohater() != null) {
//                    a.animujLblDmg(a.btnAtcNorthWest.getX(), a.btnAtcNorthWest.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie - 1][this.pozYnaMapie + 1].getBohater());
//                } else if (gs.mapa.pola[this.pozXnaMapie - 1][this.pozYnaMapie + 1].getCastle() != null) {
//                    a.animujLblDmg(a.btnAtcNorthWest.getX(), a.btnAtcNorthWest.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie - 1][this.pozYnaMapie + 1].getCastle());
//                } else if (gs.mapa.pola[this.pozXnaMapie - 1][this.pozYnaMapie + 1].getMob() != null) {
//                    a.animujLblDmg(a.btnAtcNorthWest.getX(), a.btnAtcNorthWest.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie - 1][this.pozYnaMapie + 1].getMob());
//                }
//                usunMartweMoby();
//                usunMartwychBohaterow();
//                this.sprite.setTexture(bohaterTex);
//                wylaczPrzyciski();
//                zaznaczony = false;
//            }
//        }
//    }

//    private void sprawdzPrzyciskAtcSouthEast() {
//        if (moveable) {
//            if (a.btnAtcSouthEast.isKlikniety()) {
//                if (gs.mapa.pola[this.pozXnaMapie + 1][this.pozYnaMapie - 1].getBohater() != null) {
//                    a.animujLblDmg(a.btnAtcSouthEast.getX(), a.btnAtcSouthEast.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie + 1][this.pozYnaMapie - 1].getBohater());
//                } else if (gs.mapa.pola[this.pozXnaMapie + 1][this.pozYnaMapie - 1].getCastle() != null) {
//                    a.animujLblDmg(a.btnAtcSouthEast.getX(), a.btnAtcSouthEast.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie + 1][this.pozYnaMapie - 1].getCastle());
//                } else if (gs.mapa.pola[this.pozXnaMapie + 1][this.pozYnaMapie - 1].getMob() != null) {
//                    a.animujLblDmg(a.btnAtcSouthEast.getX(), a.btnAtcSouthEast.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie + 1][this.pozYnaMapie - 1].getMob());
//                }
//                usunMartweMoby();
//                usunMartwychBohaterow();
//                this.sprite.setTexture(bohaterTex);
//                wylaczPrzyciski();
//                zaznaczony = false;
//            }
//        }
//    }

//    private void sprawdzPrzyciskAtcSouthWest() {
//        if (moveable) {
//            if (a.btnAtcSouthWest.isKlikniety()) {
//                if (gs.mapa.pola[this.pozXnaMapie - 1][this.pozYnaMapie - 1].getBohater() != null) {
//                    a.animujLblDmg(a.btnAtcSouthWest.getX(), a.btnAtcSouthWest.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie - 1][this.pozYnaMapie - 1].getBohater());
//                } else if (gs.mapa.pola[this.pozXnaMapie - 1][this.pozYnaMapie - 1].getCastle() != null) {
//                    a.animujLblDmg(a.btnAtcSouthWest.getX(), a.btnAtcSouthWest.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie - 1][this.pozYnaMapie - 1].getCastle());
//                } else if (gs.mapa.pola[this.pozXnaMapie - 1][this.pozYnaMapie - 1].getMob() != null) {
//                    a.animujLblDmg(a.btnAtcSouthWest.getX(), a.btnAtcSouthWest.getY(),
//                            this, gs.mapa.pola[this.pozXnaMapie - 1][this.pozYnaMapie - 1].getMob());
//                }
//                usunMartweMoby();
//                usunMartwychBohaterow();
//                this.sprite.setTexture(bohaterTex);
//                wylaczPrzyciski();
//                zaznaczony = false;
//            }
//        }
//    }

    // 1. Sprawdza czy dozwolony jest ruch dla bohatera
    // 2. Sprawdza czy przycisk ruchu został kliknięty jeżeli TRUE wtedy 
    // przesuwa obiekt klasy bohatera na N/S/E/W    
    // 3. Uruchamia funkcje wyłączania przycisków
    // 4. Zmienia teksturę Bohatera na nie zaznaczoną    
    // 5. Zmienia status bohatera na niezaznaczony
    // 5. Czyści w pole na mapie z referencji bohatera i referencje zapisuje
    // w polu północnym mapy.       
    // 6. Zmniejsza ilość ruchów dla bohatera.
//    private void sprawdzPrzyciskNorth() {
//        if (moveable) {
//            if (a.btnNorth.isKlikniety()) {
//                this.addAction(Actions.moveBy(0, 100, 0.25f));
//                gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie].setBohater(null);
//                gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie + 1].setBohater(this);
//                pozYnaMapie += 1;
//                this.sprite.setTexture(bohaterTex);
//                wylaczPrzyciski();
//                zaznaczony = false;
//                this.pozostaloRuchow -= 1;
//            }
//        }
//    }

    // 1. Opis analogiczny do przycisku NORTH
//    private void sprawdzPrzyciskSouth() {
//        if (moveable) {
//            if (a.btnSouth.isKlikniety()) {
//                this.addAction(Actions.moveBy(0, -100, 0.25f));
//                gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie].setBohater(null);
//                gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie - 1].setBohater(this);
//                pozYnaMapie -= 1;
//                this.sprite.setTexture(bohaterTex);
//                wylaczPrzyciski();
//                zaznaczony = false;
//                this.pozostaloRuchow -= 1;
//            }
//        }
//    }

    // 1. Opis analogiczny do przycisku NORTH
//    private void sprawdzPrzyciskEast() {
//        if (moveable) {
//            if (a.btnEast.isKlikniety()) {
//                this.addAction(Actions.moveBy(100, 0, 0.25f));
//                gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie].setBohater(null);
//                gs.getMapa().pola[this.pozXnaMapie + 1][pozYnaMapie].setBohater(this);
//                pozXnaMapie += 1;
//                this.sprite.setTexture(bohaterTex);
//                wylaczPrzyciski();
//                zaznaczony = false;
//                this.pozostaloRuchow -= 1;
//            }
//        }
//    }

    // 1. Opis analogiczny do przycisku NORTH
//    private void sprawdzPrzyciskWest() {
//        if (moveable) {
//            if (a.btnWest.isKlikniety()) {
//                this.addAction(Actions.moveBy(-100, 0, 0.25f));
//                gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie].setBohater(null);
//                gs.getMapa().pola[this.pozXnaMapie - 1][pozYnaMapie].setBohater(this);
//                pozXnaMapie -= 1;
//                this.sprite.setTexture(bohaterTex);
//                wylaczPrzyciski();
//                zaznaczony = false;
//                this.pozostaloRuchow -= 1;
//            }
//        }
//    }

    // 1. Opis analogiczny do przycisku NORTH
//    private void sprawdzPrzyciskNorthEast() {
//        if (moveable) {
//            if (a.btnNorthEast.isKlikniety()) {
//                this.addAction(Actions.moveBy(100, 100, 0.25f));
//                gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie].setBohater(null);
//                gs.getMapa().pola[this.pozXnaMapie + 1][pozYnaMapie + 1].setBohater(this);
//                pozXnaMapie += 1;
//                pozYnaMapie += 1;
//                this.sprite.setTexture(bohaterTex);
//                wylaczPrzyciski();
//                zaznaczony = false;
//                this.pozostaloRuchow -= 1;
//            }
//        }
//    }

    // 1. Opis analogiczny do przycisku NORTH
//    private void sprawdzPrzyciskNorthWest() {
//        if (moveable) {
//            if (a.btnNorthWest.isKlikniety()) {
//                this.addAction(Actions.moveBy(-100, 100, 0.25f));
//                gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie].setBohater(null);
//                gs.getMapa().pola[this.pozXnaMapie - 1][pozYnaMapie + 1].setBohater(this);
//                pozXnaMapie -= 1;
//                pozYnaMapie += 1;
//                this.sprite.setTexture(bohaterTex);
//                wylaczPrzyciski();
//                zaznaczony = false;
//                this.pozostaloRuchow -= 1;
//            }
//        }
//    }

    // 1. Opis analogiczny do przycisku NORTH
//    private void sprawdzPrzyciskSouthEast() {
//        if (moveable) {
//            if (a.btnSouthEast.isKlikniety()) {
//                this.addAction(Actions.moveBy(100, -100, 0.25f));
//                gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie].setBohater(null);
//                gs.getMapa().pola[this.pozXnaMapie + 1][pozYnaMapie - 1].setBohater(this);
//                pozXnaMapie += 1;
//                pozYnaMapie -= 1;
//                this.sprite.setTexture(bohaterTex);
//                wylaczPrzyciski();
//                zaznaczony = false;
//                this.pozostaloRuchow -= 1;
//            }
//        }
//    }

    // 1. Opis analogiczny do przycisku NORTH
//    private void sprawdzPrzyciskSouthWest() {
//        if (moveable) {
//            if (a.btnSouthWest.isKlikniety()) {
//                this.addAction(Actions.moveBy(-100, -100, 0.25f));
//                gs.getMapa().pola[this.pozXnaMapie][pozYnaMapie].setBohater(null);
//                gs.getMapa().pola[this.pozXnaMapie - 1][pozYnaMapie - 1].setBohater(this);
//                pozXnaMapie -= 1;
//                pozYnaMapie -= 1;
//                this.sprite.setTexture(bohaterTex);
//                wylaczPrzyciski();
//                zaznaczony = false;
//                this.pozostaloRuchow -= 1;
//            }
//        }
//    }

//    private void sprawdzPrzyciskCancel() {
//        if (moveable) {
//            if (a.btnCancel.isKlikniety()) {
//                this.sprite.setTexture(bohaterTex);
//                wylaczPrzyciski();
//                zaznaczony = false;
//            }
//        }
//    }

    /**
     * Funkcja usuwa martywch bohaterów z pól mapy
     */
//    private void usunMartwychBohaterow() {
//        for (int i = 0; i < gs.getMapa().getIloscPolX(); i++) {
//            for (int j = 0; j < gs.getMapa().getIloscPolY(); j++) {
//                if (gs.getMapa().pola[i][j].getBohater() != null) {
//                    if (gs.getMapa().pola[i][j].getBohater().getActualHp() <= 0) {
//                        gs.getMapa().pola[i][j].setBohater(null);
//                    }
//                }
//            }
//        }
//    }

//    private void usunMartweMoby() {
//        for (int i = 0; i < gs.getMapa().getIloscPolX(); i++) {
//            for (int j = 0; j < gs.getMapa().getIloscPolY(); j++) {
//                if (gs.getMapa().pola[i][j].getMob() != null) {
//                    if (gs.getMapa().pola[i][j].getMob().getAktualneHp() <= 0) {
//                        gs.getMapa().pola[i][j].setMob(null);
//                    }
//                }
//            }
//        }
//
//        for (int j = 0; j < Assets.stage01MapScreen.getActors().size; j++) {
//            if (Assets.stage01MapScreen.getActors().get(j).getClass() == Mob.class) {
//                Mob tmpMob = (Mob) Assets.stage01MapScreen.getActors().get(j);
//                if (tmpMob.getAktualneHp() < 1) {
//                    Assets.stage01MapScreen.getActors().removeIndex(j);
//                }
//            }
//        }
//    }

    /**
     * Wyłącza wszystkie aktywne przyciski oraz uniemożliwia ruch bohatera
     */
//    public void wylaczPrzyciski() {
//        a.btnNorth.setVisible(false);
//        a.btnNorth.setKlikniety(false);
//        a.btnSouth.setVisible(false);
//        a.btnSouth.setKlikniety(false);
//        a.btnEast.setVisible(false);
//        a.btnEast.setKlikniety(false);
//        a.btnWest.setVisible(false);
//        a.btnWest.setKlikniety(false);
//        a.btnNorthEast.setVisible(false);
//        a.btnNorthEast.setKlikniety(false);
//        a.btnNorthWest.setVisible(false);
//        a.btnNorthWest.setKlikniety(false);
//        a.btnSouthEast.setVisible(false);
//        a.btnSouthEast.setKlikniety(false);
//        a.btnSouthWest.setVisible(false);
//        a.btnSouthWest.setKlikniety(false);
//
//        a.btnAtcNorth.setVisible(false);
//        a.btnAtcNorth.setKlikniety(false);
//        a.btnAtcSouth.setVisible(false);
//        a.btnAtcSouth.setKlikniety(false);
//        a.btnAtcEast.setVisible(false);
//        a.btnAtcEast.setKlikniety(false);
//        a.btnAtcWest.setVisible(false);
//        a.btnAtcWest.setKlikniety(false);
//        a.btnAtcNorthEast.setVisible(false);
//        a.btnAtcNorthEast.setKlikniety(false);
//        a.btnAtcNorthWest.setVisible(false);
//        a.btnAtcNorthWest.setKlikniety(false);
//        a.btnAtcSouthEast.setVisible(false);
//        a.btnAtcSouthEast.setKlikniety(false);
//        a.btnAtcSouthWest.setVisible(false);
//        a.btnAtcSouthWest.setKlikniety(false);
//
//        a.btnCancel.setVisible(false);
//        a.btnCancel.setKlikniety(false);
//
//        this.moveable = false;
//
//        gs.setCzyZaznaczonoBohatera(false);
//        this.checkTresureBox();
//        checkCastle();
//    }

    /**
     * Funkcja sprawdza czy bohater nie wlazł na zamek
     */
    private void checkCastle() {
        if (gs.getMapa().getPola()[this.pozXnaMapie][this.pozYnaMapie].getCastle() != null) {
            System.out.println(gs.getTuraGracza() + " Nadepnięto na zamek :-)");
            gs.getMapa().getPola()[this.pozXnaMapie][this.pozYnaMapie].getCastle().setPrzynaleznoscDoGracza(this.getPrzynaleznoscDoGracza());
            gs.getMapa().getPola()[this.pozXnaMapie][this.pozYnaMapie].getCastle().aktualizujIkoneZamku();

            System.out.println("Zamek zmienił włąsciciela");
        }
    }

    /**
     * Funkcja sprawdza czy bohater nie wlazł na skrzynkę ze skarbem
     */
    private void checkTresureBox() {
        if (gs.getMapa().getPola()[this.pozXnaMapie][this.pozYnaMapie].getTresureBox() != null) {
            System.out.println("Nadepnięto na skrzynkę ze skarbem");
            a.pokazInfoWindow(gs.getMapa().getPola()[this.pozXnaMapie][this.pozYnaMapie].getTresureBox(), this, this.gs);
            otwartaSkrzyniaZeSkarbem = true;
        }
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

//        // Sprawdza co klatkę czy którykolwiek z przycisków ruchu został kliknięty        
//        this.sprawdzPrzyciskNorth();
//        this.sprawdzPrzyciskSouth();
//        this.sprawdzPrzyciskEast();
//        this.sprawdzPrzyciskWest();
//        this.sprawdzPrzyciskNorthEast();
//        this.sprawdzPrzyciskNorthWest();
//        this.sprawdzPrzyciskSouthEast();
//        this.sprawdzPrzyciskSouthWest();
//
//        this.sprawdzPrzyciskAtcNorth();
//        this.sprawdzPrzyciskAtcSouth();
//        this.sprawdzPrzyciskAtcEast();
//        this.sprawdzPrzyciskAtcWest();
//        this.sprawdzPrzyciskAtcNorthEast();
//        this.sprawdzPrzyciskAtcNorthWest();
//        this.sprawdzPrzyciskAtcSouthEast();
//        this.sprawdzPrzyciskAtcSouthWest();
//
//        this.sprawdzPrzyciskCancel();
    }

// SETTERS AND GETTERS
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
