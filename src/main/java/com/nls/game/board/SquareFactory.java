package com.nls.game.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.nls.types.Player;
import com.nls.types.Vector2R;
import lombok.NonNull;

public class SquareFactory {
    public static final float SQUARE_WIDTH = Gdx.graphics.getWidth() / 8f;
    public static final float SQUARE_HEIGHT = Gdx.graphics.getHeight() / 8f;
    private final Vector2 _pos = new Vector2();

    private SquareFactory() {
        _pos.setZero();
        _next();
    }

    private static SquareFactory _instance;

    public static SquareFactory getInstance(boolean forceReset) {
        if ( _instance == null || forceReset ) {
            _instance = new SquareFactory();
        }
        return _instance;
    }

    private @NonNull Vector2R getNextSquare() {
        if ( !hasNext() ) throw new IndexOutOfBoundsException("Cannot get next square (full). args: " + _pos);
        final Vector2R next = new Vector2R(( int ) _pos.y, ( int ) _pos.x);
        _next();
        return next;
    }

    private void _next() {
        do {
            if ( _pos.x > 6 ) {
                _pos.x = 0;
                _pos.y++;
            } else {
                _pos.x++;
            }
        }
        while ( hasNext() && _pos.x % 2 == _pos.y % 2 );
    }

    public @NonNull Square next() {
        return new Square(getNextSquare()) {{
            setPosition(col() * SQUARE_WIDTH, row() * SQUARE_HEIGHT);
            setSize(SQUARE_WIDTH, SQUARE_HEIGHT);
            setColor(Color.BLACK);
            setTouchable(Touchable.enabled);
            if ( this.row() < 3 ) addActor(new Checker(this, Player.PLAYER1));
            if ( this.row() > 4 ) addActor(new Checker(this, Player.PLAYER2));
        }};
    }

    public boolean hasNext() {
//        System.out.println(_pos);
//        if ( _pos.y == 8 ) {
//            System.out.println();
//        }
        return _pos.y < 8;
    }
}
