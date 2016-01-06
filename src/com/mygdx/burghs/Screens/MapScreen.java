package com.mygdx.burghs.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.burghs.Assets;
import com.mygdx.burghs.Bohater;
import com.mygdx.burghs.ButtonActor;
import com.mygdx.burghs.Castle;
import com.mygdx.burghs.DefaultActor;
import com.mygdx.burghs.GameStatus;
import com.mygdx.burghs.Gracz;
import com.mygdx.burghs.Mob;
import com.mygdx.burghs.TresureBox;
import enums.TypyTerenu;
import java.util.ArrayList;

public class MapScreen implements Screen {

    private DefaultActor gornaBelka;
    //private DefaultActor prawaBelka;

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

    // ikona gracza który aktualnie posiada swoją kolej 
    private final DefaultActor ikonaGracza;
    private final DefaultActor ikonaGold;

    // przechowuje referencje do obiektu bohatera który będzie atakowany
    //private Player atakowanyBohater;
    public MapScreen(final Game g, final Assets a, final GameStatus gs) {
        this.a = a;
        this.gs = gs;
        this.g = g;

        utworzInterfejs();

        ikonaGracza = new DefaultActor(a.btnAttackTex, 0, 0);
        ikonaGracza.getSprite().setTexture(gs.gracze.get(gs.getTuraGracza()).getTeksturaIkonyGracza());
        ikonaGracza.setPosition(110, Gdx.graphics.getHeight() - 23);

        ikonaGold = new DefaultActor(a.texGold, 315, Gdx.graphics.getHeight() - 25);
        ikonaGold.setSize(25, 25);

        Assets.stage01MapScreen = this.stage01;
        Assets.stage02MapScreen = this.stage02;

        btnKupBohatera = new TextButton("Kup bohatera", a.skin);
        btnKupBohatera.setSize(100, 50);
        btnKupBohatera.setPosition(Gdx.graphics.getWidth() - btnKupBohatera.getWidth() - 25, 175);
        btnKupBohatera.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Dodanie nowego bohatera.");
                g.setScreen(Assets.newBohaterScreen);
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
        lblTuraGracza.setPosition(10, Gdx.graphics.getHeight() - 25);
        //lblTuraGracza.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 25);
        lblPozostaloRuchow = new Label("Pozostalo ruchow: ", a.skin);
        //lblPozostaloRuchow.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 150);
        lblPozostaloRuchow.setPosition(150, Gdx.graphics.getHeight() - 25);

        lblGold = new Label("" + gs.getGracze().get(gs.getTuraGracza()).getGold(), a.skin);
        lblGold.setPosition(350, Gdx.graphics.getHeight() - 25);

        dodajDoStage02();

        generujPlansze();
        generujGraczy();

        dodajDoStage01();

        gs.czyUtworzonoMape = true;

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

        przesunKamereNadBohatera();

        // zmiana ikony gracza na górnej belce
        this.ikonaGracza.getSprite().setTexture(gs.gracze.get(gs.getTuraGracza()).getTeksturaIkonyGracza());
    }

    /**
     * Przesuwa kamerę nad bohatera
     */
    public void przesunKamereNadBohatera() {
        Camera cam = stage01.getCamera();
        System.out.println(cam.position);
        //cam.translate(100, 100, 0);
        float xCord = gs.getGracze().get(gs.getTuraGracza()).getBohaterowie().get(0).getX();
        float yCord = gs.getGracze().get(gs.getTuraGracza()).getBohaterowie().get(0).getY();

        cam.translate(xCord - cam.position.x + 200, yCord - cam.position.y + 100, 0);
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
                    //gs.getMapa().getPola()[i][j].getBohater().wylaczPrzyciski();
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

    /**
     * Tworzy wygląd interfejsu
     */
    private void utworzInterfejs() {
        Pixmap pmGornaBelka = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        pmGornaBelka.setColor(Color.LIGHT_GRAY);
        pmGornaBelka.fillRectangle(0, 0, Gdx.graphics.getWidth(), 30);
        pmGornaBelka.fillRectangle(Gdx.graphics.getWidth() - 250, 0, 250, Gdx.graphics.getHeight());
        pmGornaBelka.setColor(Color.BLACK);
        // czarna ramka wokół mapy 
        pmGornaBelka.drawRectangle(0, 30, Gdx.graphics.getWidth() - 250, Gdx.graphics.getHeight() - 30);
        pmGornaBelka.drawRectangle(1, 31, Gdx.graphics.getWidth() - 252, Gdx.graphics.getHeight() - 2 - 30);
        // imitacja miniMapy
        pmGornaBelka.fillRectangle(Gdx.graphics.getWidth() - 240, 30, 230, 200);
        pmGornaBelka.setColor(Color.WHITE);
        pmGornaBelka.drawRectangle(Gdx.graphics.getWidth() - 240, 30, 230, 200);

        Texture texGornaBelka = new Texture(pmGornaBelka);

        gornaBelka = new DefaultActor(texGornaBelka, 0, Gdx.graphics.getHeight() - texGornaBelka.getHeight());
    }

    // Dodaje do Stage 02 przycisk Exit i koniec tury oraz labele wyświetlające statystyki
    private void dodajDoStage02() {
        stage02.addActor(gornaBelka);
        stage02.addActor(lblTuraGracza);
        stage02.addActor(lblPozostaloRuchow);
        stage02.addActor(btnBohaterScreen);
        stage02.addActor(btnExit);
        stage02.addActor(btnKoniecTury);
        stage02.addActor(btnKupBohatera);
        stage02.addActor(lblGold);
        stage02.addActor(ikonaGracza);
        stage02.addActor(ikonaGold);
    }

    // Dodaj do stage 01 predefiniowane przyciski ruchu i ataku oraz przycisk cancel
    private void dodajDoStage01() {
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

    private Texture teksturaTerenu(TypyTerenu tT){
        System.out.println(tT);
        switch (tT){
            case Gory:
                return a.trawaGoraTex;                
            case Trawa:
                return a.trawaTex;
            case Drzewo:
                return a.trawaDrzewoTex;
        }
        return a.trawaTex;
    }
    
    // wypełnia stage01 aktorami planszy
    private void generujPlansze() {
        
        for (int i = 0; i < gs.getMapa().getIloscPolX(); i++) {
            for (int j = 0; j < gs.getMapa().getIloscPolY(); j++) {                
                teren.add(new DefaultActor(teksturaTerenu(gs.getMapa().getPola()[i][j].getTypTerenu()), i * 100, j * 100));                
            }            
        }
        
        for (DefaultActor teren1 : teren) {
            stage01.addActor(teren1);
        }

        // Tworzy nową skrzynie ze skarbem i wrzuca jej referencje do stage 01
        // oraz do obiektu mapy w obiekt pole.
        TresureBox tb = new TresureBox(this.a, this.gs, this.g);
        gs.getMapa().getPola()[2][2].setTresureBox(tb);
        stage01.addActor(gs.getMapa().getPola()[2][2].getTresureBox());

        // Tymczasowa obiekt moba
        Mob mob = new Mob(a.texWilkMob, gs, a, 300, 0, 1);
        gs.getMapa().getPola()[3][0].setMob(mob);
        stage01.addActor(gs.getMapa().getPola()[3][0].getMob());

        Mob mob2 = new Mob(a.texSzkieletMob, gs, a, 0, 300, 1);
        gs.getMapa().getPola()[0][3].setMob(mob2);
        stage01.addActor(gs.getMapa().getPola()[0][3].getMob());

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
                gs.getMapa().getPola()[0][9].setCastle(new Castle(a, 0, 900, 2));
                stage01.addActor(gs.getMapa().getPola()[0][9].getCastle());
                break;
            // generuje zamek dla 4 graczy
            case 4:
                generujZamki();
                gs.getMapa().getPola()[0][9].setCastle(new Castle(a, 0, 900, 2));
                stage01.addActor(gs.getMapa().getPola()[0][9].getCastle());

                gs.getMapa().getPola()[9][0].setCastle(new Castle(a, 900, 0, 3));
                stage01.addActor(gs.getMapa().getPola()[9][0].getCastle());
                break;
        }
    }

    // generuje zamki 2 dwóch podstawowych graczy
    private void generujZamki() {
        gs.getMapa().getPola()[0][0].setCastle(new Castle(a, 0, 0, 0));
        stage01.addActor(gs.getMapa().getPola()[0][0].getCastle());

        gs.getMapa().getPola()[9][9].setCastle(new Castle(a, 900, 900, 1));
        stage01.addActor(gs.getMapa().getPola()[9][9].getCastle());
    }

    @Override
    public void render(float delta) {

        sprawdzPolozenieKursora();
        ruchKamery();
        wyswietlStatystykiBohatera();
        this.lblGold.setText(Integer.toString(gs.getZlotoAktualnegoGracza()));

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

    /**
     * Metoda sortuje Zindex aktorów z ArrayList Stage01 ustawiając przyciski
     * jako te na 'górze'.
     */
    private void sortujZindex() {
        // Pętla szukająca przycisków
        for (int i = 0; i < stage01.getActors().size; i++) {
            if (stage01.getActors().get(i).getClass() == ButtonActor.class) {
                stage01.getActors().get(i).toBack();
            }
        }

        // Pętla szukająca bohaterowie
        for (int i = 0; i < stage01.getActors().size; i++) {
            if (stage01.getActors().get(i).getClass() == Bohater.class) {
                stage01.getActors().get(i).toBack();
            }
        }

        for (int i = 0; i < stage01.getActors().size; i++) {
            if (stage01.getActors().get(i).getClass() == TresureBox.class) {
                stage01.getActors().get(i).toBack();
            }
        }

        for (int i = 0; i < stage01.getActors().size; i++) {
            if (stage01.getActors().get(i).getClass() == Castle.class) {
                stage01.getActors().get(i).toBack();
            }
        }

        for (int i = 0; i < stage01.getActors().size; i++) {
            if (stage01.getActors().get(i).getClass() == Mob.class) {
                stage01.getActors().get(i).toBack();
            }
        }

        for (int i = 0; i < stage01.getActors().size; i++) {
            if (stage01.getActors().get(i).getClass() == DefaultActor.class) {
                stage01.getActors().get(i).toBack();
            }
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
        this.sortujZindex();
    }

    // Setters and Getters
    public Stage getStage01() {
        return stage01;
    }

}
