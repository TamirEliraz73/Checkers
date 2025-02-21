package com.nls.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.nls.base.MyInputAdapter;
import com.nls.game.board.Board;
import com.nls.game.board.Square;
import lombok.Getter;
import lombok.NonNull;

public class BoardService implements MyInputAdapter {
    private static BoardService _instance;
    @Getter
    private final Board board;
    private final Array<BoardListener> anySquareTouchedListeners;
    private final Array<BoardListener> anySquareHoveredListeners;


    public static BoardService getInstance(@NonNull Board board) {
        if ( _instance == null ) { _instance = new BoardService(board); }
        return _instance;
    }

    public static BoardService getInstance() {
        if ( _instance == null ) {
            throw new IllegalStateException("BoardService not initialized (should add a Board instance)");
        }
        return _instance;
    }

    private BoardService(@NonNull Board board) {
        this.board = board;
        anySquareTouchedListeners = new Array<>();
        anySquareHoveredListeners = new Array<>();
        Gdx.input.setInputProcessor(this);
    }

    public @NonNull BoardService registerAnySquareTouchedListener(BoardListener listener) {
        if ( !anySquareTouchedListeners.contains(listener, true) ) anySquareTouchedListeners.add(listener);
        return this;
    }

    public @NonNull BoardService registerAnySquareHoverListener(BoardListener listener) {
        if ( !anySquareHoveredListeners.contains(listener, true) ) anySquareHoveredListeners.add(listener);
        return this;
    }

    private void notifySquareTouched(@NonNull Square square, int button) {
        square.onSquareTouched(button);
    }

    private void notifySquareHover(@NonNull Square square) {
        square.onSquareHover();
    }

    private void notifyAnySquareTouched(Square square, int button) {
        for ( BoardListener listener : anySquareTouchedListeners ) {
            if ( listener.onAnySquareTouched(square, button) ) break;
        }
    }

    private void notifyAnySquareHover(Square square) {
        for ( BoardListener listener : anySquareHoveredListeners ) {
            if ( listener.onAnySquareHover(square) ) break;
        }
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Square square = hit(board, screenX, screenY);
        if ( square != null ) {
            notifySquareHover(square);
        }
        notifyAnySquareHover(square);
        return false; // just in case
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Square square = hit(board, screenX, screenY);
        if ( square != null ) {
            notifySquareHover(square);
        }
        notifyAnySquareHover(square);
        return false; // just in case
    }
}
