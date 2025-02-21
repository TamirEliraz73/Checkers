package com.nls.base;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.nls.types.BaseScreenType;
import lombok.Getter;

@Getter
public class BaseGame extends Game {
    private OrthographicCamera camera;
    private BaseScreen screen;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        screen = new BaseScreen(BaseScreenType.STRETCH, camera);
        setScreen(screen);
//        Gdx.input.setInputProcessor(this);
    }

//    @Override
//    public boolean keyDown(int keycode) {
//        if ( keycode == Input.Keys.SPACE ) {
//            this.screen.setBackgroundColor(Color.BLUE);
//        } else if ( keycode == Input.Keys.G ) { this.screen.setBackgroundColor(Color.GREEN); }
//        return MyInputAdapter.super.keyDown(keycode);
//    }

    @Override
    public void dispose() {
        super.dispose();
        screen.dispose();
    }
}
