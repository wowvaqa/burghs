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
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Align;
import enums.CzesciCiala;
import enums.DostepneItemki;
import java.util.ArrayList;

/**
 * Wyświetla screen z statystykami oraz ekwipunkiem bohatera
 *
 * @author wow
 */
public class BohaterScreen implements Screen {

    // Informuje czy tabela jest zaktualizowana
    private boolean tabelaZaktualizowana = false;

    // Referencje do obiektu assetów, statusu gry, bohatera który jest kliknięty
    private final Assets a;

    private final GameStatus gs;

    // Plansza
    private final Stage stage01 = new Stage();

    // Przyciski
    private TextButton btnExit;

    // Inne
    // Referencje do itemków
    //private Item itemGlowa;
    // Labele
    private Label lblBohater;
    private Label lblAtak, lblObrona, lblHp, lblSzybkosc;
    private Label lblExp, lblExpToNextLevel, lblLevel;
    private Label lblStopy, lblNogi, lblLewaReka, lblPrawaReka, lblKorpus, lblGlowa;

    // Tabela
    private final Table tabela = new Table();
    private final Table tabela2 = new Table();

    public BohaterScreen(Assets a, GameStatus gs) {
        this.a = a;
        this.gs = gs;

        utworzPrzyciski();
        utworzLabele();
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
        tabela.add(sprawdzBohatera().getItemGlowa()).size(50, 50);
        tabela.row();

        tabela.add(lblLewaReka).align(Align.left);
        tabela.add(sprawdzBohatera().getItemLewaReka()).size(50, 50);

        tabela.add(lblPrawaReka).align(Align.left);
        tabela.add(sprawdzBohatera().getItemPrawaReka()).size(50, 50);
        tabela.row();

        tabela.add(lblKorpus).align(Align.left);
        tabela.add(sprawdzBohatera().getItemKorpus()).size(50, 50);

        tabela.add(lblNogi).align(Align.left);
        tabela.add(sprawdzBohatera().getItemNogi()).size(50, 50);
        tabela.row();

        tabela.add(lblStopy).align(Align.left);
        tabela.add(sprawdzBohatera().getItemStopy()).size(50, 50);
        tabela.row();

        tabela.add(tabela2).align(Align.topLeft).expand().colspan(tabela.getColumns());

        tabela.row();

        tabela.add(btnExit).expand().align(Align.bottom).width(100).height(50).colspan(tabela.getColumns());
    }

    /**
     * Formatuje tabele ekwipunku
     */
    private void formatujTabele2() {
        //tabela2.setFillParent(true);
        // ustawia odstęp od krawędzi tabeli
        tabela2.pad(10);
        // włacza linie debugujące tabelę
        tabela2.setDebug(true);

        tabela2.add(new Label("Ekwipunek:", a.skin)).align(Align.topLeft);
        tabela2.row();

        final ArrayList<TextButton> tmpButtonsUsun = new ArrayList<TextButton>();
        final ArrayList<TextButton> tmpButtonsZaloz = new ArrayList<TextButton>();

        for (int i = 0; i < sprawdzBohatera().getEquipment().size(); i++) {
            tabela2.add(new Label(sprawdzBohatera().getEquipment().get(i).getNazwa(), a.skin)).align(Align.left).pad(5);
            tabela2.add(sprawdzBohatera().getEquipment().get(i)).size(50);
            tmpButtonsUsun.add(new TextButton("Usun" + i, a.skin));
            tmpButtonsZaloz.add(new TextButton("Zaloz" + i, a.skin));

            tmpButtonsUsun.get(i).addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("przycisk Unus klikniety");
                    for (int i = 0; i < tmpButtonsUsun.size(); i++) {
                        if (tmpButtonsUsun.get(i).isPressed()) {

                            // usuwa przycisk z tabeli
                            tmpButtonsUsun.get(i).remove();
                            tmpButtonsZaloz.get(i).remove();
                            // usuwa itemka z equipmentu
                            sprawdzBohatera().getEquipment().remove(i);
                            // resetuje tabele
                            tabela2.reset();
                            formatujTabele2();
                            break;
                        }
                    }
                }
            });

            tmpButtonsZaloz.get(i).addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("przycisk zaloz klikniety");
                    for (int i = 0; i < tmpButtonsZaloz.size(); i++) {
                        if (tmpButtonsZaloz.get(i).isPressed()) {
                            int tmpI = i;

                            // usuwa przycisk z tabeli
                            tmpButtonsUsun.get(i).remove();
                            //tmpButtonsZaloz.get(i).remove();
                            tmpButtonsZaloz.remove(i);
                            // usuwa itemka z equipmentu
                            tabelaZaktualizowana = false;

                            podmianaItemkow(i);
                            System.out.println("przycisk: " + tmpI);

                            // resetuje tabele
                            tabela.clear();
                            tabela2.clear();
                            aktualizujTabele();
                            tmpButtonsZaloz.clear();
                        }
                    }
                }
            });

            tabela2.add(tmpButtonsUsun.get(i)).pad(5);
            tabela2.add(tmpButtonsZaloz.get(i)).pad(5);
            tabela2.row();
        }
    }

    /**
     * Podmienia tiemka między ekwipunkiem a wyposażeniem bohatera
     *
     * @param i
     */
    private void podmianaItemkow(int i) {
        Item tmpItem;
        if (sprawdzBohatera().getEquipment().get(i).getCzescCiala().equals(CzesciCiala.glowa)) {
            tmpItem = sprawdzBohatera().getItemGlowa();
            sprawdzBohatera().setItemGlowa(sprawdzBohatera().getEquipment().get(i));
            sprawdzBohatera().getEquipment().remove(i);
            if (!"Gola Glowa".equals(tmpItem.getNazwa())) {
                sprawdzBohatera().getEquipment().add(tmpItem);
            }
        } else if (sprawdzBohatera().getEquipment().get(i).getCzescCiala().equals(CzesciCiala.nogi)) {
            tmpItem = sprawdzBohatera().getItemNogi();
            sprawdzBohatera().setItemNogi(sprawdzBohatera().getEquipment().get(i));
            sprawdzBohatera().getEquipment().remove(i);
            sprawdzBohatera().getEquipment().add(tmpItem);
        } else if (sprawdzBohatera().getEquipment().get(i).getCzescCiala().equals(CzesciCiala.stopy)) {
            tmpItem = sprawdzBohatera().getItemStopy();
            sprawdzBohatera().setItemStopy(sprawdzBohatera().getEquipment().get(i));
            sprawdzBohatera().getEquipment().remove(i);
            sprawdzBohatera().getEquipment().add(tmpItem);
        }
    }

    /**
     * Tworzy labele
     */
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

    /**
     * Tworzy przyciski
     */
    private void utworzPrzyciski() {
        btnExit = new TextButton("EXIT", a.skin);
        btnExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gs.setActualScreen(1);
                tabela.clear();
                tabela2.clear();
                tabelaZaktualizowana = false;
                System.out.println("Exit klikniety");
            }
        });
    }

    /**
     * Dodaje aktorów do stage 01
     */
    private void dodajDoStage01() {
        stage01.addActor(tabela);
        this.stage01.addActor(a.getInfoWindow());
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
    private void aktualizujTabele() {

        formatujTabele();
        formatujTabele2();

        lblAtak.setText("Atak: " + sprawdzBohatera().getAtak() + " (" + sumujAtak() + ")");
        lblObrona.setText("Obrona: " + sprawdzBohatera().getObrona() + " (" + sumujObrone() + ")");
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

        tabelaZaktualizowana = true;
    }

    // Sumuje siłę obrony bohatera razem z obroną wszystkich itemków
    private int sumujObrone() {
        int suma = 0;
        suma += sprawdzBohatera().getAtak();
        suma += sprawdzBohatera().getItemGlowa().getObrona();
        return suma;
    }

    // Sumuje siłę ataku bohatera razem z atakiem wszystkich itemków
    private int sumujAtak() {
        return 0;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage01);

        if (gs.isCzyZaznaczonoBohatera()) {
            if (!tabelaZaktualizowana) {
                aktualizujTabele();
            }
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
