package com.mygdx.burghs;

import com.mygdx.burghs.Screens.OptionsScreen;
import com.mygdx.burghs.Screens.MapScreen;
import com.mygdx.burghs.Screens.BohaterScreen;
import com.badlogic.gdx.Game;
import com.mygdx.burghs.Screens.AwansScreen;
import com.mygdx.burghs.Screens.ItemScreen;
import com.mygdx.burghs.Screens.NewBohaterScreen;
import com.mygdx.burghs.Screens.NewGameScreen;
import com.mygdx.burghs.Testing.MapEditor;
import com.mygdx.burghs.Testing.TestingScreen;

public class Burghs extends Game {

    private final GameStatus gs = new GameStatus();
    private Assets a;

    private Main mainScreen;
    private MapScreen mapScreen;
    //private NewGameScreenOld newGameScreen;
    private NewGameScreen newGameScreen;
    private OptionsScreen optionsScreen;
    private BohaterScreen bohaterScreen;
    private ItemScreen itemScreen;
    private TestingScreen testScreen;
    private NewBohaterScreen newBohaterScreen;
    private MapEditor mapEditor;
    private AwansScreen awansScreen;
    // zmienna informująca czy ma zostać uruchomione tworzenie nowej mapy
    private final boolean nieWywolujTworzeniaMapy = false;

    @Override
    public void create() {
        a = new Assets();
        mainScreen = new Main(this, a, gs);
        newGameScreen = new NewGameScreen(this, this.a, this.gs);
        optionsScreen = new OptionsScreen(this.gs, this.a);
        bohaterScreen = new BohaterScreen(this, this.a, this.gs);
        itemScreen = new ItemScreen(this.a, this.gs, this);
        testScreen = new TestingScreen(this, this.a, this.gs);
        newBohaterScreen = new NewBohaterScreen(this, this.gs, this.a, Assets.stage01MapScreen);
        mapEditor = new MapEditor(this, this.a);
        awansScreen = new AwansScreen(this, this.a, this.gs);
        
        Assets.testScreen = testScreen;
        Assets.mainMenuScreen = mainScreen;
        Assets.newGameScreen = newGameScreen;
        Assets.mapScreen = mapScreen;
        Assets.bohaterScreen = bohaterScreen;
        Assets.itemScreen = itemScreen;
        Assets.newBohaterScreen = newBohaterScreen;
        Assets.mapEditor = mapEditor;
        Assets.awansScreen = awansScreen;
        
        this.setScreen(mainScreen);
    }

    @Override
    public void render() {
        super.render();                  
    }
}
