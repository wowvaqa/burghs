package com.mygdx.burghs.Screens;

// Screen Opcji
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
import com.mygdx.burghs.Assets;
import com.mygdx.burghs.GameStatus;

// Odpowiada za wyświetlanie opcji z gry.
public class OptionsScreen implements Screen {

    private final GameStatus gs;
    private final Assets a;
    private final Stage stage01 = new Stage();                                  // scena najniższa

    // Label
    private Label lblOpcje;

    // Przycisk wyjścia z menu opcji
    private TextButton btnExit;

    // Tabela
    private final Table tabela = new Table();

    public OptionsScreen(GameStatus gs, Assets a) {
        this.gs = gs;
        this.a = a;

        formatujTabele();
        dodajPrzyciski();
    }

    // formatuje tabelę
    private void formatujTabele(){
        // ustawia rozmiar tebeli na cały ekran
        tabela.setFillParent(true);
        // ustawia odstęp od krawędzi tabeli
        tabela.pad(10);
        // włacza linie debugujące tabelę
        //tabela.setDebug(true);
        
        // dodaje label do tabeli
        lblOpcje = new Label("OPCJE", a.skin);
        tabela.add(lblOpcje).expand().align(Align.top);
        tabela.row();
        
        stage01.addActor(tabela);
    }
    
    private void dodajPrzyciski() {
        btnExit = new TextButton("EXIT", a.skin);
        btnExit.setSize(100, 50);
        //btnExit.setPosition(Gdx.graphics.getWidth() - btnExit.getWidth() - 25, 25);

        btnExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gs.setActualScreen(0);
            }
        });

        tabela.add(btnExit).width(200).height(50);        
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage01);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage01.act();
        stage01.draw();
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
        stage01.dispose();
    }
}
