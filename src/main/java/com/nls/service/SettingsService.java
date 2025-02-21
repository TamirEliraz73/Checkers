package com.nls.service;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Null;
import com.nls.base.BaseScreen;
import com.nls.game.board.Checker;
import com.nls.game.board.Square;
import com.nls.types.Player;
import com.nls.utilities.MyColor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
public class SettingsService {
    private final @NonNull Color player1Color;
    private final @NonNull Color player2Color;
    private final @NonNull Color squareColor;
    private final @NonNull Color backgroundColor;
    @Setter
    private @Null BaseScreen screen;
    private static @Null SettingsService _instance;

    public static SettingsService getInstance() {
        return getInstance(false);
    }

    public static SettingsService getInstance(boolean forceReload) {
        if ( _instance == null || forceReload ) { _instance = new SettingsService(); }
        return _instance;
    }

    private SettingsService() {
        player1Color = Color.GREEN;
        player2Color = Color.RED;
        squareColor = Color.BLACK;
        backgroundColor = MyColor.woodColor3;
    }

    private void updateColor(@NonNull Color color, @NonNull Player player) {
        player.getColor().set(color);
        for ( Square square : BoardService.getInstance().getBoard().getSquares() ) {
            if ( square.hasChecker() ) {
                Checker checker = square.getChecker();
                System.out.println(checker);
                if ( checker.getPlayer().isPlayer(player) )
                    checker.setColor(color);
            }
        }
    }

    private void updateSquaresColor(@NonNull Color color) {
        for ( Square square : BoardService.getInstance().getBoard().getSquares() ) {
            square.setColor(color);
        }
    }

    public @NonNull SettingsService setPlayer1Color(@NonNull Color player1Color) {
        this.player1Color.set(player1Color);
        updateColor(player1Color, Player.PLAYER1);
        return this;
    }

    public @NonNull SettingsService setPlayer2Color(@NonNull Color player2Color) {
        this.player2Color.set(player2Color);
        updateColor(player2Color, Player.PLAYER2);
        return this;
    }

    public @NonNull SettingsService setPlayerColor(@NonNull Color color, @NonNull Player player) {
        return switch ( player ) {
            case PLAYER1 -> setPlayer1Color(color);
            case PLAYER2 -> setPlayer2Color(color);
        };
    }

    public @NonNull SettingsService setSquareColor(@NonNull Color squareColor) {
        this.squareColor.set(squareColor);
        updateSquaresColor(squareColor);
        return this;
    }

    public @NonNull SettingsService setBackgroundColor(@NonNull Color backgroundColor) {
        this.backgroundColor.set(backgroundColor);
        if ( screen != null ) screen.setBackgroundColor(backgroundColor);
        return this;
    }
}