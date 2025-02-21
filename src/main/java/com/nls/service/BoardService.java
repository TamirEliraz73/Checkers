package com.nls.service;

import com.badlogic.gdx.utils.Array;
import com.nls.game.board.Square;
import lombok.NonNull;

public class BoardService {
    private static BoardService _instance;
    private final Array<BoardListener> squareTouchedListeners;
    private final Array<BoardListener> squareHoveredListeners;

    public static BoardService getInstance() {
        if ( _instance == null ) { _instance = new BoardService(); }
        return _instance;
    }

    private BoardService() {
        squareTouchedListeners = new Array<>();
        squareHoveredListeners = new Array<>();
    }

    public @NonNull BoardService registerSquareTouchedListener(BoardListener listener) {
        if ( !squareTouchedListeners.contains(listener, true) ) squareTouchedListeners.add(listener);
        return this;
    }

    public @NonNull BoardService registerSquareHoverListener(BoardListener listener) {
        if ( !squareHoveredListeners.contains(listener, true) ) squareHoveredListeners.add(listener);
        return this;
    }

    public void notifySquareTouched(Square square, int button) {
        for ( BoardListener listener : squareTouchedListeners ) {
            if ( listener.onSquareTouched(square, button) ) break;
        }
    }

    public void notifySquareHover(Square square) {
        for ( BoardListener listener : squareHoveredListeners ) { if ( listener.onSquareHover(square) ) break; }
    }

}
