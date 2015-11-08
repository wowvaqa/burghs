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
    
    // Labele
    private Label lblBohater;
    private Label lblAtak, lblObrona, lblHp, lblSzybkosc;
    private Label lblExp, lblExpToNextLevel, lblLevel;
    private Label lblStopy, lblNogi, lblLewaReka;
    
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
    private void formatujTabele(){
        // ustawia rozmiar tebeli na cały ekran
        tabela.setFillParent(true);
        // ustawia odstęp od krawędzi tabeli
        tabela.pad(10);
        // włacza linie debugujące tabelę
        //tabela.setDebug(true);
        
        // dodaje label do tabeli        
        tabela.add(lblBohater).expand().align(Align.top);
        tabela.row();
        tabela.add(btnExit).width(100).height(50);
    }
    
    // Tworzy labele
    private void utworzLabele(){
        lblBohater = new Label("BOHATER", a.skin);
        lblAtak = new Label("Atak: ", a.skin);        
        lblObrona = new Label("Obrona: ", a.skin);        
        lblHp = new Label("HP: ", a.skin);        
        lblSzybkosc = new Label("Szybkosc: ", a.skin);
        lblLevel = new Label("Poziom: ", a.skin);        
        lblExp = new Label("Doswiadczenie: ", a.skin);        
        lblExpToNextLevel = new Label("Pozostalo ruchow: ", a.skin);        
        lblNogi = new Label("Nogi: ", a.skin);        
        lblLewaReka = new Label("L. Reka: ", a.skin);        
    }
    
    // Tworzy przyciski
    private void utworzPrzyciski(){
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
    private void dodajDoStage01(){
        stage01.addActor(tabela);
    }
    
    private Bohater sprawdzBohatera(){
        return null;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage01);
        
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
