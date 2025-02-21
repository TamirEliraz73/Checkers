package com.nls.base;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Null;
import com.nls.game.board.Board;
import com.nls.game.board.Square;
import com.nls.game.board.SquareFactory;
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
        Vector2 stageXY = getStageXY(actor, screenX, screenY);

        float stageX = stageXY.x;
        float stageY = stageXY.y;

        return stageX >= actor.getX() && stageX <= actor.getX() + actor.getWidth() &&
                stageY >= actor.getY() && stageY <= actor.getY() + actor.getHeight();
    }

    public default @Null Square hit(@NonNull Board board, float screenX, float screenY) {
        Vector2 stageXY = getStageXY(board, screenX, screenY);

        int col = ( int ) (stageXY.x / (SquareFactory.SQUARE_WIDTH));
        int row = ( int ) (stageXY.y / (SquareFactory.SQUARE_HEIGHT));

        try {
            return board.getSquare(row, col);
        } catch ( SquareNotFoundException ignore ) { return null; }
    }

    private @NonNull Vector2 getStageXY(@NonNull Actor actor, float screenX, float screenY) {
        MyStage stage = ( MyStage ) actor.getStage();
        stage.updateStageCoordinates(screenX, screenY);
        return stage.getStageCoordinates();
    }
}
