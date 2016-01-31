package com.mygdx.burghs.Screens;

import com.badlogic.gdx.Game;
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
import com.mygdx.burghs.Bohater;
import com.mygdx.burghs.GameStatus;

/**
 * Wyświetla screen Awansu bohatera.
 *
 * @author v
 */
public class AwansScreen implements Screen {

    private final Assets a;
    private final GameStatus gs;
    private final Game g;

    private final Stage stage01 = new Stage();

    private final TextButton btnExit;

    private Awans awans = new Awans();

    /**
     * Główna tabela.
     */
    private final Table mainTable;
    private final Table leftTable;
    private final Table rightTable;

    /**
     * informuje czy Screen awansu jest pokazany
     */
    private boolean awansScreenShow = false;

    //private final TextButton btnExit;
    public AwansScreen(final Game g, Assets a, final GameStatus gs) {
        this.g = g;
        this.a = a;
        this.gs = gs;

        btnExit = new TextButton("ZAKONCZ", this.a.skin);
        btnExit.addListener(
                new ClickListener() {

                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        
                        awans.zakonczAwans(gs);
                        
                        awansScreenShow = false;
                        mainTable.clear();
                        rightTable.clear();
                        leftTable.clear();
                        g.setScreen(Assets.mapScreen);
                    }
                }
        );

        leftTable = new Table(a.skin);
        rightTable = new Table(a.skin);
        mainTable = new Table(a.skin);
    }

    /**
     * Formatuje główną tabelę.
     */
    private void formatMainTable() {
        formatLeftTable();
        formatRightTable();

        mainTable.setFillParent(true);
        // ustawia odstęp od krawędzi tabeli
        mainTable.pad(10);
        // włacza linie debugujące tabelę
        mainTable.setDebug(true);

        Label lblTitle = new Label("AWANS", this.a.skin);
        mainTable.add(lblTitle).expandX().pad(5).colspan(10);
        mainTable.row();
        mainTable.add(leftTable);
        mainTable.add(rightTable).expand().colspan(10);
        mainTable.row();
        mainTable.add(btnExit).expandX().pad(5).colspan(10).size(100, 50);
    }

    private void formatLeftTable() {
        leftTable.pad(10);
        leftTable.setDebug(true);
    }

    private void formatRightTable() {        
        rightTable.pad(10);
        rightTable.setDebug(true);

        Label lblAtrybutKlasowy = new Label("Atrybut klasowy: " + awans.getAtrybutKlasowy(), this.a.skin);
        rightTable.add(lblAtrybutKlasowy).colspan(10).align(Align.center);
        rightTable.row();

        Label lbl01 = new Label("Wybierz atrybut: ", this.a.skin);
        rightTable.add(lbl01).colspan(10).align(Align.center);
        rightTable.row();
        TextButton btn01 = new TextButton("A", a.skin);
        TextButton btn02 = new TextButton("O", a.skin);
        TextButton btn03 = new TextButton("S", a.skin);
        TextButton btn04 = new TextButton("HP", a.skin);
        TextButton btn05 = new TextButton("M", a.skin);
        TextButton btn06 = new TextButton("W", a.skin);

        btn01.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                awans.zwiekszAtak();
                reformatujTabele();                
            }
        });

        btn02.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                awans.zwiekszObrone();
                reformatujTabele();
            }
        });
        
        btn03.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                awans.zwiekszSzybkosc();
                reformatujTabele();
            }
        });
        
        btn04.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                awans.zwiekszHp();
                reformatujTabele();
            }
        });
        
        btn05.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                awans.zwiekszMoc();
                reformatujTabele();
            }
        });
        
        btn06.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                awans.zwiekszWiedze();
                reformatujTabele();
            }
        });
        
        rightTable.add(btn01).size(50).align(Align.center).pad(25);
        rightTable.add(btn02).size(50).align(Align.center).pad(25);
        rightTable.add(btn03).size(50).align(Align.center).pad(25);
        rightTable.add(btn04).size(50).align(Align.center).pad(25);
        rightTable.add(btn05).size(50).align(Align.center).pad(25);
        rightTable.add(btn06).size(50).align(Align.center).pad(25);

        rightTable.row();

        Label lblOpis1 = new Label("Aktualne: ", a.skin);
        Label lblatc = new Label("Atak: " + gs.getBohaterZaznaczony().getAtak(), a.skin);
        Label lblobr = new Label("Obrona: " + gs.getBohaterZaznaczony().getObrona(), a.skin);
        Label lblszb = new Label("Szybkosc: " + gs.getBohaterZaznaczony().getSzybkosc(), a.skin);
        Label lblhpp = new Label("Hp: " + gs.getBohaterZaznaczony().getHp(), a.skin);
        Label lblmoc = new Label("Moc: " + gs.getBohaterZaznaczony().getMoc(), a.skin);
        Label lblwie = new Label("Wiedza: " + gs.getBohaterZaznaczony().getWiedza(), a.skin);

        rightTable.add(lblOpis1).colspan(10).align(Align.center);
        rightTable.row();
        rightTable.add(lblatc).pad(5);
        rightTable.add(lblobr).pad(5);
        rightTable.add(lblszb).pad(5);
        rightTable.add(lblhpp).pad(5);
        rightTable.add(lblmoc).pad(5);
        rightTable.add(lblwie).pad(5);
        rightTable.row();

        Label lblOpis2 = new Label("Po awansie: ", a.skin);
        Label lblatc2 = new Label("Atak: " + (gs.getBohaterZaznaczony().getAtak() + awans.tmpAtak + awans.atrKlasowyAtak), a.skin);
        Label lblobr2 = new Label("Obrona: " + (gs.getBohaterZaznaczony().getObrona() + awans.tmpObrona + awans.atrKlasowyObrona), a.skin);
        Label lblszb2 = new Label("Szybkosc: " + (gs.getBohaterZaznaczony().getSzybkosc() + awans.tmpSzybkosc + awans.atrKlasowySzybkosc), a.skin);
        Label lblhpp2 = new Label("Hp: " + (gs.getBohaterZaznaczony().getHp() + awans.tmpHp + awans.atrKlasowyHp), a.skin);
        Label lblmoc2 = new Label("Moc: " + (gs.getBohaterZaznaczony().getMana() + awans.tmpMoc + awans.atrKlasowyMoc), a.skin);
        Label lblwie2 = new Label("Wiedza: " + (gs.getBohaterZaznaczony().getMana() + awans.tmpWiedza + awans.atrKlasowyWiedza), a.skin);

        rightTable.add(lblOpis2).colspan(10).align(Align.center);
        rightTable.row();
        rightTable.add(lblatc2).pad(5);
        rightTable.add(lblobr2).pad(5);
        rightTable.add(lblszb2).pad(5);
        rightTable.add(lblhpp2).pad(5);
        rightTable.add(lblmoc2).pad(5);
        rightTable.add(lblwie2).pad(5);
        rightTable.row();
    }

    /**
     * Formatuje wszystkie tabele.
     */
    private void reformatujTabele() {
        mainTable.clear();
        rightTable.clear();
        leftTable.clear();
        formatMainTable();
    }

    @Override
    public void show() {
        if (!awansScreenShow) {
            Gdx.input.setInputProcessor(stage01);
            formatMainTable();
            stage01.addActor(mainTable);
            awansScreenShow = true;
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

    public class Awans {

        public int tmpAtak = 0;
        public int tmpObrona = 0;
        public int tmpSzybkosc = 0;
        public int tmpHp = 0;
        public int tmpMoc = 0;
        public int tmpWiedza = 0;

        public int atrKlasowyAtak = 0;
        public int atrKlasowyObrona = 0;
        public int atrKlasowySzybkosc = 0;
        public int atrKlasowyHp = 0;
        public int atrKlasowyMoc = 0;
        public int atrKlasowyWiedza = 0;

        public void zwiekszAtak() {
            wyczyscTmpStats();
            tmpAtak += 1;
        }

        public void zwiekszObrone() {
            wyczyscTmpStats();
            tmpObrona += 1;
        }

        public void zwiekszSzybkosc() {
            wyczyscTmpStats();
            tmpSzybkosc += 1;
        }

        public void zwiekszHp() {
            wyczyscTmpStats();
            tmpHp += 1;
        }

        public void zwiekszMoc() {
            wyczyscTmpStats();
            tmpMoc += 1;
        }

        public void zwiekszWiedze() {
            wyczyscTmpStats();
            tmpWiedza += 1;
        }
        
        private void wyczyscTmpStats() {
            this.tmpAtak = 0;
            this.tmpObrona = 0;
            this.tmpSzybkosc = 0;
            this.tmpHp = 0;
            this.tmpMoc = 0;
            this.tmpWiedza = 0;
        }

        public String getAtrybutKlasowy() {
            this.atrKlasowyAtak = 0;
            this.atrKlasowyObrona = 0;
            this.atrKlasowySzybkosc = 0;
            this.atrKlasowyHp = 0;
            this.atrKlasowyMoc = 0;
            this.atrKlasowyWiedza = 0;
            
            switch (gs.getBohaterZaznaczony().getKlasyPostaci()) {
                case Berserk:
                    atrKlasowyAtak += 1;
                    return "Atak + 1";
                case Czarodziej:
                    atrKlasowyMoc += 1;
                    return "Moc + 1";
                case Lowca:
                    atrKlasowySzybkosc += 1;
                    return "Szybkosc + 1";
                case Obronca:
                    atrKlasowyObrona += 1;
                    return "Obrona + 1";
                case Twierdza:
                    atrKlasowyHp += 1;
                    return "HP + 1";
            }
            return null;
        }
        
        public void zakonczAwans(GameStatus gs){
            Bohater b = gs.getBohaterZaznaczony();
            
            b.setAtak(b.getAtak() + this.tmpAtak + this.atrKlasowyAtak);
            b.setObrona(b.getObrona()+ this.tmpObrona + this.atrKlasowyObrona);
            b.setSzybkosc(b.getSzybkosc()+ this.tmpSzybkosc + this.atrKlasowySzybkosc);
            b.setHp(b.getHp()+ this.tmpHp + this.atrKlasowyHp);
            b.setMoc(b.getMoc()+ this.tmpMoc + this.atrKlasowyMoc);
            b.setWiedza(b.getWiedza()+ this.tmpWiedza + this.atrKlasowyWiedza);
            
            //Ustala poziom many na poziomie nowej wiedzy.
            b.setMana(b.getWiedza());
            b.setActualMana(b.getWiedza());
            
            b.setExpToNextLevel(b.getExp() + 2 * b.getExp());
        }
    }
}
