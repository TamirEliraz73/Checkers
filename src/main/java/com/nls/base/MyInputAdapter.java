package com.nls.base;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import lombok.NonNull;

public interface MyInputAdapter extends InputProcessor {
    @Override
    public default boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public default boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public default boolean keyTyped(char character) {
        return false;
    }

    @Override
    public default boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public default boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public default boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public default boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public default boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public default boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public default boolean isMouse(int pointer) { return pointer == 0; }

    public default boolean hit(@NonNull Actor actor, float screenX, float screenY) {
        MyStage stage = ( MyStage ) actor.getStage();
        stage.updateStageCoordinates(screenX, screenY);

        float stageX = stage.getStageCoordinatesX();
        float stageY = stage.getStageCoordinatesY();

        return stageX >= actor.getX() && stageX <= actor.getX() + actor.getWidth() &&
                stageY >= actor.getY() && stageY <= actor.getY() + actor.getHeight();
    }
}
