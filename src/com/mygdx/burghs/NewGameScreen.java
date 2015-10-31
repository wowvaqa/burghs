//TODO:
// Stworzyć 4 gotowe portrety postaci
// Stworzyć możliwość wyboru
package com.mygdx.burghs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class NewGameScreen implements Screen {

    private final BitmapFont font;

    private final Game g;
    private final Assets a;
    private final GameStatus gs;

    private final Stage stage01 = new Stage();
    private final SpriteBatch batch;

    // wysokość i szerokość ekranu
    private final int sH, sW;

    private int tmpIloscGraczy = 2;
    private int tmpIloscBohaterow = 1;

    // zmienne przechowują informacje który portret jest wyświetlany
    private int wyborPortretGracz01, wyborPortretGracz02, wyborPortretGracz03, wyborPortretGracz04;

    // tekstury portretów graczy
    private Texture portretGracza01, portretGracza02, portretGracza03, portretGracza04;
    private Texture portretGracza01zaz, portretGracza02zaz, portretGracza03zaz, portretGracza04zaz;

    // przyciski
    private ButtonActor button01, button02, button03, button04;

    // przyciski left/right przy wybieraniu wyglądu bohatera
    private ButtonActor button05, button06, button07, button08;

    private ButtonActor buttonOK;

    public NewGameScreen(Game g, Assets a, GameStatus gs) {
        this.g = g;
        this.a = a;
        this.gs = gs;
        this.font = new BitmapFont();
        font.setColor(Color.BLACK);
        batch = new SpriteBatch();
        // zmienne pomocnicze skaracjające długość późniejszego koduS
        sH = Gdx.graphics.getHeight();
        sW = Gdx.graphics.getWidth();

        portretGracza01 = a.mobElfTex;
        portretGracza02 = a.mobElfTex;
        dodajPrzyciski();
    }

    // dodaje przyciski do manipulowania opcjami nowej gry
    private void dodajPrzyciski() {
        // przycisk OK w prawym dolnym rogu
        buttonOK = new ButtonActor(a.btnOK, sW - a.btnOK.getWidth(), 1);
        buttonOK.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                zakonczGnerowanieNowejGry();
            }
        });
        stage01.addActor(buttonOK);

        // przycisk plus przy ilości graczy
        button01 = new ButtonActor(a.btnPlus, 60, sH - 110);
        button01.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tmpIloscGraczy += 1;
            }
        });
        stage01.addActor(button01);
        // przycisk minus przy ilosci graczy
        button02 = new ButtonActor(a.btnMinus, 60, sH - 130);
        button02.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tmpIloscGraczy -= 1;
            }
        });
        stage01.addActor(button02);
        // przycisk plus przy ilości bohaterów
        button03 = new ButtonActor(a.btnPlus, 560, sH - 110);
        button03.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tmpIloscBohaterow += 1;
            }
        });
        stage01.addActor(button03);
        // przycisk minus przy ilosci bohaterów
        button04 = new ButtonActor(a.btnMinus, 560, sH - 130);
        button04.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tmpIloscBohaterow -= 1;
            }
        });
        stage01.addActor(button04);

        // Przycisk left przy wybieraniu postaci gracza 1
        button05 = new ButtonActor(a.btnLeft, 100, 160);
        button05.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (wyborPortretGracz01 > 0) {
                    wyborPortretGracz01 -= 1;
                }
            }
        });
        stage01.addActor(button05);

        // Przycisk right przy wybieraniu postaci gracza 1
        button06 = new ButtonActor(a.btnRight, 120, 160);
        button06.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (wyborPortretGracz01 < 4) {
                    wyborPortretGracz01 += 1;
                }
            }
        });
        stage01.addActor(button06);

        // Przycisk left przy wybieraniu postaci gracza 2
        button07 = new ButtonActor(a.btnLeft, 400, 160);
        button07.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (wyborPortretGracz02 > 0) {
                    wyborPortretGracz02 -= 1;
                }
            }
        });
        stage01.addActor(button07);

        // Przycisk right przy wybieraniu postaci gracza 2
        button08 = new ButtonActor(a.btnRight, 420, 160);
        button08.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (wyborPortretGracz02 < 4) {
                    wyborPortretGracz02 += 1;
                }
            }
        });
        stage01.addActor(button08);
    }

    @Override
    public void render(float delta) {

        sprawdzZmienne();

        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage01.act();
        stage01.draw();

        batch.begin();

        font.draw(batch, "NOWA GRA", sW / 2, sH - 25);
        font.draw(batch, "ILOSC GRACZY: " + tmpIloscGraczy, 100, sH - 100);
        font.draw(batch, "MAX. ILOSC BOHATEROW: " + tmpIloscBohaterow, 600, sH - 100);
        font.draw(batch, "GRACZ 1: ", 100, sH - 300);
        font.draw(batch, "GRACZ 2: ", 400, sH - 300);
        font.draw(batch, "GRACZ 3: ", 700, sH - 300);
        font.draw(batch, "GRACZ 4: ", 1000, sH - 300);

        rysujPortretyGraczy();

        batch.end();
    }

    // Rysuje portrety graczy
    private void rysujPortretyGraczy() {

        if (wyborPortretGracz01 == 0) {
            portretGracza01 = a.mobDwarfTex;
            portretGracza01zaz = a.mobDwarfTexZaznaczony;
        } else if (wyborPortretGracz01 == 1) {
            portretGracza01 = a.mobElfTex;
            portretGracza01zaz = a.mobElfTexZaznaczony;
        } else if (wyborPortretGracz01 == 2) {
            portretGracza01 = a.mobOrkTex;
            portretGracza01zaz = a.mobOrkTexZaznaczony;
        } else if (wyborPortretGracz01 == 3) {
            portretGracza01 = a.mobHumanTex;
            portretGracza01zaz = a.mobHumanTexZaznaczony;
        }

        if (wyborPortretGracz02 == 0) {
            portretGracza02 = a.mobDwarfTex;
            portretGracza02zaz = a.mobDwarfTexZaznaczony;
        } else if (wyborPortretGracz02 == 1) {
            portretGracza02 = a.mobElfTex;
            portretGracza02zaz = a.mobElfTexZaznaczony;
        } else if (wyborPortretGracz02 == 2) {
            portretGracza02 = a.mobOrkTex;
            portretGracza02zaz = a.mobOrkTexZaznaczony;
        } else if (wyborPortretGracz02 == 3) {
            portretGracza02 = a.mobHumanTex;
            portretGracza02zaz = a.mobHumanTexZaznaczony;
        }

        batch.draw(this.portretGracza01, 100, 200);
        batch.draw(this.portretGracza02, 400, 200);
    }

    // funkcja sprawdza czy zmienne które zostaną przekazane do klasy GameStatus
    // zawierają się w odpowiednich przedziałach
    private void sprawdzZmienne() {
        // Sprawdzenie czy ilość graczy mieści się w przedziale
        if (tmpIloscGraczy < 2) {
            tmpIloscGraczy = 2;
        }
        if (tmpIloscGraczy > 4) {
            tmpIloscGraczy = 4;
        }
        // Sprawdzenie czy ilość bohaterów mieści sięw przedziale
        if (tmpIloscBohaterow < 1) {
            tmpIloscBohaterow = 1;
        }
        if (tmpIloscBohaterow > 4) {
            tmpIloscBohaterow = 4;
        }
    }

    // kod generujący zakończenie tworzenia nowej gry
    private void zakonczGnerowanieNowejGry() {
        gs.setActualScreen(1);
        gs.iloscGraczy = tmpIloscGraczy;
        // Po kliknięciu w OK następuje przekazanie info, że mapa
        // została utworzona (wszystkie parametry zadane przez screen
        // nowej gry zostaną użyte do tworzenia nowej mapy).
        gs.czyUtworzonoMape = true;

        // Dodoaje nowych graczy wg. ilości zadeklarowanej
        for (int i = 0; i < gs.iloscGraczy; i++) {
            gs.gracze.add(new Gracz());
            //System.out.println(gs.gracze.size());
        }
        // Dodanie dla każdego gracza bohatera
        for (int i = 0; i < gs.gracze.size(); i++) {
            // tymczasowa tekstura przekazana do konstruktora nowego bohatera
            Texture tmpTex, tmpTexZazanaczony;
            // tymczasowa tekstura do określenia lokacji początkowej gracza
            int lokPoczatkowaX, lokPoczatkowaY;

            // Ustawienie lokacji początkowej dla bohatera
            switch (i) {
                case 0:
                    lokPoczatkowaX = 0;
                    lokPoczatkowaY = 0;
                    tmpTex = portretGracza01;
                    tmpTexZazanaczony = portretGracza01zaz;
                    break;
                case 1:
                    lokPoczatkowaX = 900;
                    lokPoczatkowaY = 900;
                    tmpTex = portretGracza02;
                    tmpTexZazanaczony = portretGracza02zaz;
                    break;
                default:
                    lokPoczatkowaX = 0;
                    lokPoczatkowaY = 0;
                    tmpTex = portretGracza01;
                    tmpTexZazanaczony = portretGracza01zaz;
                    break;
            }
            //gs.gracze.get(i).getBohaterowieOld().add(new Player(tmpTex, tmpTexZazanaczony, gs, lokPoczatkowaX, lokPoczatkowaY, 0, 0));
            
            gs.gracze.get(i).getBohaterowie().add(new Bohater(tmpTex, tmpTexZazanaczony, lokPoczatkowaX, lokPoczatkowaY, this.a, 0, 0, gs));
            // Ustala do którego gracza z tablicy graczy należy bohater
            gs.gracze.get(i).getBohaterowie().get(0).setPrzynaleznoscDoGracza(i);
        }
        
        // Bohater gracza 1
        gs.getMapa().pola[0][0].setBohater(gs.gracze.get(0).getBohaterowie().get(0));
        gs.getMapa().pola[0][0].getBohater().setPozXnaMapie(0);
        gs.getMapa().pola[0][0].getBohater().setPozYnaMapie(0);
        // Bohater gracza 2
        gs.getMapa().pola[9][9].setBohater(gs.gracze.get(1).getBohaterowie().get(0));
        gs.getMapa().pola[9][9].getBohater().setPozXnaMapie(9);
        gs.getMapa().pola[9][9].getBohater().setPozYnaMapie(9);
        
        // Tymczasowe podpiecie z góry ustalonych statystyk
        gs.gracze.get(0).getBohaterowie().get(0).setAtak(5);
        gs.gracze.get(0).getBohaterowie().get(0).setObrona(5);
        gs.gracze.get(0).getBohaterowie().get(0).setHp(2);
        gs.gracze.get(0).getBohaterowie().get(0).setSzybkosc(2);
        
        gs.gracze.get(1).getBohaterowie().get(0).setAtak(4);
        gs.gracze.get(1).getBohaterowie().get(0).setObrona(4);
        gs.gracze.get(1).getBohaterowie().get(0).setHp(1);
        gs.gracze.get(1).getBohaterowie().get(0).setSzybkosc(3);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage01);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
