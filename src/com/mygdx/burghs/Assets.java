package com.mygdx.burghs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.util.ArrayList;

public class Assets {

    public Texture trawaTex, btnGoTex, btnAttackTex;
    public Texture btnPlus, btnMinus, btnOK, btnRight, btnLeft;
    public Texture trawaGoraTex, trawaZamekTex;
    public Texture mobElfTex, mobElfTexZaznaczony;
    public Texture mobOrkTex, mobOrkTexZaznaczony;
    public Texture mobDwarfTex, mobDwarfTexZaznaczony;
    public Texture mobHumanTex, mobHumanTexZaznaczony;
    public Texture cancelTex;
    public Skin skin;

    // obiekty na mapie
    public Texture texTresureBox;

    // tekstury itemków
    public Texture texLinenTousers;
    public Texture texLinenShoes;
    public Texture texLeatherShoes;
    public Texture texLinenShirt;
    public Texture texLinenCap;
    public Texture texLeatherCap;
    public Texture texLeatherTousers;
    public Texture texLinenGloves;
    public Texture texFist;

    // predefiniowane przyciski ruchu
    public ButtonActor btnNorth;
    public ButtonActor btnSouth;
    public ButtonActor btnEast;
    public ButtonActor btnWest;
    public ButtonActor btnNorthEast;
    public ButtonActor btnNorthWest;
    public ButtonActor btnSouthEast;
    public ButtonActor btnSouthWest;

    // predefiniowane przyciski ataku
    public ButtonActor btnAtcNorth;
    public ButtonActor btnAtcSouth;
    public ButtonActor btnAtcEast;
    public ButtonActor btnAtcWest;
    public ButtonActor btnAtcNorthEast;
    public ButtonActor btnAtcNorthWest;
    public ButtonActor btnAtcSouthEast;
    public ButtonActor btnAtcSouthWest;
    
    public AssetManager am;

    public ButtonActor btnCancel;

    // predefiniowane okno ifnoramcyjne
    private Window infoWindow;

    public int[] mapa = new int[100];

    public Assets() {        
        trawaTex = new Texture("grass100x100.png");
        trawaGoraTex = new Texture("grassMountain100x100.png");
        trawaZamekTex = new Texture("grassCastle100x100.png");
        btnGoTex = new Texture("goBtt.png");
        btnAttackTex = new Texture("attackBtt.png");
        btnPlus = new Texture("bttPlus.png");
        btnMinus = new Texture("bttMinus.png");
        btnOK = new Texture("btnOK.png");
        btnRight = new Texture("btnRight.png");
        btnLeft = new Texture("btnLeft.png");
        mobElfTex = new Texture("mobElfTex.png");
        mobElfTexZaznaczony = new Texture("mobElfTexZaznaczony.png");
        mobOrkTex = new Texture("mobOrkTex.png");
        mobOrkTexZaznaczony = new Texture("mobOrkTexZaznaczony.png");
        mobDwarfTex = new Texture("mobDwarfTex.png");
        mobDwarfTexZaznaczony = new Texture("mobDwarfTexZaznaczony.png");
        mobHumanTex = new Texture("mobHumanTex.png");
        mobHumanTexZaznaczony = new Texture("mobHumanTexZaznaczony.png");
        cancelTex = new Texture("cancelBtt.png");

        texTresureBox = new Texture("texTresureBox.png");

        skin = new Skin(Gdx.files.internal("styles/uiskin.json"));

        utworzPrzyciskiRuchu();
        utworzPrzyciskiAtaku();

        utworzItemki();

        wypelnijMape();
        utworzInfoWindow();

    }

    // utworzenie okna informacyjnego
    private void utworzInfoWindow() {
        infoWindow = new Window("TEST", skin);
        infoWindow.setPosition(Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2 - 150);
        infoWindow.setSize(400, 300);
        infoWindow.setVisible(false);
    }

    /**
     * Pokazuje okno z informacjami dotyczącymi charakterystyki itemka.
     *
     * @param nazwa Nazwa itemka
     * @param atak modyfikator ataku itemka
     * @param obrona modyfikator obrony itemka
     * @param hp modyfikator hp itemka
     * @param szybkosc modyfikator szybkości itemka
     */
    public void pokazInfoWindow(String nazwa, int atak, int obrona, int hp,
            int szybkosc) {
        System.out.println("Funkcja pokazInfoWindow jest uruchomiona");
        infoWindow.setZIndex(200);
        infoWindow.setVisible(true);
        infoWindow.add("Nazwa:");
        infoWindow.add(nazwa);
        infoWindow.row();
        infoWindow.add("Mod. Ataku: ");
        infoWindow.add(Integer.toString(atak));
        infoWindow.row();
        infoWindow.add("Mod. Obrony: ");
        infoWindow.add(Integer.toString(obrona));
        infoWindow.row();
        infoWindow.add("Mod. Hp: ");
        infoWindow.add(Integer.toString(hp));
        infoWindow.row();
        infoWindow.add("Mod. Szybkosci: ");
        infoWindow.add(Integer.toString(szybkosc));
        infoWindow.row();
        infoWindow.toFront();
    }

    /**
     * Pokazuje okno z informacjami dla Tresure Box
     *
     * @param tresureBox Referencja do obiektu TresureBox którego itemy mają być
     * wyświetlone w oknie informacyjnym
     * @param bohater Referencja do obiketu bohatera do którego ekwipunku 
     * dodawane będą itemki z tresure boxa
     */
    public void pokazInfoWindow(final TresureBox tresureBox, final Bohater bohater) {
        infoWindow.setZIndex(200);
        infoWindow.setVisible(true);

        // Tymczasowa ArrayLista przechowująca TextButtony
        final ArrayList<TextButton> tmpButtons = new ArrayList<TextButton>();

        for (int i = 0; i < tresureBox.getDostepneItemy().size(); i++) {
            infoWindow.add(tresureBox.getDostepneItemy().get(i).getNazwa());
            tmpButtons.add(new TextButton("TAKE IT", skin));
            tmpButtons.get(i).addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("przycisk TAKE IT kliknięty");
                    for (int i = 0; i < tmpButtons.size(); i++) {
                        if (tmpButtons.get(i).isPressed()) {
                            tmpButtons.get(i).remove();
                            // dodanie itemka z tresureboxa do ekwipunku
                            bohater.getEquipment().add(tresureBox.getDostepneItemy().get(i));                            
                            // usuniecie wybranego itemka z trasureboxa
                            tresureBox.getDostepneItemy().remove(i);
                            // aktualizacja okna
                            ukryjInfoWindow();
                            pokazInfoWindow(tresureBox, bohater);
                        }
                    }
                }
            });
            infoWindow.add(tmpButtons.get(i));
            infoWindow.row();
        }

        // tymczasowy przycisk Exit dodany do okna InfoWindow
        TextButton tmpExitBtn = new TextButton("EXIT", skin);
        tmpExitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ukryjInfoWindow();
            }
        });

        infoWindow.add(tmpExitBtn);
    }

    /**
     * Ukrywa okno informacyjne
     */
    public void ukryjInfoWindow() {
        infoWindow.setVisible(false);
        infoWindow.reset();
    }

    private void utworzPrzyciskiAtaku() {
        btnAtcNorth = new ButtonActor(this.btnAttackTex, 0, 0);
        btnAtcNorth.setVisible(false);
        btnAtcNorth.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnAtcNorth.setKlikniety(true);
            }
        });

        btnAtcSouth = new ButtonActor(this.btnAttackTex, 0, 0);
        btnAtcSouth.setVisible(false);
        btnAtcSouth.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnAtcSouth.setKlikniety(true);
            }
        });

        btnAtcEast = new ButtonActor(this.btnAttackTex, 0, 0);
        btnAtcEast.setVisible(false);
        btnAtcEast.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnAtcEast.setKlikniety(true);
            }
        });

        btnAtcWest = new ButtonActor(this.btnAttackTex, 0, 0);
        btnAtcWest.setVisible(false);
        btnAtcWest.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnAtcWest.setKlikniety(true);
            }
        });

        btnAtcNorthEast = new ButtonActor(this.btnAttackTex, 0, 0);
        btnAtcNorthEast.setVisible(false);
        btnAtcNorthEast.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnAtcNorthEast.setKlikniety(true);
            }
        });

        btnAtcNorthWest = new ButtonActor(this.btnAttackTex, 0, 0);
        btnAtcNorthWest.setVisible(false);
        btnAtcNorthWest.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnAtcNorthWest.setKlikniety(true);
            }
        });

        btnAtcSouthEast = new ButtonActor(this.btnAttackTex, 0, 0);
        btnAtcSouthEast.setVisible(false);
        btnAtcSouthEast.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnAtcSouthEast.setKlikniety(true);
            }
        });

        btnAtcSouthWest = new ButtonActor(this.btnAttackTex, 0, 0);
        btnAtcSouthWest.setVisible(false);
        btnAtcSouthWest.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnAtcSouthWest.setKlikniety(true);
            }
        });
    }

    /**
     * 1. Tworzy przycisk ruchu po kliknięciu na bohatera 2. Ustawia widzialność
     * przycisku na FALSE 3. Dodaje ClickListnera do przycisku, który po
     * kliknięciu
     */
    private void utworzPrzyciskiRuchu() {
        btnNorth = new ButtonActor(this.btnGoTex, 0, 0);
        btnNorth.setVisible(false);
        btnNorth.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnNorth.setKlikniety(true);
            }
        });

        btnSouth = new ButtonActor(this.btnGoTex, 0, 0);
        btnSouth.setVisible(false);
        btnSouth.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnSouth.setKlikniety(true);
            }
        });

        btnEast = new ButtonActor(this.btnGoTex, 0, 0);
        btnEast.setVisible(false);
        btnEast.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnEast.setKlikniety(true);
            }
        });

        btnWest = new ButtonActor(this.btnGoTex, 0, 0);
        btnWest.setVisible(false);
        btnWest.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnWest.setKlikniety(true);
            }
        });

        btnNorthEast = new ButtonActor(this.btnGoTex, 0, 0);
        btnNorthEast.setVisible(false);
        btnNorthEast.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnNorthEast.setKlikniety(true);
            }
        });

        btnNorthWest = new ButtonActor(this.btnGoTex, 0, 0);
        btnNorthWest.setVisible(false);
        btnNorthWest.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnNorthWest.setKlikniety(true);
            }
        });

        btnSouthEast = new ButtonActor(this.btnGoTex, 0, 0);
        btnSouthEast.setVisible(false);
        btnSouthEast.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnSouthEast.setKlikniety(true);
            }
        });

        btnSouthWest = new ButtonActor(this.btnGoTex, 0, 0);
        btnSouthWest.setVisible(false);
        btnSouthWest.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnSouthWest.setKlikniety(true);
            }
        });

        btnCancel = new ButtonActor(this.cancelTex, 0, 0);
        btnCancel.setVisible(false);
        btnCancel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnCancel.setKlikniety(true);
            }
        });

    }

    private void utworzItemki() {
        texLinenGloves = new Texture("items/texLinenGloves.png");
        texLinenCap = new Texture("items/texLinenCap.png");
        texLinenShirt = new Texture("items/texLinenShirt.png");
        texLinenShoes = new Texture("items/texLinenShoes.png");
        texLinenTousers = new Texture("items/texLinenTrousers.png");
        texLeatherCap = new Texture("items/texLeatherCap.png");
        texLeatherTousers = new Texture("items/texLeatherTrousers.png");
        texLeatherShoes = new Texture("items/texLeatherShoes.png");
        texFist = new Texture("items/texFist.png");
    }

    // wypełnia mapę 
    private void wypelnijMape() {
        // wypełnienie mapy trawą
        for (int i = 0; i < 100; i++) {
            mapa[i] = 1;
        }
        // dodanie zamków
        mapa[0] = 3;
        mapa[99] = 3;
        // dodanie gór
        mapa[5] = 2;
        mapa[6] = 2;
        mapa[18] = 2;
        mapa[19] = 2;
        mapa[43] = 2;
        mapa[66] = 2;
        mapa[67] = 2;
    }

    public Window getInfoWindow() {
        return infoWindow;
    }

    public void setInfoWindow(Window infoWindow) {
        this.infoWindow = infoWindow;
    }
}
