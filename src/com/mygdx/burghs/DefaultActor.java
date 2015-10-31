package com.mygdx.burghs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class DefaultActor extends Actor {

    private final Sprite sprite;

    /**
     *
     * @param tekstura
     * Tekstura która wyświetli się jako obrazek aktora
     * @param x
     * współżędna szerokości na ekranie
     * @param y
     * współżędna wysokości na ekranie
     */
    public DefaultActor(Texture tekstura, int x, int y) {
        sprite = new Sprite(tekstura);
        this.setSize(sprite.getWidth(), sprite.getHeight());
        this.setPosition(x, y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

}
