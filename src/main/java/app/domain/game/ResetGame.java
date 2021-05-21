package app.domain.game;

import app.domain.player.Players;

public class ResetGame {

    private final GameSwitch gameSwitch;
    private final Players players;

    public ResetGame(GameSwitch gameSwitch, Players players) {
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
