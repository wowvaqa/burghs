package com.mygdx.burghs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

/**
 * Wyświetla screen z statystykami oraz ekwipunkiem bohatera
 *
 * @author wow
 */
public class BohaterScreen implements Screen {

    // Referencje do obiektu assetów, statusu gry, bohatera który jest kliknięty
    private final Assets a;
    private final GameStatus gs;

    // Plansza
    private final Stage stage01 = new Stage();

    // Przyciski
    private TextButton btnExit;    

    // Inne
    // Labele
    private Label lblBohater;
    private Label lblAtak, lblObrona, lblHp, lblSzybkosc;
    private Label lblExp, lblExpToNextLevel, lblLevel;
    private Label lblStopy, lblNogi, lblLewaReka, lblPrawaReka, lblKorpus, lblGlowa;

    // Tabela
    private final Table tabela = new Table();

    public BohaterScreen(Assets a, GameStatus gs) {
        this.a = a;
        this.gs = gs;

        utworzPrzyciski();
        utworzLabele();
        formatujTabele();
        dodajDoStage01();
    }

    // Formatuje tabele dodaje do niej elementy
    private void formatujTabele() {
        // ustawia rozmiar tebeli na cały ekran
        tabela.setFillParent(true);
        // ustawia odstęp od krawędzi tabeli
        tabela.pad(10);
        // włacza linie debugujące tabelę
        tabela.setDebug(true);        

        // dodaje label do tabeli        
        tabela.add(lblBohater).align(Align.top).expandX().colspan(tabela.getColumns());

        tabela.row();

        tabela.add(lblAtak).align(Align.topLeft);
        tabela.add(lblObrona).align(Align.topLeft);
        tabela.add(lblHp).align(Align.topLeft);
        tabela.add(lblSzybkosc).align(Align.topLeft);
        tabela.row();

        tabela.add(lblLevel).align(Align.left);
        tabela.add(lblExp).align(Align.left);
        tabela.add(lblExpToNextLevel).align(Align.left);
        tabela.row();
        
        tabela.add(lblGlowa).align(Align.left);
        tabela.add(lblLewaReka).align(Align.left);
        tabela.add(lblPrawaReka).align(Align.left);
        tabela.row();
        
        tabela.add(lblKorpus).align(Align.left);
        tabela.add(lblNogi).align(Align.left);
        tabela.add(lblStopy).align(Align.left);
        tabela.row();
        
        tabela.add(btnExit).expand().align(Align.bottom).width(100).height(50).colspan(tabela.getColumns());
    }

    // Tworzy labele
    private void utworzLabele() {
        lblBohater = new Label("BOHATER", a.skin);
        lblAtak = new Label("Atak: ", a.skin);
        lblObrona = new Label("Obrona: ", a.skin);
        lblHp = new Label("HP: ", a.skin);
        lblSzybkosc = new Label("Szybkosc: ", a.skin);
        lblLevel = new Label("Poziom: ", a.skin);
        lblExp = new Label("Doswiadczenie: ", a.skin);
        lblExpToNextLevel = new Label("Pozostalo nast. poz.: ", a.skin);
        lblNogi = new Label("Nogi: ", a.skin);
        lblLewaReka = new Label("L. Reka: ", a.skin);
        lblPrawaReka = new Label("", a.skin);
        lblKorpus = new Label("", a.skin);
        lblGlowa = new Label("", a.skin);
        lblStopy = new Label("", a.skin);

    }

    // Tworzy przyciski
    private void utworzPrzyciski() {
        btnExit = new TextButton("EXIT", a.skin);
        btnExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gs.setActualScreen(1);
                System.out.println("Exit klikniety");
            }
        });        
    }

    // Dodaje do stage01 tabele
    private void dodajDoStage01() {
        stage01.addActor(tabela);
    }

    // Przeszukuje wszystkich bohaterów sprawdzając czy ktoryś nie jest zaznaczony
    // Jeżeli true zwraca referencje do zaznaczonego bohatera.
    private Bohater sprawdzBohatera() {
        Bohater bohater = null;
        for (Gracz g : gs.getGracze()) {
            for (Bohater b : g.getBohaterowie()) {
                if (b.isZaznaczony()) {
                    return b;
                }
            }
        }
        return bohater;
    }

    // Aktualizuje labele o dane klikniętego bohatera
    private void aktualizujLabele() {

        lblAtak.setText("Atak: " + sprawdzBohatera().getAtak());
        lblObrona.setText("Obrona: " + sprawdzBohatera().getObrona());
        lblHp.setText("HP: " + sprawdzBohatera().getHp());
        lblSzybkosc.setText("Szybkosc: " + sprawdzBohatera().getSzybkosc());

        lblLevel.setText("Poziom: " + sprawdzBohatera().getLevelOfExp());
        lblExp.setText("Punkty doswiadczenia: " + sprawdzBohatera().getExp());
        lblExpToNextLevel.setText("Punkty do nst. poz.: " + sprawdzBohatera().getExpToNextLevel());

        lblKorpus.setText("Korpus: " + sprawdzBohatera().getItemKorpus().getNazwa());
        lblGlowa.setText("Glowa: " + sprawdzBohatera().getItemGlowa().getNazwa());
        lblLewaReka.setText("L. Reka: " + sprawdzBohatera().getItemLewaReka().getNazwa());        
        lblPrawaReka.setText("P. Reka: " + sprawdzBohatera().getItemPrawaReka().getNazwa());
        lblNogi.setText("Nogi: " + sprawdzBohatera().getItemNogi().getNazwa());
        lblStopy.setText("Stopy: " + sprawdzBohatera().getItemStopy().getNazwa());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage01);

        if (gs.isCzyZaznaczonoBohatera()) {
            aktualizujLabele();
        }
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage01.act();
        stage01.draw();
    }

    @Override
    public void resize(int i, int i1) {

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

    // Setters and Getters
}
