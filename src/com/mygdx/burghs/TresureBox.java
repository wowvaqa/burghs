package com.mygdx.burghs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import enums.DostepneItemki;
import java.util.ArrayList;

/**
 *  Klasa przechowuje losową partię itemków, które bohater może podnieść i
 *  zabrać w swoim ekwipunku.
 * @author wow
 */
public class TresureBox extends Actor{
    
    // wygląd
    private Texture icon;
    private final Sprite sprite;
    
    // assety
    private final Assets a;
    private Game g;
    private final GameStatus gs;
    
    // array list przechowujący itemy.
    private final ArrayList<Item> dostepneItemy = new ArrayList<Item>(); 
    
    // obiekt do generowania itemów.
    private final ItemCreator itemCreator;
    
    // Konsturktor
    public TresureBox(Assets a, GameStatus gs, Game g){
        this.a = a;
        this.gs = gs;
        this.g = g;
        
        itemCreator = new ItemCreator(gs);
        
        sprite = new Sprite(a.texTresureBox);
        this.setSize(100, 100);
        this.setPosition(200, 200);    
        
        this.losujItemy();
    }
    
    @Override
    public void act(float delta) {
        super.act(delta); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.sprite, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    
    // SETTERS AND GETTERS
    public ArrayList<Item> getDostepneItemy() {
        return dostepneItemy;
    }
    
    /**
     *  Generuje zestaw itemów i dodaje je do arraylista z itemami     
     */
    private void losujItemy(){
        dostepneItemy.add(itemCreator.utworzItem(DostepneItemki.LnianaCzapka, a, g));
        dostepneItemy.add(itemCreator.utworzItem(DostepneItemki.SkorzanaCzapka, a, g));
        dostepneItemy.add(itemCreator.utworzItem(DostepneItemki.LnianaKoszula, a, g));
        dostepneItemy.add(itemCreator.utworzItem(DostepneItemki.SkorzaneSpodnie, a, g));
        dostepneItemy.add(itemCreator.utworzItem(DostepneItemki.SkorzaneButy, a, g));
        dostepneItemy.add(itemCreator.utworzItem(DostepneItemki.Kij, a, g));
        dostepneItemy.add(itemCreator.utworzItem(DostepneItemki.Miecz, a, g));
        dostepneItemy.add(itemCreator.utworzItem(DostepneItemki.Tarcza, a, g));
        dostepneItemy.add(itemCreator.utworzItem(DostepneItemki.Gold, a, g));
    }
}