package usecase.reset_game;

import usecase.UseCase;
import usecase.add_player.Players;

public class ResetGameUseCase implements UseCase {

    private Players players;

    public ResetGameUseCase(Players players) {
        this.players = players;
    }

    public void acceptCommand(String commandLine) {
        players.clear();
    }
}
