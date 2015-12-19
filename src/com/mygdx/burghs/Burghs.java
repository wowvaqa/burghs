package com.mygdx.burghs;

// SCENA 0  - Główne Menu
// SCENA 1  - Mapa
// SCENA 3  - Nowa Gra
// SCENA 4  - Opcje
// SCENA 5  - Screen Bohatera
// SCENA 6  - Item Screen
import com.badlogic.gdx.Game;
import com.mygdx.burghs.Screens.ItemScreen;
import com.mygdx.burghs.Screens.NewGameScreen;
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
    // zmienna informująca czy ma zostać uruchomione tworzenie nowej mapy
    private boolean nieWywolujTworzeniaMapy = false;

    @Override
    public void create() {
        a = new Assets();
        mainScreen = new Main(this, a, gs);
        //mapScreen = new MapScreen(this, a, gs);
        //newGameScreen = new NewGameScreenOld(this, a, gs);
        newGameScreen = new NewGameScreen(this, this.a, this.gs);
        optionsScreen = new OptionsScreen(this.gs, this.a);
        bohaterScreen = new BohaterScreen(this, this.a, this.gs);
        itemScreen = new ItemScreen(this.a, this.gs);
        testScreen = new TestingScreen(this, this.a, this.gs);
        
        Assets.testScreen = testScreen;
        Assets.mainMenuScreen = mainScreen;
        Assets.newGameScreen = newGameScreen;
        Assets.mapScreen = mapScreen;
        Assets.bohaterScreen = bohaterScreen;
        
        this.setScreen(mainScreen);
    }

    @Override
    public void render() {
        super.render();
        // Algorytm sprawdza czy ma wywoływać tworzenie nowej mapy jeżeli tak
        // sprawdza w GS, czy mapa ma zostać utworzona
        // jeżeli tak, tworzy nowy obiekt klasy MapScreen
        
        /*
        if (!nieWywolujTworzeniaMapy) {
            if (gs.czyUtworzonoMape) {
                mapScreen = new MapScreen(this, a, gs);
                nieWywolujTworzeniaMapy = true;
            }
        }

        if (gs.getActualScreen() == 0) {
            this.setScreen(mainScreen);
        }
        if (gs.getActualScreen() == 1) {
            gs.czyUtworzonoMape = true;
            this.setScreen(mapScreen);
        }
        if (gs.getActualScreen() == 3) {            
            this.setScreen(newGameScreen);
        }
        if (gs.getActualScreen() == 4) {
            this.setScreen(optionsScreen);
        }
        if (gs.getActualScreen() == 5){
            this.setScreen(bohaterScreen);
        }
        if (gs.getActualScreen() == 6){
            this.setScreen(itemScreen);
        }
     */           
    }
}
