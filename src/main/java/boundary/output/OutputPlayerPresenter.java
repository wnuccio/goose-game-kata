package boundary.output;

import usecase.add_player.PlayerPresenter;

public class OutputPlayerPresenter implements PlayerPresenter {
    private OutputBoundary outputBoundary;

    public OutputPlayerPresenter(OutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void presentPlayers(String... players) {
        String playersSeparatedByComma = String.join(", ", players);
        outputBoundary.writeOutputLine("players: " + playersSeparatedByComma);
    }

    @Override
    public void presentExistingPlayerError(String player) {
        outputBoundary.writeOutputLine(player + ": already existing player");
    }
}
