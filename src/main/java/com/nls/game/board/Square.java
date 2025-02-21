package com.nls.game.board;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.nls.base.MyInputAdapter;
import com.nls.base.MyShapeRenderer;
import com.nls.base.Vector2R;
import com.nls.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public class Square extends Group implements MyInputAdapter {
    @Getter
    private final Vector2R positionInBoard;
    public static final MyShapeRenderer shapeRenderer = new MyShapeRenderer();

    public Square(int row, int col) { this(new Vector2R(row, col)); }

    public int row() { return getPositionInBoard().row(); }

    public int col() { return getPositionInBoard().col(); }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        shapeRenderer.drawRect(this, batch);
        super.draw(batch, parentAlpha);
    }

    public boolean hit(float screenX, float screenY) { return hit(this, screenX, screenY); }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if ( !isTouchable() ) return false;
        if ( isMouse(pointer) && button == Input.Buttons.LEFT && hit(screenX, screenY) ) {
            BoardService.getInstance().notifySquareTouched(this, button);
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if ( !isTouchable() ) return false;
        if ( hit(screenX, screenY) ) {
            BoardService.getInstance().notifySquareHover(this);
            return true;
        }
        return false;
    }

    public boolean hasChecker() { return this.hasChildren(); }

    public @NonNull Checker getChecker() { return ( Checker ) this.getChild(0); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Square (").append(row()).append(", ").append(col()).append(") ");
        if ( hasChecker() ) sb.append("has ").append(getChecker()).append(".");
        else sb.append("does not have Checker.");
        return sb.toString();
    }
}
