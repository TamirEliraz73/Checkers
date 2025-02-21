package com.nls.service;

import com.badlogic.gdx.utils.Timer;
import com.nls.types.Player;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class GameService {
    @Getter
    private Player currentPlayer;
    @Getter
    private Timer timer;
    private static GameService _instance;

    public static GameService getInstance() {
        if ( _instance == null ) { _instance = new GameService(); }
        return _instance;
    }

    private GameService() { currentPlayer = Player.PLAYER1;timer = new Timer(); }

    public @NonNull GameService changeCurrentPlayer() {
        this.currentPlayer = Player.changeCurrentPlayer(currentPlayer);
        return this;
    }
}
