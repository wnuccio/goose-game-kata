package usecase.reset_game;

import domain.Players;

public class ResetGameService {

    private Players players;

    public ResetGameService(Players players) {
        this.players = players;
    }

    public void doReset() {
        players.clear();
    }
}
