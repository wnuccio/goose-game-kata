package console;

import usecase.Movement;
import usecase.OutputBoundary;
import usecase.Presenter;

import static java.lang.String.valueOf;

public class ConsolePresenter implements Presenter {
    private OutputBoundary outputBoundary;

    public ConsolePresenter(OutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void presentMovement(Movement movement) {
        outputBoundary.writeOutputLine(buildStringFrom(movement));
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

    private String buildStringFrom(Movement movement) {
        String playerRolls = String.format("%s rolls %s, %s" + ". ",
                movement.player(),
                movement.firstDice(),
                movement.secondDice());

        String playerMoves = String.format("%s moves from %s to %s",
                movement.player(),
                positionString(movement.fromPosition()),
                movement.toPosition());

        String playerVictory = movement.isVictory() ? ". " + movement.player() + " Wins!!" : "";

        return playerRolls + playerMoves + playerVictory;
    }

    private String positionString(int position) {
        return position == 0 ? "Start" : valueOf(position);
    }
}
