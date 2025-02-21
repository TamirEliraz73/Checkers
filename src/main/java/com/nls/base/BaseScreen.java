package com.nls.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nls.game.board.Board;
import com.nls.service.SettingsService;
import com.nls.types.BaseScreenType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class BaseScreen implements Screen {
    public static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
    public static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private final Viewport viewport;
    @Getter
    private final Board board;
    @Getter
    @Setter
    private Color backgroundColor;
    public BaseScreen(@NonNull BaseScreenType type, @NonNull Camera camera) {
        this(type, SCREEN_WIDTH, SCREEN_HEIGHT, camera);
    }

    public BaseScreen(@NonNull BaseScreenType type, float width, float height, Camera camera) {
        this.viewport = type.getViewport(width, height, camera);
        this.board = new Board(this.viewport);
        this.backgroundColor = SettingsService.getInstance().getBackgroundColor();
        SettingsService.getInstance().setScreen(this);
    }

    @Override
    public void show() { }

    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        board.update(dt);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() { }


}
