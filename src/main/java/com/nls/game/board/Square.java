package com.nls.game.board;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Null;
import com.nls.service.SettingsService;
import com.nls.types.Direction;
import com.nls.utilities.MyShapeRenderer;
import com.nls.types.Vector2R;
import com.nls.service.BoardListener;
import com.nls.service.BoardService;
import lombok.Getter;
import lombok.NonNull;

import java.util.Random;

//@AllArgsConstructor
public class Square extends Group implements BoardListener {// implements MyInputAdapter {
    @Getter
    private final Vector2R positionInBoard;
    public static final MyShapeRenderer shapeRenderer = new MyShapeRenderer();

    public Square(int row, int col) { this(new Vector2R(row, col)); }

    public Square(@NonNull Vector2R position) {
        this.positionInBoard = position;
//        BoardService.getInstance().registerAnySquareHoverListener(this);
    }

    public int row() { return getPositionInBoard().row(); }

    public int col() { return getPositionInBoard().col(); }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        shapeRenderer.drawRect(this, batch);
        super.draw(batch, parentAlpha);
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

    @Override
    public boolean onSquareHover() {
//        Random random = new Random();
//        SettingsService.getInstance().setBackgroundColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat()));
//        SettingsService.getInstance().setPlayer2Color(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1));
//        SettingsService.getInstance().setSquareColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1));
        if ( hasChecker() ) getChecker().onSquareHover();
        return BoardListener.super.onSquareHover();
    }

    public @Null Square getNeighbor(@NonNull Direction direction) {
        Board board = BoardService.getInstance().getBoard();
        int _row = getPositionInBoard().row();
        int _col = getPositionInBoard().col();
        switch ( direction ) {
            case UP_LEFT -> {
                _row++;
                _col--;
            }
            case UP_RIGHT -> {
                _row++;
                _col++;
            }
            case DOWN_LEFT -> {
                _row--;
                _col--;
            }
            case DOWN_RIGHT -> {
                _row--;
                _col++;
            }
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }
        return board.hasSquare(_row, _col) ? board.getSquare(_row, _col) : null;
    }
}
