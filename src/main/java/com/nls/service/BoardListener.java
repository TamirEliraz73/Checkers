package com.nls.service;

import com.nls.game.board.Square;

public interface BoardListener {
    public default boolean onAnySquareTouched(Square square, int button) { return false; }

    public default boolean onSquareTouched(int button) { return false; }

    public default boolean onAnySquareHover(Square square) { return false; }

    public default boolean onSquareHover() { return false; }
}
