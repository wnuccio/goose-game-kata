package app.domain.game;

import app.domain.player.Players;

public class Game {
    private boolean isOn;
    private Players players;

    public Game(Players players) {
        this.isOn = true;
        this.players = players;
    }

    public boolean isOn() {
        return isOn;
    }

    public void reset() {
        players.clear();
    }

    public void stop() {
        isOn = false;
    }
}
