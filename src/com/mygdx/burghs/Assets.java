package com.mygdx.burghs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

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
    public Texture texLinenShirt;
    public Texture texLinenCap;
    public Texture texLeatherCap;
    public Texture texLinenGloves;
    public Texture texFist;
    
    //public Sprite sprLeatherCap;
    
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
    
    public ButtonActor btnCancel;

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
    }
    
    private void utworzPrzyciskiAtaku(){
        btnAtcNorth = new ButtonActor(this.btnAttackTex, 0, 0);
        btnAtcNorth.setVisible(false);
        btnAtcNorth.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnAtcNorth.setKlikniety(true);
            }            
        });
        
        btnAtcSouth = new ButtonActor(this.btnAttackTex, 0, 0);
        btnAtcSouth.setVisible(false);
        btnAtcSouth.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnAtcSouth.setKlikniety(true);
            }            
        });
        
        btnAtcEast = new ButtonActor(this.btnAttackTex, 0, 0);
        btnAtcEast.setVisible(false);
        btnAtcEast.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnAtcEast.setKlikniety(true);
            }            
        });
        
        btnAtcWest = new ButtonActor(this.btnAttackTex, 0, 0);
        btnAtcWest.setVisible(false);
        btnAtcWest.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnAtcWest.setKlikniety(true);
            }            
        });
        
        btnAtcNorthEast = new ButtonActor(this.btnAttackTex, 0, 0);
        btnAtcNorthEast.setVisible(false);
        btnAtcNorthEast.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnAtcNorthEast.setKlikniety(true);
            }            
        });
        
        btnAtcNorthWest = new ButtonActor(this.btnAttackTex, 0, 0);
        btnAtcNorthWest.setVisible(false);
        btnAtcNorthWest.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnAtcNorthWest.setKlikniety(true);
            }            
        });
        
        btnAtcSouthEast = new ButtonActor(this.btnAttackTex, 0, 0);
        btnAtcSouthEast.setVisible(false);
        btnAtcSouthEast.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnAtcSouthEast.setKlikniety(true);
            }            
        });
        
        btnAtcSouthWest = new ButtonActor(this.btnAttackTex, 0, 0);
        btnAtcSouthWest.setVisible(false);
        btnAtcSouthWest.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnAtcSouthWest.setKlikniety(true);
            }            
        });
    }
    
    /** 1. Tworzy przycisk ruchu po kliknięciu na bohatera
     *  2. Ustawia widzialność przycisku na FALSE
     *  3. Dodaje ClickListnera do przycisku, który po kliknięciu
     */    
    private void utworzPrzyciskiRuchu(){
        btnNorth = new ButtonActor(this.btnGoTex, 0, 0);
        btnNorth.setVisible(false);
        btnNorth.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnNorth.setKlikniety(true);
            }            
        });
        
        btnSouth = new ButtonActor(this.btnGoTex, 0, 0);
        btnSouth.setVisible(false);
        btnSouth.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnSouth.setKlikniety(true);
            }            
        });
        
        btnEast = new ButtonActor(this.btnGoTex, 0, 0);
        btnEast.setVisible(false);
        btnEast.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnEast.setKlikniety(true);
            }            
        });
        
        btnWest = new ButtonActor(this.btnGoTex, 0, 0);
        btnWest.setVisible(false);
        btnWest.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnWest.setKlikniety(true);
            }            
        });
        
        btnNorthEast = new ButtonActor(this.btnGoTex, 0, 0);
        btnNorthEast.setVisible(false);
        btnNorthEast.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnNorthEast.setKlikniety(true);
            }            
        });
        
        btnNorthWest = new ButtonActor(this.btnGoTex, 0, 0);
        btnNorthWest.setVisible(false);
        btnNorthWest.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnNorthWest.setKlikniety(true);
            }            
        });
        
        btnSouthEast = new ButtonActor(this.btnGoTex, 0, 0);
        btnSouthEast.setVisible(false);
        btnSouthEast.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnSouthEast.setKlikniety(true);
            }            
        });
        
        btnSouthWest = new ButtonActor(this.btnGoTex, 0, 0);
        btnSouthWest.setVisible(false);
        btnSouthWest.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnSouthWest.setKlikniety(true);
            }            
        });
        
        btnCancel = new ButtonActor(this.cancelTex, 0, 0);
        btnCancel.setVisible(false);
        btnCancel.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnCancel.setKlikniety(true);
            }            
        });
        
    }
    
    private void utworzItemki(){
        texLinenGloves = new Texture("items/texLinenGloves.png");
        texLinenCap = new Texture("items/texLinenCap.png");
        texLinenShirt = new Texture("items/texLinenShirt.png");
        texLinenShoes = new Texture("items/texLinenShoes.png");
        texLinenTousers = new Texture("items/texLinenTrousers.png");        
        texLeatherCap = new Texture("items/texLeatherCap.png");
        texFist = new Texture("items/texFist.png");
        //sprLeatherCap = new Sprite(texLeatherCap);
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

}
