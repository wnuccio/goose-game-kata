package app.domain.game;

import app.domain.player.Players;

public class StopOrResetGame {

    private final Game game;
    private final Players players;

    public StopOrResetGame(Game game, Players players) {
        this.game = game;
        this.players = players;
    }

    public void doReset() {
        players.clear();
    }

    public void doStop() {
        game.turnOff();
    }
}
