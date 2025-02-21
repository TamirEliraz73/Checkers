package com.nls.service;

import com.nls.game.board.Square;

public interface BoardListener {
    public default boolean onSquareTouched(Square square, int button) { return false; }

    public default boolean onSquareHover(Square square) { return false; }
}
