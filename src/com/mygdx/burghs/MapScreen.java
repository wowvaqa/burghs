package com.mygdx.burghs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import java.util.ArrayList;

public class MapScreen implements Screen {
    
    private final OrthographicCamera c;
    private final FitViewport viewPort;

    private final Assets a;
    private final GameStatus gs;
    private final Game g;

    private final Stage stage01 = new Stage();  // wyświetla mapę i playera
    private final Stage stage02 = new Stage();  // zarządza przyciskami interfejsu            

    private final TextButton btnExit;
    private final TextButton btnKoniecTury;
    private final TextButton btnBohaterScreen;
    private final TextButton btnKupBohatera;

    private final ArrayList<DefaultActor> teren = new ArrayList<DefaultActor>();

    // labele informujące o statystykach klikniętego bohatera
    private final Label lblGold;
    private final Label lblTuraGracza;
    private final Label lblPozostaloRuchow;

    // przechowuje referencje do obiektu bohatera który będzie atakowany
    //private Player atakowanyBohater;
    public MapScreen(final Game g, final Assets a, final GameStatus gs) {
        this.a = a;
        this.gs = gs;
        this.g = g;
        
        Assets.stage01MapScreen = this.stage01;
        Assets.stage02MapScreen = this.stage02;

        btnKupBohatera = new TextButton("Kup bohatera", a.skin);
        btnKupBohatera.setSize(100, 50);
        btnKupBohatera.setPosition(Gdx.graphics.getWidth() - btnKupBohatera.getWidth() - 25, 175);
        btnKupBohatera.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Dodanie nowego bohatera.");
                gs.getGracze().get(gs.getTuraGracza()).getBohaterowie().add(new Bohater(a.mobDwarfTex, a.mobDwarfTexZaznaczony, 0, 0, a, 0, 0, gs));
                int index = gs.getGracze().get(gs.getTuraGracza()).getBohaterowie().size() - 1;
                stage01.addActor(gs.getGracze().get(gs.getTuraGracza()).getBohaterowie().get(index));
                Bohater tmpBohater = gs.getGracze().get(gs.getTuraGracza()).getBohaterowie().get(index);
                tmpBohater.setPrzynaleznoscDoGracza(gs.getTuraGracza());
                tmpBohater.setSzybkosc(100);
                
            }            
        });

        // Dodaje przycisk wyjścia do planszy 02.
        btnBohaterScreen = new TextButton("Bohater", a.skin);
        btnBohaterScreen.setSize(100, 50);
        btnBohaterScreen.setPosition(Gdx.graphics.getWidth() - btnBohaterScreen.getWidth() - 25, 75);
        btnBohaterScreen.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (gs.isCzyZaznaczonoBohatera()) {
                    //gs.setActualScreen(5);
                    g.setScreen(Assets.bohaterScreen);
                } else {
                    System.out.println("Nie zaznaczono bohatera");
                }
            }
        });

        // Dodaje przycisk wyjścia do planszy 02.
        btnExit = new TextButton("EXIT", a.skin);
        btnExit.setSize(100, 50);
        btnExit.setPosition(Gdx.graphics.getWidth() - btnExit.getWidth() - 25, 25);
        btnExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exitClick();
            }
        });

        // Przycisk Koniec Tury
        // 1. Zmienia turę gracza na następnego gracza
        // 2. Sprawdza czy rozmiar tablicy graczy nie został przekroczony
        // jeżeli TRUE ustawia turę gracza na pierwszego z tablicy graczy
        // 3. Wyrzuca do labela aktualną turę gracza.
        btnKoniecTury = new TextButton("Koniec Tury", a.skin);
        btnKoniecTury.setSize(100, 50);
        btnKoniecTury.setPosition(Gdx.graphics.getWidth() - btnKoniecTury.getWidth() - 25, 125);
        btnKoniecTury.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                koniecTuryClick();
            }
        });

        // Dodanie etykiet prezentujących statsy graczy
        lblTuraGracza = new Label("Tura gracz: " + gs.getTuraGracza(), a.skin);
        lblTuraGracza.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 25);
        lblPozostaloRuchow = new Label("Pozostalo ruchow: ", a.skin);
        lblPozostaloRuchow.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 150);

        lblGold = new Label("Zloto: " + gs.getGracze().get(gs.getTuraGracza()).getGold(), a.skin);
        lblGold.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 75);

        dodajDoStage02();

        generujPlansze();
        generujGraczy();

        gs.czyUtworzonoMape = true;

        dodajDoStage01();
        
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        c = new OrthographicCamera(w, h);
        viewPort = new FitViewport(w, h, c);
    }

    /**
     * Przycisk Koniec Tury
     */
    private void koniecTuryClick() {

        if (!sprawdzCzyGraczPosiadaZamek()) {
            System.out.println("Sprawdzanie czy gracz posiada zamek.");
            // Jeżeli gracz nie będzie posiadał zamku wtedy zmianie ulegnie jego
            // status oraz zwiększona zostanie ilość tur bez zamku
            gs.getGracze().get(gs.getTuraGracza()).setStatusBezZamku(true);
            gs.getGracze().get(gs.getTuraGracza()).setTuryBezZamku(gs.getGracze().get(gs.getTuraGracza()).getTuryBezZamku() + 1);
            if (gs.getGracze().get(gs.getTuraGracza()).getTuryBezZamku() >= 5) {
                System.out.println("Gracz: " + gs.getTuraGracza() + " kaput");
                gs.getGracze().get(gs.getTuraGracza()).setStatusGameOver(true);
            }
            System.out.println("Gracz " + gs.getTuraGracza() + " nie posiada zamku: "
                    + gs.getGracze().get(gs.getTuraGracza()).getTuryBezZamku() + " tur.");
        }
        wylaczAktywnychBohaterow();
        sprawdzCzyKoniecTuryOgolnej();
        System.out.println("Koniec Tury");
        // Ustala turę następnego gracza
        gs.setTuraGracza(gs.getTuraGracza() + 1);
        if (gs.getTuraGracza() > gs.getGracze().size() - 1) {
            gs.setTuraGracza(0);
        }
        lblTuraGracza.setText("Tura gracz: " + Integer.toString(gs.getTuraGracza()));

        // Przywrócenie wszystkich punktów ruchu dla bohaterów
        for (Bohater i : gs.getGracze().get(gs.getTuraGracza()).getBohaterowie()) {
            i.setPozostaloRuchow(i.getSzybkosc());
        }
        usunBohaterowGraczyGO();
    }

    /**
     * Usuwa bohaterów graczy którzy mają status Game Over
     */
    private void usunBohaterowGraczyGO() {
        if (gs.getGracze().get(gs.getTuraGracza()).isStatusGameOver()) {
            if (gs.getGracze().get(gs.getTuraGracza()).getBohaterowie().size() > 0) {
                for (int i = 0; i < gs.getGracze().get(gs.getTuraGracza()).getBohaterowie().size(); i++) {
                    System.out.println("Usunięcie bohatera");
                    gs.getGracze().get(gs.getTuraGracza()).getBohaterowie().remove(i);

                }
            }
            for (int x = 0; x < gs.getMapa().getIloscPolX(); x++) {
                for (int y = 0; y < gs.getMapa().getIloscPolY(); y++) {
                    if (gs.getMapa().getPola()[x][y].getBohater() != null
                            && gs.getMapa().getPola()[x][y].getBohater().getPrzynaleznoscDoGracza() == gs.getTuraGracza()) {
                        System.out.println("XXX");
                        gs.getMapa().pola[x][y].getBohater().remove();
                        gs.getMapa().pola[x][y].setBohater(null);
                    }
                }
            }
            koniecTuryClick();
        }
    }

    /**
     * Sprawdza czy gracz posiada zamek. Zwraca True jeżeli posiada False jeżeli
     * nie posiada
     *
     * @return
     */
    private boolean sprawdzCzyGraczPosiadaZamek() {
        for (int i = 0; i < gs.getMapa().getIloscPolX(); i++) {
            for (int j = 0; j < gs.getMapa().getIloscPolY(); j++) {
                if (gs.getMapa().getPola()[i][j].getCastle() != null) {
                    if (gs.getMapa().getPola()[i][j].getCastle().getPrzynaleznoscDoGracza() == gs.getTuraGracza()) {
                        System.out.println("Gracz posiada zamek.");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Funkcja wyłącza aktywnych bohaterów
     */
    private void wylaczAktywnychBohaterow() {
        for (int i = 0; i < gs.getMapa().getIloscPolX(); i++) {
            for (int j = 0; j < gs.getMapa().getIloscPolY(); j++) {
                if (gs.getMapa().getPola()[i][j].getBohater() != null) {
                    gs.getMapa().getPola()[i][j].getBohater().wylaczPrzyciski();
                    gs.getMapa().getPola()[i][j].getBohater().setZaznaczony(false);
                }
            }
        }
    }

    // Działania wywołane po naciśnięciu przycisku Exit
    private void exitClick() {
        g.setScreen(Assets.mainMenuScreen);
        gs.setActualScreen(0);
    }

    /**
     * Funkcja sprawdza czy tura gry nie została zakończona Jezeli TRUE zwiększa
     * +1
     */
    private void sprawdzCzyKoniecTuryOgolnej() {
        if (gs.getTuraGracza() == gs.iloscGraczy - 1) {
            System.out.println("Koniec tury ogólnej");
            gs.setTuraGry(gs.getTuraGry() + 1);
            odnowZdrowieZamkow();
        }
    }

    /**
     * Odnawia co turę gry HP zamków +1
     */
    private void odnowZdrowieZamkow() {
        for (int i = 0; i < gs.getMapa().getIloscPolX(); i++) {
            for (int j = 0; j < gs.getMapa().getIloscPolY(); j++) {
                if (gs.getMapa().getPola()[i][j].getCastle() != null
                        && gs.getMapa().getPola()[i][j].getCastle().getActualHp()
                        < gs.getMapa().getPola()[i][j].getCastle().getMaxHp()) {
                    System.out.println("Zamek odnawia życie");
                    gs.getMapa().getPola()[i][j].getCastle().setActualHp(
                            gs.getMapa().getPola()[i][j].getCastle().getActualHp() + 1);
                }
            }
        }
    }

    // Dodaje do Stage 02 przycisk Exit i koniec tury oraz labele wyświetlające statystyki
    private void dodajDoStage02() {
        stage02.addActor(lblTuraGracza);
        stage02.addActor(lblPozostaloRuchow);
        stage02.addActor(btnBohaterScreen);
        stage02.addActor(btnExit);
        stage02.addActor(btnKoniecTury);
        stage02.addActor(btnKupBohatera);
        stage02.addActor(lblGold);
    }

    // Dodaj do stage 01 predefiniowane przyciski ruchu i ataku oraz przycisk cancel
    private void dodajDoStage01() {
        // Dodaje predefiniowane przyciski w obiekcie klasy Assets do Stage01
        // Przyciski ataku
        stage01.addActor(a.btnAtcNorth);
        stage01.addActor(a.btnAtcSouth);
        stage01.addActor(a.btnAtcEast);
        stage01.addActor(a.btnAtcWest);
        stage01.addActor(a.btnAtcNorthEast);
        stage01.addActor(a.btnAtcNorthWest);
        stage01.addActor(a.btnAtcSouthEast);
        stage01.addActor(a.btnAtcSouthWest);
        // Przyciski ruchu
        stage01.addActor(a.btnNorth);
        stage01.addActor(a.btnSouth);
        stage01.addActor(a.btnEast);
        stage01.addActor(a.btnWest);
        stage01.addActor(a.btnNorthEast);
        stage01.addActor(a.btnNorthWest);
        stage01.addActor(a.btnSouthEast);
        stage01.addActor(a.btnSouthWest);
        // Przycisk Cancel
        stage01.addActor(a.btnCancel);
        //stage01.addActor(window);

        // Dodaje do planszy info window z assetów do wyświetlania info o skrzynce ze skarbem
        stage01.addActor(a.getInfoWindow());

        stage01.addActor(a.lblDmg);
    }

    // Gneruje graczy w konstruktorze klasy i dodaje ich do planszy 01
    private void generujGraczy() {
        for (int i = 0; i < gs.getGracze().size(); i++) {
            stage01.addActor(gs.getGracze().get(i).getBohaterowie().get(0));
        }
    }

    // wypełnia stage01 aktorami planszy
    private void generujPlansze() {
        int y = 0;  // zmienna pomocnicza - ilość kolumn
        int x = 0;  // zmienna pomocnicza - ilość wierszy

        for (int i = 0; i < 100; i++) {
            if (a.mapa[i] == 1) {
                teren.add(new DefaultActor(a.trawaTex, x * 100, y * 100));
            } else if (a.mapa[i] == 3) {
                teren.add(new DefaultActor(a.trawaTex, x * 100, y * 100));
                // Stare wywołanie utworzenia zamku
                //teren.add(new DefaultActor(a.trawaZamekTex, x * 100, y * 100));
            } else {
                teren.add(new DefaultActor(a.trawaGoraTex, x * 100, y * 100));
            }
            stage01.addActor(teren.get(i));
            x += 1;
            if (x > 9) {
                x = 0;
                y += 1;
            }
        }

        // Tworzy nową skrzynie ze skarbem i wrzuca jej referencje do stage 01
        // oraz do obiektu mapy w obiekt pole.
        TresureBox tb = new TresureBox(this.a, this.gs);
        gs.getMapa().getPola()[2][2].setTresureBox(tb);
        stage01.addActor(gs.getMapa().getPola()[2][2].getTresureBox());

        // W zależności od iloścy gracz utworzone zostaja odpowiednie ilości 
        // zamków
        switch (gs.iloscGraczy) {
            // generuje zamek dla 2 graczy
            case 2:
                generujZamki();
                break;
            // generuje zamek dla 3 graczy
            case 3:
                generujZamki();
                gs.getMapa().getPola()[0][9].setCastle(new Castle(this.stage01, a, 0, 900));
                stage01.addActor(gs.getMapa().getPola()[0][9].getCastle());
                gs.getMapa().getPola()[0][9].getCastle().setPrzynaleznoscDoGracza(2);
                break;
            // generuje zamek dla 4 graczy
            case 4:
                generujZamki();
                gs.getMapa().getPola()[0][9].setCastle(new Castle(this.stage01, a, 0, 900));
                stage01.addActor(gs.getMapa().getPola()[0][9].getCastle());
                gs.getMapa().getPola()[0][9].getCastle().setPrzynaleznoscDoGracza(2);

                gs.getMapa().getPola()[9][0].setCastle(new Castle(this.stage01, a, 900, 0));
                stage01.addActor(gs.getMapa().getPola()[9][0].getCastle());
                gs.getMapa().getPola()[9][0].getCastle().setPrzynaleznoscDoGracza(3);
                break;
        }
    }

    // generuje zamki 2 dwóch podstawowych graczy
    private void generujZamki() {
        gs.getMapa().getPola()[0][0].setCastle(new Castle(this.stage01, a, 0, 0));
        stage01.addActor(gs.getMapa().getPola()[0][0].getCastle());
        gs.getMapa().getPola()[0][0].getCastle().setPrzynaleznoscDoGracza(0);

        gs.getMapa().getPola()[9][9].setCastle(new Castle(this.stage01, a, 900, 900));
        stage01.addActor(gs.getMapa().getPola()[9][9].getCastle());
        gs.getMapa().getPola()[9][9].getCastle().setPrzynaleznoscDoGracza(1);
    }

    @Override
    public void render(float delta) {

        sprawdzPolozenieKursora();
        ruchKamery();
        wyswietlStatystykiBohatera();

        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage01.act();
        stage01.draw();

        stage02.act();
        stage02.draw();
    }

    // Sprawdza położenie kursora 
    // w zależności od lokalizacji ustawia sterowanie na odpowiedni stage
    private void sprawdzPolozenieKursora() {
        //Ustawia stage 02 na 1/6 ekranu z prawej strony
        if (Gdx.input.getX() < Gdx.graphics.getWidth() / 6 * 5) {
            Gdx.input.setInputProcessor(stage01);
        } else {
            Gdx.input.setInputProcessor(stage02);
        }
    }

    /**
     * 1. Sprawdza każdego bohatera z Graczy pod względem zaznaczenia. 2. Jeżeli
     * TRUE wtedy ładuje statsy bohatera do labeli z MapScreena
     */
    private void wyswietlStatystykiBohatera() {
        for (Gracz i : gs.getGracze()) {
            for (Bohater j : i.getBohaterowie()) {
                if (j.isZaznaczony()) {
                    lblPozostaloRuchow.setText("Pozostalo ruchow: " + Integer.toString(j.getPozostaloRuchow()));
                }
            }
        }
    }

    // Steruje ruchem kamery
    private void ruchKamery() {

        int predkoscRuchuKamery = gs.getPredkoscScrollowaniaKamery();
        int predkoscZoom = gs.getPredkoscZoomKamery();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            stage01.getCamera().translate(-predkoscRuchuKamery, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            stage01.getCamera().translate(predkoscRuchuKamery, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            stage01.getCamera().translate(0, predkoscRuchuKamery, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            stage01.getCamera().translate(0, -predkoscRuchuKamery, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            stage01.getCamera().viewportHeight += predkoscZoom;
            stage01.getCamera().viewportWidth += predkoscZoom;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            stage01.getCamera().viewportHeight -= predkoscZoom;
            stage01.getCamera().viewportWidth -= predkoscZoom;
        }
    }

    // Setters and Getters
    public GameStatus getGs() {
        return gs;
    }

    @Override
    public void resize(int width, int height) {
        stage01.getViewport().update(width, height, true);
        stage02.getViewport().update(width, height, true);
        viewPort.update(width, height, true);
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

    @Override
    public void show() {
        this.lblGold.setText("Zloto: " + Integer.toString(gs.getZlotoAktualnegoGracza()));
    }
}
