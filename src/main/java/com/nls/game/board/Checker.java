package com.nls.game.board;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.nls.types.Direction;
import com.nls.types.Player;
import com.nls.service.BoardListener;
import com.nls.service.BoardService;
import com.nls.service.GameService;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Getter
@Accessors(chain = true)
public class Checker extends Actor implements BoardListener {
    private boolean isActive;
    private Square square;
    private final Player player;

    public Checker(@NonNull Square square, @NonNull Player player) {
        setSquare(square).setColor(player.getColor());
        this.player = player;

        this.isActive = false;
        BoardService.getInstance().registerAnySquareHoverListener(this);
//        BoardService.getInstance().registerSquareTouchedListener(this).registerSquareHoverListener(this);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Square.shapeRenderer.drawCircle(this, batch);
    }

    public @NonNull Checker setSquare(@NonNull Square square) {
        square.addActor(this);
        setPosition(square.getX() + (square.getWidth()) / 2f,
                square.getY() + (square.getHeight()) / 2f);
        setWidth(Math.min(square.getWidth(), square.getHeight()) / 3f);
        this.square = square;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Checker player ").append(player.name());
        return sb.toString();
    }

    @Override
    public boolean onSquareHover() {
        System.out.print("\r" + this + " at " + square + " with neighbors " + square.getNeighbor(Direction.DOWN_LEFT));
        if ( this.player == GameService.getInstance().getCurrentPlayer() ) {
            if ( !isActive ) {
                addAction(Actions.sequence(
                        Actions.parallel(
                                Actions.scaleBy(0.5f, 0.5f, 0.2f),  // 🔹 מגדיל את הריבוע
                                Actions.color(Color.YELLOW, 0.2f)   // 🔹 משנה צבע לצהוב
                        ),
                        Actions.parallel(
                                Actions.scaleBy(-0.5f, -0.5f, 0.2f), // 🔹 מחזיר לגודל המקורי
                                Actions.color(getPlayer().getColor(), 0.2f)    // 🔹 מחזיר את הצבע
                        )
                ));
                isActive = true;
            }
        }
        return BoardListener.super.onSquareHover();
    }

    @Override
    public boolean onAnySquareHover(Square square) {
        if ( square != this.square ) { this.isActive = false; }
        return BoardListener.super.onAnySquareHover(square);
    }
}