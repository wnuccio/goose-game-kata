package usecase;

import player.Players;

public class ResetGameUseCase {

    private Players players;

    public ResetGameUseCase(Players players) {
        this.players = players;
    }

    public void acceptCommand(String commandLine) {
        players.clear();
    }
}
