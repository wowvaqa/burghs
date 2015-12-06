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
import com.badlogic.gdx.utils.Align;
import com.mygdx.burghs.Assets;
import com.mygdx.burghs.GameStatus;
import com.mygdx.burghs.NewGame;

/**
 * Klasa definiuje Screen nowej gry zajmuje się zarządzaniem interfejsem
 *
 * @author v
 */
public class NewGameScreen implements Screen {

    private final Assets a;
    private final GameStatus gs;

    private final Table tabela01 = new Table();

    private final Table tabelaGracz01 = new Table();
    private final Table tabelaGracz02 = new Table();
    private final Table tabelaGracz03 = new Table();
    private final Table tabelaGracz04 = new Table();

    private final Stage stage01;

    private final Label lblIloscGraczy;

    private int iloscGraczy = 2;

    private boolean tabelaUtworzona = false;

    public NewGameScreen(Assets a, GameStatus gs) {

        // referencje do obiektów assetów i gamestatusu
        this.a = a;
        this.gs = gs;

        stage01 = new Stage();

        lblIloscGraczy = new Label(Integer.toString(iloscGraczy), a.skin);
    }

    private void dodajDoStage01() {
        stage01.addActor(tabela01);
    }

    private void formatujTabele01() {
        // ustawia rozmiar tebeli na cały ekran
        tabela01.setFillParent(true);
        // ustawia odstęp od krawędzi tabeli
        tabela01.pad(10);
        // włacza linie debugujące tabelę
        tabela01.setDebug(true);

        tabela01.add(new Label("Nowa Gra", a.skin)).align(Align.center).align(Align.top).expandX().colspan(tabela01.getColumns());
        tabela01.row();
        tabela01.add(new Label("Ilosc graczy", a.skin));
        tabela01.add(lblIloscGraczy);

        // Przycisk - przy wyborze ilosci graczy
        TextButton tB01 = new TextButton("-", a.skin);
        tB01.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                iloscGraczy = NewGame.odejmijGracza(iloscGraczy);
                lblIloscGraczy.setText(Integer.toString(iloscGraczy));
                tabela01.reset();
                formatujTabele01();
            }
        });
        tabela01.add(tB01);

        // Przycisk + przy wyborze ilośći graczy
        TextButton tB02 = new TextButton("+", a.skin);
        tB02.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                iloscGraczy = NewGame.dodajGracza(iloscGraczy);
                lblIloscGraczy.setText(Integer.toString(iloscGraczy));
                tabela01.reset();
                formatujTabele01();
            }
        });
        tabela01.add(tB02);

        tabela01.row();

        if (iloscGraczy == 2) {
            tabela01.add(tabelaGracz01).align(Align.topLeft).expand();            
            tabela01.add(tabelaGracz02).align(Align.topLeft).expand();
            tabela01.row();
        } else if (iloscGraczy == 3){
            tabela01.add(tabelaGracz01).align(Align.topLeft).expand();            
            tabela01.add(tabelaGracz02).align(Align.topLeft).expand();
            tabela01.add(tabelaGracz03).align(Align.topLeft).expand();
            tabela01.row();
        } else if (iloscGraczy == 4){
            tabela01.add(tabelaGracz01).align(Align.topLeft).expand();            
            tabela01.add(tabelaGracz02).align(Align.topLeft).expand();
            tabela01.add(tabelaGracz03).align(Align.topLeft).expand();
            tabela01.add(tabelaGracz04).align(Align.topLeft).expand();
            tabela01.row();
        }

        // Przycisk Zakończ
        TextButton btnExit = new TextButton("ZAKONCZ", a.skin);
        btnExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("EXIT");
            }
        });
        tabela01.add(btnExit).align(Align.top).expandX().colspan(tabela01.getColumns());

        tabelaUtworzona = true;
    }

    private void formatujTabeleGracza01() {
        tabelaGracz01.pad(10);
        tabelaGracz01.add(new Label("Gracz 1", a.skin));
        tabelaGracz01.row();

        // Przycisk - przy wyborze ilosci graczy
        TextButton g01B01 = new TextButton("-", a.skin);
        g01B01.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        tabelaGracz01.add(g01B01).pad(10);

        // Przycisk + przy wyborze ilośći graczy
        TextButton tB02 = new TextButton("+", a.skin);
        tB02.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        tabelaGracz01.add(tB02).pad(10);
    }

    private void formatujTabeleGracza02() {
        tabelaGracz02.pad(10);
        tabelaGracz02.add(new Label("Gracz 2", a.skin));
        tabelaGracz02.row();

        // Przycisk - przy wyborze ilosci graczy
        TextButton g01B01 = new TextButton("-", a.skin);
        g01B01.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        tabelaGracz02.add(g01B01).pad(10);

        // Przycisk + przy wyborze ilośći graczy
        TextButton tB02 = new TextButton("+", a.skin);
        tB02.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        tabelaGracz02.add(tB02).pad(10);
    }

    private void formatujTabeleGracza03() {
        tabelaGracz03.pad(10);
        tabelaGracz03.add(new Label("Gracz 3", a.skin));
        tabelaGracz03.row();

        // Przycisk - przy wyborze ilosci graczy
        TextButton g01B01 = new TextButton("-", a.skin);
        g01B01.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        tabelaGracz03.add(g01B01).pad(10);

        // Przycisk + przy wyborze ilośći graczy
        TextButton tB02 = new TextButton("+", a.skin);
        tB02.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        tabelaGracz03.add(tB02).pad(10);
    }

    private void formatujTabeleGracza04() {
        tabelaGracz04.pad(10);
        tabelaGracz04.add(new Label("Gracz 4", a.skin));
        tabelaGracz04.row();

        // Przycisk - przy wyborze ilosci graczy
        TextButton g01B01 = new TextButton("-", a.skin);
        g01B01.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        tabelaGracz04.add(g01B01).pad(10);

        // Przycisk + przy wyborze ilośći graczy
        TextButton tB02 = new TextButton("+", a.skin);
        tB02.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        tabelaGracz04.add(tB02).pad(10);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage01);
        if (!tabelaUtworzona) {
            formatujTabeleGracza01();
            formatujTabeleGracza02();
            formatujTabeleGracza03();
            formatujTabeleGracza04();
            formatujTabele01();
            dodajDoStage01();
        }
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
