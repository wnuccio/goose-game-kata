package usecase.reset_game;

import player.Players;
import usecase.UseCase;

public class ResetGameUseCase implements UseCase {

    private Players players;

    public ResetGameUseCase(Players players) {
        this.players = players;
    }

    public void acceptCommand(String commandLine) {
        players.clear();
    }
}
