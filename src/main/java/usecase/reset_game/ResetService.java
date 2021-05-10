package usecase.reset_game;

import domain.Players;

public class ResetService {

    private ApplicationSwitch applicationSwitch;
    private Players players;

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
