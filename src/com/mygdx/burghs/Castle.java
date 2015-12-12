package com.mygdx.burghs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Klasa odpowiedzialna za zachowanie zamku.
 * @author v
 */
public class Castle extends Actor{
    
    private final Sprite sprite;
    private final GameStatus gs;
    private final Assets a;
    
    private int maxHp = 0;
    private int actualHp = 0;
    private int Obrona = 0;
    private int przynaleznoscDoGracza = 0;

    public Castle(GameStatus gs, Assets a, Texture tekstura, int x, int y) {
        this.sprite = new Sprite(tekstura);
        this.gs = gs;
        this.a = a;
    }    

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    
    //Setters and Getters
    
}
