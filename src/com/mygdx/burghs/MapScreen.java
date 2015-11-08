package com.mygdx.burghs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.util.ArrayList;

public class MapScreen implements Screen {

    //private final Game g;
    private final Assets a;
    private final GameStatus gs;

    private final Stage stage01 = new Stage();  // wyświetla mapę i playera
    private final Stage stage02 = new Stage();  // zarządza przyciskami interfejsu        

    private final TextButton btnExit;
    private final TextButton btnKoniecTury;
    private final TextButton btnBohaterScreen;

    private final ArrayList<DefaultActor> teren = new ArrayList<DefaultActor>();

    // labele informujące o statystykach klikniętego bohatera
    private Label lblAtak, lblObrona, lblHp, lblSzybkosc;
    private Label lblExp, lblExpToNextLevel, lblLevel;
    private Label lblStopy, lblNogi, lblLewaReka;
    private Label lblGold;
    private final Label lblTuraGracza;
    private final Label lblPozostaloRuchow;
    
    private Window window;

    // przechowuje referencje do obiektu bohatera który będzie atakowany
    //private Player atakowanyBohater;
    public MapScreen(Game g, Assets a, final GameStatus gs) {
        this.a = a;
        //this.g = g;
        this.gs = gs;
        
        utworzOkno();

        // Dodaje przycisk wyjścia do planszy 02.
        btnBohaterScreen = new TextButton("Bohater", a.skin);
        btnBohaterScreen.setSize(100, 50);
        btnBohaterScreen.setPosition(Gdx.graphics.getWidth() - btnBohaterScreen.getWidth() - 25, 75);
        btnBohaterScreen.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gs.setActualScreen(5);
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
        lblAtak = new Label("A: ", a.skin);
        lblAtak.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 50);
        lblObrona = new Label("O: ", a.skin);
        lblObrona.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 75);
        lblHp = new Label("HP: ", a.skin);
        lblHp.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 100);
        lblSzybkosc = new Label("S: ", a.skin);
        lblSzybkosc.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 125);
        lblPozostaloRuchow = new Label("Pozostalo ruchow: ", a.skin);
        lblPozostaloRuchow.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 150);
        lblLevel = new Label("Poziom: ", a.skin);
        lblLevel.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 175);
        lblExp = new Label("Doswiadczenie: ", a.skin);
        lblExp.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 200);
        lblExpToNextLevel = new Label("Pozostalo ruchow: ", a.skin);
        lblExpToNextLevel.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 225);
        lblNogi = new Label("Nogi: ", a.skin);
        lblNogi.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 250);
        lblLewaReka = new Label("L. Reka: ", a.skin);
        lblLewaReka.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 275);

        dodajDoStage02();

        generujPlansze();
        generujGraczy();

        gs.czyUtworzonoMape = true;

        dodajDoStage01();
    }
    
    private void utworzOkno(){
        // Dodanie nowego okna
        window = new Window("ATAK", a.skin);
        window.add("Obrazenia: ");
        window.setSize(400, 300);
        window.setPosition(500, 500);
        window.setVisible(false);        
    }

    // Działania wywołane po naciśnięciu przycisku koniec tury.
    private void koniecTuryClick() {
        System.out.println("Koniec Tury");
        // Ustala turę następnego gracza
        gs.setTuraGracza(gs.getTuraGracza() + 1);
        if (gs.getTuraGracza() > gs.getGracze().size() - 1) {
            gs.setTuraGracza(0);            
        }
        lblTuraGracza.setText("Tura gracz: " + Integer.toString(gs.getTuraGracza()));
        
        // Przywrócenie wszystkich punktów ruchu dla bohaterów
        for (Bohater i: gs.getGracze().get(gs.getTuraGracza()).getBohaterowie()){
            i.setPozostaloRuchow(i.getSzybkosc());            
        }
    }

    // Działania wywołane po naciśnięciu przycisku Exit
    private void exitClick() {
        gs.setActualScreen(0);
    }

    // Dodaje do Stage 02 przycisk Exit i koniec tury oraz labele wyświetlające statystyki
    private void dodajDoStage02() {
        stage02.addActor(lblTuraGracza);
        stage02.addActor(lblAtak);
        stage02.addActor(lblObrona);
        stage02.addActor(lblHp);
        stage02.addActor(lblSzybkosc);
        stage02.addActor(lblPozostaloRuchow);
        stage02.addActor(btnBohaterScreen);
        stage02.addActor(btnExit);
        stage02.addActor(btnKoniecTury);
        stage02.addActor(lblExp);
        stage02.addActor(lblExpToNextLevel);
        stage02.addActor(lblLevel);
        stage02.addActor(lblNogi);
        stage02.addActor(lblLewaReka);
        
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
        stage01.addActor(window);
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
                teren.add(new DefaultActor(a.trawaZamekTex, x * 100, y * 100));
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
                    lblAtak.setText("A: " + Integer.toString(j.getAtak()));
                    lblObrona.setText("O: " + Integer.toString(j.getObrona()));
                    lblHp.setText("HP: " + Integer.toString(j.getHp()));
                    lblSzybkosc.setText("S: " + Integer.toString(j.getSzybkosc()));
                    lblPozostaloRuchow.setText("Pozostalo ruchow: " + Integer.toString(j.getPozostaloRuchow()));
                    lblExp.setText("Exp: " + Integer.toString(j.getExp()));
                    lblExpToNextLevel.setText("Exp to next level: " + Integer.toString(j.getExpToNextLevel()));
                    lblLevel.setText("Poziom doś.: " + Integer.toString(j.getLevelOfExp()));
                    lblNogi.setText("Nogi.: " + j.getItemNogi().getNazwa());
                    lblLewaReka.setText("Lewa Reka: " + j.getItemLewaReka().getNazwa());
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
        //Gdx.input.setInputProcessor(stage01);
    }

}
