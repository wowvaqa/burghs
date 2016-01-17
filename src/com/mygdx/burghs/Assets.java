package com.mygdx.burghs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import enums.CzesciCiala;
import java.util.ArrayList;

public class Assets {

    // Tekstury terenu
    public Texture trawaDrzewoTex;
    public Texture trawaTex;
    public Texture trawaGoraTex;
    public Texture trawaZamekTex;

    public Texture btnGoTex, btnAttackTex;
    public Texture btnPlus, btnMinus, btnOK, btnRight, btnLeft;
    public Texture mobElfTex, mobElfTexZaznaczony;
    public Texture mobOrkTex, mobOrkTexZaznaczony;
    public Texture mobDwarfTex, mobDwarfTexZaznaczony;
    public Texture mobHumanTex, mobHumanTexZaznaczony;
    public Texture mobWizardTex, mobWizardTexZaznaczony;
    public Texture cancelTex;
    public Skin skin;

    // obiekty na mapie
    public Texture texTresureBox;

    // tekstury mobów
    public Texture texSzkieletMob;
    public Texture texWilkMob;

    // tekstury itemków
    public Texture texHead;
    public Texture texLinenTousers;
    public Texture texLinenShoes;
    public Texture texLeatherShoes;
    public Texture texLinenShirt;
    public Texture texLinenCap;
    public Texture texLeatherCap;
    public Texture texLeatherTousers;
    public Texture texLinenGloves;
    public Texture texFist;
    public Texture texStick;
    public Texture texLegs;
    public Texture texSword;
    public Texture texShield;
    public Texture texBow;
    public Texture texGold;

    // Potions
    public Texture texHelthPotion;
    public Texture texSpeedPotion;
    public Texture texAttackPotion;
    public Texture texDefencePotion;

    // interfejs
    public Texture moveIcon;
    public Texture cancelIcon;
    public Texture attackIcon;

    public AssetManager am;

//    public ButtonActor btnCancel;
    public Label lblDmg;

    // predefiniowane okno ifnoramcyjne
    private Window infoWindow;

    public int[] mapa = new int[100];

    public static Stage stage01MapScreen;
    public static Stage stage02MapScreen;

    public static Screen testScreen;
    public static Screen mainMenuScreen;
    public static Screen newGameScreen;
    public static Screen mapScreen;
    public static Screen bohaterScreen;
    public static Screen itemScreen;
    public static Screen lastScreen;
    public static Screen newBohaterScreen;
    public static Screen mapEditor;

    public Assets() {
        trawaDrzewoTex = new Texture("grassTree100x100.png");
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
        mobWizardTex = new Texture("mobWizardTex.png");
        mobWizardTexZaznaczony = new Texture("mobWizardTexZaznaczony.png");

        cancelTex = new Texture("cancelBtt.png");

        texTresureBox = new Texture("texTresureBox2.png");

        skin = new Skin(Gdx.files.internal("styles/uiskin.json"));

        lblDmg = new Label("null", skin);

        moveIcon = new Texture("interface/texMoveIcon.png");
        cancelIcon = new Texture("interface/texCancelIcon.png");
        attackIcon = new Texture("interface/texAtakIcon.png");

        utworzItemki();

        utworzMoby();

        wypelnijMape();
        utworzInfoWindow();

    }

    /**
     * Funkcja animuje Labelkę od wyświetlania obrażeń
     *
     * @param pozX pozycja x gdzie ma być ustawiona labelka
     * @param pozY pozycja y gdzie ma być ustawiona labelka
     * @param bohaterAtakujacy referencja do obiketu bohatera atakującego
     * @param bohaterBroniacy referencja do obiektu bohatera broniącego
     */
    public void animujLblDmg(float pozX, float pozY, Bohater bohaterAtakujacy, Bohater bohaterBroniacy) {
        lblDmg.setText("Dmg: " + Integer.toString(Fight.getObrazenia(bohaterAtakujacy, bohaterBroniacy)));
        lblDmg.setPosition(pozX - 50, pozY - 25);
        lblDmg.setFontScale(1.5f);
        lblDmg.addAction(Actions.alpha(1));
        lblDmg.addAction(Actions.fadeOut(2.0f));
        lblDmg.addAction(Actions.moveBy(0, 175, 2.0f));
        lblDmg.act(Gdx.graphics.getDeltaTime());
    }

    public void animujLblDmg(float pozX, float pozY, Bohater bohaterAtakujacy, Castle castle) {
        lblDmg.setText("Dmg: " + Integer.toString(Fight.getObrazenia(bohaterAtakujacy, castle)));
        lblDmg.setPosition(pozX - 50, pozY - 25);
        lblDmg.setFontScale(1.5f);
        lblDmg.addAction(Actions.alpha(1));
        lblDmg.addAction(Actions.fadeOut(2.0f));
        lblDmg.addAction(Actions.moveBy(0, 175, 2.0f));
        lblDmg.act(Gdx.graphics.getDeltaTime());
    }

    public void animujLblDmg(float pozX, float pozY, Mob mob, Bohater bohaterBroniacy) {
        lblDmg.setText("Dmg: " + Integer.toString(Fight.getObrazenia(mob, bohaterBroniacy)));
        lblDmg.setPosition(pozX - 50, pozY - 25);
        lblDmg.setFontScale(1.5f);
        lblDmg.addAction(Actions.alpha(1));
        lblDmg.addAction(Actions.fadeOut(2.0f));
        lblDmg.addAction(Actions.moveBy(0, 175, 2.0f));
        lblDmg.act(Gdx.graphics.getDeltaTime());
    }

    /**
     *
     * @param pozX
     * @param pozY
     * @param bohaterAtakujacy
     * @param mob
     */
    public void animujLblDmg(float pozX, float pozY, Bohater bohaterAtakujacy, Mob mob) {
        lblDmg.setText("Dmg: " + Integer.toString(Fight.getObrazenia(bohaterAtakujacy, mob)));
        lblDmg.setPosition(pozX - 50, pozY - 25);
        lblDmg.setFontScale(1.5f);
        lblDmg.addAction(Actions.alpha(1));
        lblDmg.addAction(Actions.fadeOut(2.0f));
        lblDmg.addAction(Actions.moveBy(0, 175, 2.0f));
        lblDmg.act(Gdx.graphics.getDeltaTime());
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
     * @param gs
     */
    public void pokazInfoWindow(final TresureBox tresureBox, final Bohater bohater, final GameStatus gs) {
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
                            // Sprawdzenie czy itemek jest złotem
                            if (tresureBox.getDostepneItemy().get(i).getCzescCiala().equals(CzesciCiala.gold)) {
                                gs.dodajDoZlotaAktualnegoGracza(tresureBox.getDostepneItemy().get(i).getGold());
                                tresureBox.getDostepneItemy().remove(i);
                                tmpButtons.get(i).remove();
                                ukryjInfoWindow();
                                pokazInfoWindow(tresureBox, bohater, gs);
                                // Jeżeli nie jest złotem
                            } else {
                                tmpButtons.get(i).remove();
                                // dodanie itemka z tresureboxa do ekwipunku
                                bohater.getEquipment().add(tresureBox.getDostepneItemy().get(i));
                                // usuniecie wybranego itemka z trasureboxa
                                tresureBox.getDostepneItemy().remove(i);
                                // aktualizacja okna
                                ukryjInfoWindow();
                                pokazInfoWindow(tresureBox, bohater, gs);
                            }
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
                // Usuwa Tresure Boxa z Stage01
                tresureBox.remove();

                // Usuwa TresureBoxa z mapy
                for (int i = 0; i < gs.getMapa().getIloscPolX(); i ++){
                    for (int j = 0; j < gs.getMapa().getIloscPolY(); j ++){
                        if (gs.getMapa().getPola()[i][j].getTresureBox() != null){
                            if (gs.getMapa().getPola()[i][j].getTresureBox().equals(tresureBox)){
                                gs.getMapa().getPola()[i][j].setTresureBox(null);
                            }
                        }
                    }
                }
                
                bohater.setOtwartaSkrzyniaZeSkarbem(false);
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

    private void utworzItemki() {
        texHead = new Texture("items/texHead.png");
        texLinenGloves = new Texture("items/texLinenGloves.png");
        texLinenCap = new Texture("items/texLinenCap.png");
        texLinenShirt = new Texture("items/texLinenShirt.png");
        texLinenShoes = new Texture("items/texLinenShoes.png");
        texLinenTousers = new Texture("items/texLinenTrousers.png");
        texLeatherCap = new Texture("items/texLeatherCap.png");
        texLeatherTousers = new Texture("items/texLeatherTrousers.png");
        texLeatherShoes = new Texture("items/texLeatherShoes.png");
        texFist = new Texture("items/texFist.png");
        texStick = new Texture("items/texStick.png");
        texLegs = new Texture("items/texLegs.png");
        texSword = new Texture("items/texSword.png");
        texShield = new Texture("items/texShield.png");
        texGold = new Texture("items/texGold.png");
        texBow = new Texture("items/texBow.png");
        texHelthPotion = new Texture("items/texHealthPotion.png");
        texSpeedPotion = new Texture("items/texSpeedPotion.png");
        texAttackPotion = new Texture("items/texAttackPotion.png");
        texDefencePotion = new Texture("items/texDefencePotion.png");
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

    private void utworzMoby() {
        this.texWilkMob = new Texture("moby/mobWolfTex.png");
        this.texSzkieletMob = new Texture("moby/mobSzkieletfTex.png");
    }
}
