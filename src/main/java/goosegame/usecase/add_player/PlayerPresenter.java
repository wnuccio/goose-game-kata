package goosegame.usecase.add_player;

import goosegame.domain.OutputBoundary;

public class PlayerPresenter {
    private final OutputBoundary outputBoundary;

    public PlayerPresenter(OutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    public void presentPlayers(String... players) {
        String playersSeparatedByComma = String.join(", ", players);
        outputBoundary.writeOutputLine("players: " + playersSeparatedByComma);
    }
    
    public void presentExistingPlayerError(String player) {
        outputBoundary.writeOutputLine(player + ": already existing player");
    }
}
