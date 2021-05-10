package boundary.output;

import domain.Position;
import usecase.Presenter;
import usecase.move_player.Movement;

import static java.lang.String.format;
import static java.lang.String.valueOf;

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

        } else if (movement.type().isBouncing()) {
            specialCase = format(". %s bounces! %s returns to %d",
                    movement.player(),
                    movement.player(),
                    movement.finalPosition());

        } else if (movement.type().isJumpOnBridge()) {
            specialCase = format(". %s jumps to 12",
                    movement.player());

        } else if (movement.type().isRepeatOnGoose()) {
            playerMoves = format("%s moves from %s to %s, The Goose. %s moves again and goes to %s",
                    movement.player(),
                    positionName(movement.startPosition()),
                    positionName(movement.intermediatePosition()),
                    movement.player(),
                    positionName(movement.finalPosition()));
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
