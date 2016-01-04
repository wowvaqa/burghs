package com.mygdx.burghs.Testing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.mygdx.burghs.Assets;
import com.mygdx.burghs.Mapa;
import enums.TypyTerenu;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Edytor Map.
 *
 * @author v
 */
public class MapEditor implements Screen {

    private final Game g;
    private final Assets a;

    private final Stage stage01 = new Stage();
    private TextButton btnExit;

    private final Table tabela = new Table();
    private final Table tabela01 = new Table();

    private final Array array = new Array();
    private final ArrayList listaPol = new ArrayList();

    private int iloscPolX = 10;
    private int iloscPolY = 10;

    private Label lblIloscPolX;
    private Label lblIloscPolY;
    private TextButton btnPlus;
    private TextButton btnMinus;
    private TextButton btnZapiszMape;

    public MapEditor(Game g, Assets a) {
        this.g = g;
        this.a = a;

        array.add(TypyTerenu.Trawa);
        array.add(TypyTerenu.Gory);

        this.formatujTypyTerenu();
        this.makeButtons();
        this.formatujTeabele02();
        this.formatujTabele();
    }

    private void formatujTypyTerenu() {
        for (int i = 0; i < iloscPolX; i++) {
            for (int j = 0; j < iloscPolY; j++) {
                SelectBox sb = new SelectBox(a.skin);
                sb.setItems(array);
                listaPol.add(sb);
            }
        }
    }

    private void makeButtons() {

        btnZapiszMape = new TextButton("Zapisz Mape", a.skin);
        //btnZapiszMape.setPosition(1, 1);
        //btnZapiszMape.setSize(200, 100);
        btnZapiszMape.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    zapiszMape();
                } catch (IOException ex) {
                    Logger.getLogger(MapEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnExit = new TextButton("EXIT", a.skin);
        //btnExit.setPosition(1, 1);
        //btnExit.setSize(200, 100);
        btnExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.setScreen(Assets.mainMenuScreen);
            }
        });

        btnPlus = new TextButton("+", a.skin);
        btnPlus.setPosition(1, 1);
        btnPlus.setSize(200, 100);
        btnPlus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                iloscPolX += 1;
                iloscPolY += 1;
                lblIloscPolX.setText("Ilosc Pol X: " + iloscPolX);
                lblIloscPolY.setText("Ilosc Pol Y: " + iloscPolY);
                aktualizacjaPol();
            }
        });

        btnMinus = new TextButton("-", a.skin);
        btnMinus.setPosition(1, 1);
        btnMinus.setSize(200, 100);
        btnMinus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                iloscPolX -= 1;
                iloscPolY -= 1;
                lblIloscPolX.setText("Ilosc Pol X: " + iloscPolX);
                lblIloscPolY.setText("Ilosc Pol Y: " + iloscPolY);
                aktualizacjaPol();
            }
        });

        lblIloscPolX = new Label("Ilosc pol X: " + iloscPolX, a.skin);
        lblIloscPolY = new Label("Ilosc pol Y: " + iloscPolY, a.skin);
    }

    private void aktualizacjaPol() {
        listaPol.clear();
        this.formatujTypyTerenu();
        tabela01.clear();
        formatujTeabele02();
    }

    private void formatujTabele() {
        tabela.setFillParent(true);
        tabela.setDebug(true);
        tabela.pad(10);

        tabela.add(new Label("Map Editor", a.skin)).expandX().colspan(100).align(Align.top);
        tabela.row();
        tabela.add(lblIloscPolX);
        tabela.add(lblIloscPolY);
        tabela.row();
        tabela.add(btnMinus);
        tabela.add(btnPlus);

        tabela.row();
        tabela.add(tabela01).colspan(100).align(Align.center);
        tabela.row();
        tabela.add(btnExit).width(200).height(50).space(5).align(Align.right);
        tabela.add(btnZapiszMape).width(200).height(50).space(5).align(Align.left);

        stage01.addActor(tabela);
    }

    private void formatujTeabele02() {
        //tabela01.setFillParent(true);
        tabela01.setDebug(true);
        tabela01.pad(5);
        //tabela01.add(new Label("Screen", a.skin)).colspan(100).align(Align.center);

        int j = 0;

        for (Object listaPol1 : listaPol) {
            j += 1;
            tabela01.add((SelectBox) listaPol1);
            if (j > iloscPolY - 1) {
                j = 0;
                tabela01.row();
            }
        }

        tabela01.row();
    }

    /**
     * Generuje mapę i zapisuje do poliku.
     */
    private void zapiszMape() throws FileNotFoundException, IOException {
        int index = 0;
        Mapa mapa = new Mapa(iloscPolX, iloscPolY);
        for (int i = 0; i < iloscPolX; i++) {
            for (int j = 0; j < iloscPolY; j++) {
                mapa.getPola()[i][j].setTypTerenu((TypyTerenu) ((SelectBox) listaPol.get(index)).getSelected());
                index += 1;
            }
        }
        ObjectOutputStream wy = new ObjectOutputStream(new FileOutputStream("d:\\mapa.dat"));
        wy.writeObject(mapa);
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
