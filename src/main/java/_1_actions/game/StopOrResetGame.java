package _1_actions.game;

import _2_domain.game.Game;
import _2_domain.player.Players;

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
