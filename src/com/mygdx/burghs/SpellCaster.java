package com.mygdx.burghs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Klasa odpowiada za wyświetlanie interfejsu do rzucania czarów na odległość.
 *
 * @author v
 */
public class SpellCaster {

    Bohater bohater;
    Assets a;
    GameStatus gs;
    SpellActor spell;

    public SpellCaster(Bohater bohater, Assets a, GameStatus gs, SpellActor spell) {
        this.bohater = bohater;
        this.a = a;
        this.gs = gs;
        this.spell = spell;

        int pozX = this.bohater.getPozXnaMapie();
        int pozY = this.bohater.getPozYnaMapie();

        for (int i = pozX - 1 - spell.getZasieg(); i < pozX + 1 + 1 + spell.getZasieg(); i++) {
            for (int j = pozY - 1 - spell.getZasieg(); j < pozY + 1 + 1 + spell.getZasieg(); j++) {
                if (i >= 0 && j >= 0 && i < gs.getMapa().getIloscPolX() && j < gs.getMapa().getIloscPolY()) {

                    if (bohater.getPozXnaMapie() == i && bohater.getPozYnaMapie() == j) {
                        CastButtonCancel przyciskCancel = new CastButtonCancel(new TextureRegionDrawable(new TextureRegion(a.cancelIcon)));
                        przyciskCancel.setPosition(i * 100, j * 100);
                        Assets.stage01MapScreen.addActor(przyciskCancel);
                        Ruch.wylaczPrzyciski();
                    } else {
                        if (sprawdzPrzeciwnika(i, j)) {

                            // Potrzebny warunek sprawdzający czy w polu znajduje się przeciwnik
                            CastButton castButton = new CastButton(new TextureRegionDrawable(new TextureRegion(a.spellIcon)), i, j);
                            castButton.setPosition(i * 100, j * 100);
                            Assets.stage01MapScreen.addActor(castButton);
                            Assets.stage03MapScreen.clear();
                            gs.isSpellPanelActive = false;
                            Gdx.input.setInputProcessor(Assets.stage01MapScreen);
                        }
                    }
                }
            }
        }
    }
    
    public static void wylaczPrzyciski() {
        int rozmiar = Assets.stage01MapScreen.getActors().size;
        // Infromuje czy wśród aktorów stage 01 są jeszcze przyciski
        boolean czySaPrzyciski;

        do {
            czySaPrzyciski = false;
            for (int i = 0; i < rozmiar; i++) {
                if (Assets.stage01MapScreen.getActors().get(i).getClass() == SpellCaster.CastButton.class) {
                    Assets.stage01MapScreen.getActors().removeIndex(i);
                    rozmiar = Assets.stage01MapScreen.getActors().size;
                }
            }
            for (int i = 0; i < Assets.stage01MapScreen.getActors().size; i++) {
                //czySaPrzyciski = Assets.stage01MapScreen.getActors().get(i).getClass() == PrzyciskRuchu.class;
                if (Assets.stage01MapScreen.getActors().get(i).getClass() == SpellCaster.CastButton.class) {
                    czySaPrzyciski = true;
                }
            }
        } while (czySaPrzyciski);

        do {
            czySaPrzyciski = false;
            for (int i = 0; i < rozmiar; i++) {
                if (Assets.stage01MapScreen.getActors().get(i).getClass() == SpellCaster.CastButtonCancel.class) {
                    Assets.stage01MapScreen.getActors().removeIndex(i);
                    rozmiar = Assets.stage01MapScreen.getActors().size;
                }
            }
            for (int i = 0; i < rozmiar; i++) {
                czySaPrzyciski = Assets.stage01MapScreen.getActors().get(i).getClass() == SpellCaster.CastButtonCancel.class;
            }
        } while (czySaPrzyciski);


    }

    private boolean sprawdzPrzeciwnika(int x, int y) {
        if (gs.getMapa().getPola()[x][y].getBohater() != null
                && gs.getMapa().getPola()[x][y].getBohater().getPrzynaleznoscDoGracza() != gs.getTuraGracza()) {
            return true;
        }
        /**
         * Zwraca true jeżeli napotkany zamek nie należy do gracza i jego poziom
         * HP > 0
         */
        if (gs.getMapa().getPola()[x][y].getCastle() != null
                && gs.getMapa().getPola()[x][y].getCastle().getPrzynaleznoscDoGracza() != gs.getTuraGracza()
                && gs.getMapa().getPola()[x][y].getCastle().getActualHp() > 0) {
            return true;
        }
        return gs.getMapa().getPola()[x][y].getMob() != null;
    }

    /**
     * Klasa definiuje przycisk rzucania czaru.
     */
    public class CastButton extends ImageButton {

        private final int locX;
        private final int locY;

        /**
         * Tworzy przycisk rzucania czaru.
         *
         * @param imageUp Obiekt drawable tekstury przycisku
         * @param locX lokacja X na mapie
         * @param locY lokacja Y na mapie
         */
        public CastButton(Drawable imageUp, int locX, int locY) {
            super(imageUp);
            this.locX = locX;
            this.locY = locY;
        }
    }

    /**
     * Klasa definiuje przycisk anulowania rzucania czaru.
     */
    public class CastButtonCancel extends ImageButton {

        /**
         * Tworzy przycisk anulowania rzucania czaru.
         *
         * @param imageUp Obiekt drawable tekstury przycisku
         */
        public CastButtonCancel(Drawable imageUp) {
            super(imageUp);
        }

        @Override
        public boolean addListener(EventListener listener) {
            return super.addListener(new ClickListener(){

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SpellCaster.wylaczPrzyciski();
                }
            }); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
