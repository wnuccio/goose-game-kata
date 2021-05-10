package usecase.reset_game;

import domain.Players;

public class ResetGameService {

    private ApplicationSwitch applicationSwitch;
    private Players players;

    public ResetGameService(ApplicationSwitch applicationSwitch, Players players) {
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
