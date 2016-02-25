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
import com.mygdx.burghs.DefaultActor;
import com.mygdx.burghs.GameStatus;
import enums.Spells;
import java.util.ArrayList;

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
                        awans.czyscWyborKlasy();
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
        Label lblPoziom = new Label("Poziom " + (gs.getBohaterZaznaczony().getLevelOfExp() + 1), a.skin);
        mainTable.add(lblPoziom).expandX().pad(5).colspan(10);
        mainTable.row();
        mainTable.add(leftTable);
        mainTable.add(rightTable).expand().colspan(10);
        mainTable.row();
        mainTable.add(btnExit).expandX().pad(5).colspan(10).size(100, 50);
    }

    private void formatLeftTable() {
        leftTable.pad(10);
        leftTable.setDebug(true);

        Label lblPoziom1 = new Label("1", a.skin);
        Label lblPoziom2 = new Label("2", a.skin);
        Label lblPoziom3A = new Label("3A", a.skin);
        Label lblPoziom3B = new Label("3B", a.skin);
        Label lblPoziom4A = new Label("4A", a.skin);
        Label lblPoziom4B = new Label("4B", a.skin);

        definiujNazwy(lblPoziom1, lblPoziom2, lblPoziom3A, lblPoziom3B, lblPoziom4A, lblPoziom4B);

        TextButton btnPoziom2 = new TextButton("WYBIERZ", a.skin);
        TextButton btnPoziom3A = new TextButton("WYBIERZ", a.skin);
        TextButton btnPoziom3B = new TextButton("WYBIERZ", a.skin);
        TextButton btnPoziom4A = new TextButton("WYBIERZ", a.skin);
        TextButton btnPoziom4B = new TextButton("WYBIERZ", a.skin);

        btnPoziom2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                awans.czyscWyborKlasy();
                awans.wybor2 = true;
                reformatujTabele();
            }
        });

        btnPoziom3A.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                awans.czyscWyborKlasy();
                awans.wybor3A = true;
                reformatujTabele();
            }
        });

        btnPoziom3B.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                awans.czyscWyborKlasy();
                awans.wybor3B = true;
                reformatujTabele();
            }
        });

        btnPoziom4A.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                awans.czyscWyborKlasy();
                awans.wybor4A = true;
                reformatujTabele();
            }
        });

        btnPoziom4B.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                awans.czyscWyborKlasy();
                awans.wybor4B = true;
                reformatujTabele();
            }
        });

        switch (gs.getBohaterZaznaczony().getLevelOfExp()) {
            case 1:
                btnPoziom3A.setVisible(false);
                btnPoziom3B.setVisible(false);
                btnPoziom4A.setVisible(false);
                btnPoziom4B.setVisible(false);
                break;
            case 2:
                btnPoziom2.setVisible(false);
                btnPoziom4A.setVisible(false);
                btnPoziom4B.setVisible(false);
                break;
            case 3:
                btnPoziom2.setVisible(false);
                btnPoziom3A.setVisible(false);
                btnPoziom3B.setVisible(false);
                break;
        }

        leftTable.add(new DefaultActor(a.texWarrior0, 0, 0)).pad(1).colspan(2);
        leftTable.row();
        leftTable.add(lblPoziom1).pad(5).colspan(2);
        leftTable.row();
        leftTable.add(new DefaultActor(a.texWarrior1, 0, 0)).pad(1).colspan(2);
        leftTable.row();
        leftTable.add(lblPoziom2).pad(5).colspan(2);
        leftTable.row();
        leftTable.add(btnPoziom2).pad(5).colspan(2);
        leftTable.row();
        leftTable.add(new DefaultActor(a.texWarrior1, 0, 0)).pad(1).colspan(2);
        leftTable.add(new DefaultActor(a.texWarrior1, 0, 0)).pad(1).colspan(2);
        leftTable.row();
        leftTable.add(lblPoziom3A).pad(5);
        leftTable.add(lblPoziom3B).pad(5);
        leftTable.row();
        //leftTable.row();
        leftTable.add(btnPoziom3A).pad(5);
        leftTable.add(btnPoziom3B).pad(5);
        leftTable.row();
        leftTable.add(new DefaultActor(a.texWarrior1, 0, 0)).pad(1).colspan(2);
        leftTable.add(new DefaultActor(a.texWarrior1, 0, 0)).pad(1).colspan(2);
        leftTable.row();
        leftTable.add(lblPoziom4A).pad(5);
        leftTable.add(lblPoziom4B).pad(5);
        leftTable.row();
        //leftTable.row();
        leftTable.add(btnPoziom4A).pad(5);
        leftTable.add(btnPoziom4B).pad(5);
        leftTable.row();
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
        Label lblmoc2 = new Label("Moc: " + (gs.getBohaterZaznaczony().getMoc() + awans.tmpMoc + awans.atrKlasowyMoc), a.skin);
        Label lblwie2 = new Label("Wiedza: " + (gs.getBohaterZaznaczony().getWiedza() + awans.tmpWiedza + awans.atrKlasowyWiedza), a.skin);

        rightTable.add(lblOpis2).colspan(10).align(Align.center);
        rightTable.row();
        rightTable.add(lblatc2).pad(5);
        rightTable.add(lblobr2).pad(5);
        rightTable.add(lblszb2).pad(5);
        rightTable.add(lblhpp2).pad(5);
        rightTable.add(lblmoc2).pad(5);
        rightTable.add(lblwie2).pad(5);
        rightTable.row();

        Label lblNewSpells = new Label("Nowe Czary: ", a.skin);
        rightTable.add(lblNewSpells).pad(5).colspan(10);
        rightTable.row();

        for (Spells listaCzarow : awans.listaCzarow) {
            rightTable.add(ikoneNowegoCzaru()).pad(2).colspan(10);
        }
        rightTable.row();

        for (Spells listaCzarow : awans.listaCzarow) {
            rightTable.add(new Label(listaCzarow.toString(), a.skin)).pad(2).colspan(10);
        }
        rightTable.row();
    }

    /**
     * Zwraca ikonę nowego czaru.
     *
     * @return Default Actor
     */
    private DefaultActor ikoneNowegoCzaru() {
        for (Spells spls : awans.listaCzarow) {
            switch (spls) {
                case SongOfGlory:
                    return new DefaultActor(a.texSpellSongOfGlory, 0, 0);
                case Discouragement:
                    return new DefaultActor(a.texSpellDiscouragement, 0, 0);
                case Fury:
                    return new DefaultActor(a.texSpellFury, 0, 0);
                case Charge:
                    return new DefaultActor(a.texSpellCharge, 0, 0);
                case FinalJudgment:
                    return new DefaultActor(a.texSpellFinalJudgment, 0, 0);
            }
        }
        return null;
    }

    /**
     * Formatuje wszystkie tabele.
     */
    private void reformatujTabele() {
        awans.uzupelnijListyCzarow();
        mainTable.clear();
        rightTable.clear();
        leftTable.clear();
        formatMainTable();
    }

    /**
     * Definiuje jakie klasy bohaterów znajdą się na labelach
     *
     * @param l1
     * @param l2
     * @param l3
     * @param l4
     * @param l5
     * @param l6
     */
    private void definiujNazwy(Label l1, Label l2, Label l3, Label l4, Label l5, Label l6) {
        switch (gs.getBohaterZaznaczony().klasyPostaci) {
            case Wojownik:
                l1.setText("Wojownik");
                l2.setText("Paladyn");
                l3.setText("Arcypaladyn");
                l4.setText("Msciciel");
                l5.setText("Pogromca");
                l6.setText("Sedzia");
                break;
            case Giermek:
                l1.setText("Giermek");
                l2.setText("Rycerz");
                l3.setText("Hierofanta");
                l4.setText("Golem");
                l5.setText("Inkwizytor");
                l6.setText("Diamentowy golem");
                break;
        }
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

        public boolean wybor2 = false;
        public boolean wybor3A = false;
        public boolean wybor3B = false;
        public boolean wybor4A = false;
        public boolean wybor4B = false;

        private ArrayList<Spells> listaCzarow;

        public Awans() {
            listaCzarow = new ArrayList<Spells>();
        }

        /**
         * Czyści wybór klasy.
         */
        public void czyscWyborKlasy() {
            listaCzarow.clear();
            wybor2 = false;
            wybor3A = false;
            wybor3B = false;
            wybor4A = false;
            wybor4B = false;
        }

        /**
         * Uzupełnia listy czarów dla klas bohaterów
         */
        public void uzupelnijListyCzarow() {

            listaCzarow.clear();

            switch (gs.getBohaterZaznaczony().getKlasyPostaci()) {
                case Wojownik:
                    if (wybor2) {
                        listaCzarow.add(Spells.SongOfGlory);
                    } else if (wybor3A) {
                        listaCzarow.add(Spells.Charge);
                    } else if (wybor3B) {
                        listaCzarow.add(Spells.Discouragement);
                    } else if (wybor4A) {
                        listaCzarow.add(Spells.Fury);
                    } else if (wybor4B) {
                        listaCzarow.add(Spells.FinalJudgment);
                    }
                    break;
            }
        }

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
            tmpHp += 2;
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
                case Wojownik:
                    atrKlasowyAtak += 1;
                    return "Atak + 1";
                case Czarodziej:
                    atrKlasowyMoc += 1;
                    return "Moc + 1";
                case Lowca:
                    atrKlasowySzybkosc += 1;
                    return "Szybkosc + 1";
                case Giermek:
                    atrKlasowyObrona += 1;
                    return "Obrona + 1";
                case Twierdza:
                    atrKlasowyHp += 4;
                    return "HP + 4";
            }
            return null;
        }

        public void zakonczAwans(GameStatus gs) {
            Bohater b = gs.getBohaterZaznaczony();

            b.setAtak(b.getAtak() + this.tmpAtak + this.atrKlasowyAtak);
            b.setObrona(b.getObrona() + this.tmpObrona + this.atrKlasowyObrona);
            b.setSzybkosc(b.getSzybkosc() + this.tmpSzybkosc + this.atrKlasowySzybkosc);
            b.setHp(b.getHp() + this.tmpHp + this.atrKlasowyHp);
            b.setMoc(b.getMoc() + this.tmpMoc + this.atrKlasowyMoc);
            b.setWiedza(b.getWiedza() + this.tmpWiedza + this.atrKlasowyWiedza);

            //Ustala poziom many na poziomie nowej wiedzy.
            b.setMana(b.getWiedza());
            b.setActualMana(b.getWiedza());

            b.setLevelOfExp(b.getLevelOfExp() + 1);

            // Oryginalne ustawienie osiągnięcia następnego poziomu. NIE KASOWAĆ
            //b.setExpToNextLevel(b.getExp() + 2 * b.getExp());
            b.setExpToNextLevel(b.getExp() + 100);

            for (Spells listaCzarow1 : awans.listaCzarow) {
                gs.getBohaterZaznaczony().getListOfSpells().add(listaCzarow1);
            }
        }

        /**
         * Zwraca listę czarów dostępnych dla klasy.
         *
         * @return
         */
        public ArrayList<Spells> getListaCzarow() {
            return listaCzarow;
        }

        /**
         * Ustala listę czarów dostępnych dla klasy.
         *
         * @param listaCzarow
         */
        public void setListaCzarow(ArrayList<Spells> listaCzarow) {
            this.listaCzarow = listaCzarow;
        }
    }
}
