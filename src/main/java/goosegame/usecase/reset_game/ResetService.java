package goosegame.usecase.reset_game;

import goosegame.domain.Players;

public class ResetService {

    private final ApplicationSwitch applicationSwitch;
    private final Players players;

    public ResetService(ApplicationSwitch applicationSwitch, Players players) {
        this.applicationSwitch = applicationSwitch;
        this.players = players;
    }

    public void doReset() {
        players.clear();
    }

    public void doStop() {
        applicationSwitch.turnOff();
    }
}
