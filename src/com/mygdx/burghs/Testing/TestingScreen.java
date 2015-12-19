package com.mygdx.burghs.Testing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.burghs.Assets;
import com.mygdx.burghs.DefaultActor;
import com.mygdx.burghs.GameStatus;

/**
 *
 * @author v
 */
public class TestingScreen implements Screen {

    static final int WORLD_WIDTH = 100;
    static final int WORLD_HEIGHT = 100;

    private final OrthographicCamera c;
    private final FitViewport viewPort;

    private final Stage stage01 = new Stage();

    private TextButton btnExit;

    private Table tabela = new Table();
    private final Assets a;
    private final Game g;
    private final GameStatus gs;

    private final float rotationSpeed;

    public TestingScreen(Game g, Assets a, GameStatus gs) {
        rotationSpeed = 0.5f;

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        c = new OrthographicCamera(w, h);
        viewPort = new FitViewport(w, h, c);
        //stage01.setViewport(viewPort);
        //stage01.getViewport().setCamera(c);
        //c = new OrthographicCamera(30, 30 * (h / w));
        //c.position.set(c.viewportWidth / 2f, c.viewportHeight / 2f, 0);
        //c.update();

        this.a = a;
        this.gs = gs;
        this.g = g;

        tabela = new Table(a.skin);

        makeSprites();

        makeButtons();

        formatujTabele();
        
        //c.update();
    }

    private void makeSprites() {

        DefaultActor da = new DefaultActor(a.mobDwarfTex, 200, 200);
        stage01.addActor(da);
    }

    private void makeButtons() {
        btnExit = new TextButton("EXIT", a.skin);
        btnExit.setPosition(1, 1);
        btnExit.setSize(200, 100);
        btnExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.setScreen(Assets.mainMenuScreen);
            }
        });
    }

    private void formatujTabele() {
        tabela.setFillParent(true);
        tabela.pad(10);

        tabela.add(new Label("Testowanie funkcji", a.skin)).expand().align(Align.top);
        tabela.row();
        tabela.add(btnExit).width(200).height(50).space(300);

        stage01.addActor(tabela);

    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            c.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            c.zoom -= 1.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            c.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            c.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            c.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            c.translate(0, 3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            c.rotate(-rotationSpeed, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            c.rotate(rotationSpeed, 0, 0, 1);
        }

        c.zoom = MathUtils.clamp(c.zoom, 0.1f, 100 / c.viewportWidth);

        float effectiveViewportWidth = c.viewportWidth * c.zoom;
        float effectiveViewportHeight = c.viewportHeight * c.zoom;

        c.position.x = MathUtils.clamp(c.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        c.position.y = MathUtils.clamp(c.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage01);
    }

    @Override
    public void render(float delta) {
        handleInput();
        //c.update();
        //stage01.getBatch().setProjectionMatrix(c.combined);
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage01.act();
        stage01.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage01.getViewport().update(width, height, true);
        viewPort.update(width, height, true);
//        c.viewportWidth = 30f;
//        c.viewportHeight = 30f * height / width;
//        c.update();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage01.dispose();
    }

}
