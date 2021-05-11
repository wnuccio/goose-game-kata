package boundary.output;

import usecase.Presenter;
import usecase.move_player.MovementView;

public class OutputPresenter implements Presenter {
    private OutputBoundary outputBoundary;

    public OutputPresenter(OutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void presentMovement(MovementView movement) {
        movement.present(new MovementPresenter(movement, outputBoundary));
    }

    @Override
    public void presentNoSuchPlayerError(String player) {
        outputBoundary.writeOutputLine(player + ": no such player");
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
