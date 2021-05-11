package boundary.output;

import domain.Position;
import usecase.Presenter;
import usecase.move_player.Movement;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static usecase.move_player.MovementType.*;

public class OutputPresenter implements Presenter {
    private OutputBoundary outputBoundary;

    public OutputPresenter(OutputBoundary outputBoundary) {
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
        if (movement.type() == SIMPLE) {
            return outputForSimpleMovement(movement);

        } else if (movement.type() == BOUNCING) {
            return outputForBouncing(movement);

        } else if (movement.type() == JUMP_ON_BRIDGE) {
            return outputForJumpOnBridge(movement);

        } else if (movement.type().isRepeatOnGoose()) {
            return outputForRepeatOnGoose(movement);

        } else {
            throw new IllegalStateException();
        }
    }

    private String outputForRepeatOnGoose(Movement movement) {
        if (movement.type() == SIMPLE) return outputForSimpleMovement(movement) + ", The Goose.";

        return outputForRepeatOnGoose(movement.previousMovement())
                + format(" %s moves again and goes to %s%s",
                movement.player(),
                movement.finalPosition(),
                movement.endsOnGoose() ? ", The Goose." : "");
    }

    private String outputForJumpOnBridge(Movement movement) {
        String playerRolls = format("%s rolls %s, %s" + ". ",
                movement.player(),
                movement.firstDice(),
                movement.secondDice());

        String playerMoves = format("%s moves from %s to %s",
                movement.player(),
                positionName(movement.startPosition()),
                positionName(movement.intermediatePosition()));

        String specialCase = format(". %s jumps to 12",
                movement.player());

        return playerRolls + playerMoves + specialCase;
    }

    private String outputForBouncing(Movement movement) {
        String playerRolls = format("%s rolls %s, %s" + ". ",
                movement.player(),
                movement.firstDice(),
                movement.secondDice());

        String playerMoves = format("%s moves from %s to %s",
                movement.player(),
                positionName(movement.startPosition()),
                positionName(movement.intermediatePosition()));

        String specialCase = format(". %s bounces! %s returns to %d",
                movement.player(),
                movement.player(),
                movement.finalPosition());

        return playerRolls + playerMoves + specialCase;
    }

    private String outputForSimpleMovement(Movement movement) {
        String playerRolls = format("%s rolls %s, %s" + ". ",
                movement.player(),
                movement.firstDice(),
                movement.secondDice());

        String playerMoves = format("%s moves from %s to %s",
                movement.player(),
                positionName(movement.startPosition()),
                positionName(movement.intermediatePosition()));

        String specialCase = "";
        if (movement.isVictory()) {
            specialCase = format(". %s Wins!!", movement.player());
        }

        return playerRolls + playerMoves + specialCase;
    }

    private String positionName(int position) {
        if (position == Position.START) return "Start";
        if (position == Position.BRIDGE) return "The Bridge";
        if (position == Position.BRIDGE_TARGET) return "12";
        if (position == Position.WIN_POSITION) return "63";

        return valueOf(position);
    }
}
