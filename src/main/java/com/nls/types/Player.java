package com.nls.types;

import com.badlogic.gdx.graphics.Color;
import com.nls.service.SettingsService;
import lombok.NonNull;

public enum Player {
    PLAYER1, PLAYER2;

    public @NonNull Color getColor() {
        return switch ( this ) {
            case PLAYER1 -> SettingsService.getInstance().getPlayer1Color();
            case PLAYER2 -> SettingsService.getInstance().getPlayer2Color();
        };
    }

    public static @NonNull Player changeCurrentPlayer(@NonNull Player player) {
        return switch ( player ) {
            case PLAYER1 -> PLAYER2;
            case PLAYER2 -> PLAYER1;
        };
    }

    public boolean isPlayer1() { return this == PLAYER1; }

    public boolean isPlayer2() { return this == PLAYER2; }

    public boolean isPlayer(@NonNull Player player) {
        return switch ( player ) {
            case PLAYER1 -> isPlayer1();
            case PLAYER2 -> isPlayer2();
        };
    }
}