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
        String output = buildOutputFrom(
                movement.player(),
                movement.firstDice(),
                movement.secondDice(),
                movement.fromPosition(),
                movement.toPosition());

        if (movement.isVictory())
            output += ". " + movement.player() + " Wins!!";

        outputBoundary.writeOutputLine(output);
    }

    @Override
    public void presentNoSuchPlayerError(String player) {
        outputBoundary.writeOutputLine(player + ": no such player");
    }

    private String buildOutputFrom(String playerName, int firstDice, int secondDice, int prevPosition, int newPosition) {
        String prevPositionString = prevPosition == 0 ? "Start" : valueOf(prevPosition);
        String playerRolls = String.format("%s rolls %s, %s", playerName, firstDice, secondDice);
        String playerMoves = String.format("%s moves from %s to %s", playerName, prevPositionString, newPosition);
        return playerRolls + ". " + playerMoves;
    }
}
