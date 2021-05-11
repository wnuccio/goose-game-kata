package boundary.output;

import domain.Position;
import usecase.move_player.Movement;
import usecase.move_player.MovementView;
import usecase.move_player.RepeatedMovement;
import usecase.move_player.SimpleMovement;

import static domain.Position.WIN_POSITION;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static usecase.move_player.MovementType.SIMPLE;

public class MovementPresenter {
    private MovementView movementView;
    private OutputBoundary outputBoundary;

    public MovementPresenter(MovementView movementView, OutputBoundary outputBoundary) {
        this.movementView = movementView;
        this.outputBoundary = outputBoundary;
    }

    public void presentSimpleMovement(SimpleMovement movement) {
        String playerRolls = format("%s rolls %s, %s" + ". ",
                player(),
                firstDice(),
                secondDice());

        String playerMoves = format("%s moves from %s to %s",
                player(),
                positionName(movement.startPosition()),
                positionName(movement.finalPosition()));

        String specialCase = "";
        if (movement.isVictory()) {
            specialCase = format(". %s Wins!!", player());
        }

        outputBoundary.writeOutputLine(playerRolls + playerMoves + specialCase);
    }

    public void presentJumpOnBridge(RepeatedMovement movement) {
        String playerRolls = format("%s rolls %s, %s" + ". ",
                player(),
                firstDice(),
                secondDice());

        String playerMoves = format("%s moves from %s to %s",
                player(),
                positionName(movement.startPosition()),
                positionName(Position.BRIDGE));

        String specialCase = format(". %s jumps to 12",
                player());

        outputBoundary.writeOutputLine(playerRolls + playerMoves + specialCase);
    }

    public void presentBouncing(RepeatedMovement movement) {
        String playerRolls = format("%s rolls %s, %s" + ". ",
                player(),
                firstDice(),
                secondDice());

        String playerMoves = format("%s moves from %s to %s",
                player(),
                positionName(movement.startPosition()),
                positionName(WIN_POSITION));

        String specialCase = format(". %s bounces! %s returns to %d",
                player(),
                player(),
                movement.finalPosition());

        outputBoundary.writeOutputLine( playerRolls + playerMoves + specialCase);

    }

    private String outputForRepeatOnGoose(Movement movement) {
        if (movement.type() == SIMPLE) {
            String playerRolls = format("%s rolls %s, %s" + ". ",
                    player(),
                    firstDice(),
                    secondDice());

            String playerMoves = format("%s moves from %s to %s, The Goose.",
                    player(),
                    positionName(movement.startPosition()),
                    positionName(movement.finalPosition()));
            return(playerRolls + playerMoves);
        }

        return outputForRepeatOnGoose(movement.previousMovement())
                + format(" %s moves again and goes to %s%s",
                player(),
                movement.finalPosition(),
                movement.endsOnGoose() ? ", The Goose." : "");
    }

    public void presentRepeatOnGoose(RepeatedMovement movement) {
        outputBoundary.writeOutputLine(outputForRepeatOnGoose(movement));
    }

    private String positionName(int position) {
        if (position == Position.START) return "Start";
        if (position == Position.BRIDGE) return "The Bridge";
        if (position == Position.BRIDGE_TARGET) return "12";
        if (position == WIN_POSITION) return "63";

        return valueOf(position);
    }

    private int secondDice() {
        return movementView.secondDice();
    }

    private int firstDice() {
        return movementView.firstDice();
    }

    private String player() {
        return movementView.player();
    }
}
