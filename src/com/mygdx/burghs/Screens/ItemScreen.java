/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.burghs.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.burghs.Assets;
import com.mygdx.burghs.GameStatus;

/**
 * Klasa odpowiada za wyświetlanie Screenu z informacjami o itemkach
 *
 * @author v
 */
public class ItemScreen implements Screen {

    private final Assets a;
    private final GameStatus gs;    

    private final Stage stage01 = new Stage();
    
    private final Table tabela;
    
    private final TextButton btnExit;

    public ItemScreen(Assets a, final GameStatus gs) {
        this.a = a;
        this.gs = gs;
        this.tabela = new Table(a.skin);

        btnExit = new TextButton("Exit", this.a.skin);
        btnExit.addListener(
                new ClickListener() {
                    GameStatus tmpGs = gs;

                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        tmpGs.setActualScreen(tmpGs.getLastScreen());
                    }
                }
        );        
    }
    
    private void formatujTabele(){
        Label label = new Label(gs.getItem().getNazwa(), a.skin);        
        
        tabela.setFillParent(true);
        // ustawia odstęp od krawędzi tabeli
        tabela.pad(10);
        // włacza linie debugujące tabelę
        tabela.setDebug(true);
        tabela.add(label);
        tabela.row();
        tabela.add(btnExit);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage01);
        
        formatujTabele();
        
        stage01.addActor(tabela);
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
    }
}
