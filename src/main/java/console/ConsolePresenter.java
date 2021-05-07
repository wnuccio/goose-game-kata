package console;

import usecase.Presenter;
import usecase.move_player.Movement;

import static java.lang.String.format;
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
        String playerRolls = format("%s rolls %s, %s" + ". ",
                movement.player(),
                movement.firstDice(),
                movement.secondDice());

        int newPosition = movement.isBouncing() ? Movement.WIN_POSITION : movement.toPosition();

        String playerMoves = format("%s moves from %s to %s",
                movement.player(),
                positionString(movement.fromPosition()),
                newPosition);

        String specialCase = "";
        if (movement.isVictory()) {
            specialCase = format(". %s Wins!!", movement.player());

        } else if (movement.isBouncing()) {
            specialCase = format(". %s bounces! %s returns to %d",
                    movement.player(),
                    movement.player(),
                    movement.toPosition());
        }

        return playerRolls + playerMoves + specialCase;
    }

    private String positionString(int position) {
        return position == 0 ? "Start" : valueOf(position);
    }
}
