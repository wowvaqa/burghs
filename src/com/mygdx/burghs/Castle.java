package com.mygdx.burghs;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Klasa odpowiedzialna za zachowanie zamku.
 *
 * @author v
 */
public class Castle extends Actor {

    private final Sprite sprite;
    private final Assets a;
    private final Stage stage;

    private int maxHp = 20;
    private int actualHp = 20;
    private int Obrona = 5;
    private int przynaleznoscDoGracza = 0;

    /**
     *
     * @param stage     
     * @param a
     * @param x
     * @param y
     */
    public Castle(Stage stage, Assets a, int x, int y) {
        this.sprite = new Sprite(a.trawaZamekTex);
        this.setSize(sprite.getWidth(), sprite.getHeight());
        this.setPosition(x, y);
        this.a = a;
        this.stage = stage;

        this.dodajListnera();
    }

    // Dodaje ClickListnera do obiektu Zamku
    private void dodajListnera() {
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Zamek został kliknięty");
                new Dialog("Zamek", a.skin) {
                    {
                        text("Wlasciciel: " + przynaleznoscDoGracza);
                        this.row();
                        text("Obrona: " + Obrona);
                        this.row();
                        text("HP: " + actualHp);
                        this.row();
                        button("Zakoncz", "zakoncz");
                    }

                    @Override
                    protected void result(Object object) {
                        if (object.equals("zakoncz")) {
                            this.remove();
                        }
                    }

                }.show(stage);
            }
        });
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
    /**
     *
     * @return
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     *
     * @param maxHp
     */
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    /**
     *
     * @return
     */
    public int getActualHp() {
        return actualHp;
    }

    /**
     *
     * @param actualHp
     */
    public void setActualHp(int actualHp) {
        this.actualHp = actualHp;
    }

    /**
     *
     * @return
     */
    public int getObrona() {
        return Obrona;
    }

    /**
     *
     * @param Obrona
     */
    public void setObrona(int Obrona) {
        this.Obrona = Obrona;
    }

    /**
     *
     * @return
     */
    public int getPrzynaleznoscDoGracza() {
        return przynaleznoscDoGracza;
    }

    /**
     *
     * @param przynaleznoscDoGracza
     */
    public void setPrzynaleznoscDoGracza(int przynaleznoscDoGracza) {
        this.przynaleznoscDoGracza = przynaleznoscDoGracza;
    }

}
