package com.nls.base;

import com.badlogic.gdx.graphics.Color;
import lombok.NonNull;

public enum Player {
    PLAYER1, PLAYER2;

    public @NonNull Color getColor() {
        return switch ( this ) {
            case PLAYER1 -> Color.GREEN;
            case PLAYER2 -> Color.RED;
        };
    }

    public static @NonNull Player changeCurrentPlayer(@NonNull Player player) {
        return switch ( player ) {
            case PLAYER1 -> PLAYER2;
            case PLAYER2 -> PLAYER1;
        };
    }
}
