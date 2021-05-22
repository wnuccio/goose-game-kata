package _1_actions.game;

import _2_domain.game.GameSwitch;
import _2_domain.player.Players;

public class StopOrResetGame {

    private final GameSwitch gameSwitch;
    private final Players players;

    public StopOrResetGame(GameSwitch gameSwitch, Players players) {
        this.gameSwitch = gameSwitch;
        this.players = players;
    }

    public void doReset() {
        players.clear();
    }

    public void doStop() {
        gameSwitch.turnOff();
    }
}
