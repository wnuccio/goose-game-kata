package _1_actions.game;

import _2_domain.Players;

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
