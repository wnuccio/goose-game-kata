package boundary.output;

import domain.Position;
import usecase.move_player.*;

import java.util.HashMap;

import static domain.Position.*;
import static java.lang.String.format;
import static java.lang.String.valueOf;

public class MovementPresenter {
    private MovementView movementView;
    private OutputBoundary outputBoundary;

    public MovementPresenter(MovementView movementView, OutputBoundary outputBoundary) {
        this.movementView = movementView;
        this.outputBoundary = outputBoundary;
    }

    private String playerRolls() {
        return format("%s rolls %s, %s" + ". ", player(), firstDice(), secondDice());
    }

    private String playerMoves(int from, int to) {
        return format("%s moves from %s to %s", player(), positionName(from), positionName(to));
    }

    public void presentSimpleMovement(SimpleMovement movement) {
        String playerMoves = playerMoves(movement.startPosition(), movement.finalPosition());
        String playerWins = movement.isVictory() ? format(". %s Wins!!", player()) : "";

        outputBoundary.writeOutputLine(playerRolls() + playerMoves + playerWins);
    }

    public void presentJumpOnBridge(FurtherMovement movement) {
        String playerMoves = playerMoves(movement.startPosition(), BRIDGE);
        String playerJumps = format(". %s jumps to 12", player());

        outputBoundary.writeOutputLine(playerRolls() + playerMoves + playerJumps);
    }

    public void presentBouncing(FurtherMovement movement) {
        String playerMoves = playerMoves(movement.startPosition(), WIN_POSITION);
        String playerBounces = format(". %s bounces! %s returns to %d", player(), player(), movement.finalPosition());

        outputBoundary.writeOutputLine( playerRolls() + playerMoves + playerBounces);
    }

    public void presentPlayerSwitching(MovementWithSwitch movement) {
        String playerSwitch = format(". On %s there is %s, who returns to %s",
                positionName(movement.finalPosition()),
                movement.switchedPlayer(),
                movement.startPosition());

        outputBoundary.writeOutputLine(
                playerRolls() +
                        playerMoves(movement.startPosition(), movement.finalPosition()) +
                        playerSwitch);
    }

    public void presentRepeatOnGoose(FurtherMovement movement) {
        outputBoundary.writeOutputLine(playerMovesOnTheGoose(movement));
    }

    private String playerMovesOnTheGoose(Movement movement) {
        if (! movement.hasPreviousMovement()) {
            return(playerRolls() + playerMoves(movement.startPosition(), movement.finalPosition()));
        }

        String playerMovesAgain =
                format(" %s moves again and goes to %s", player(), positionName(movement.finalPosition()));

        return playerMovesOnTheGoose(movement.previousMovement()) + playerMovesAgain;
    }

    private String positionName(int position) {
        HashMap<Position, String> names = new HashMap<>();
        names.put(of(START), "Start");
        names.put(of(BRIDGE), "The Bridge");

        String name = names.getOrDefault(of(position), valueOf(position));
        String gooseSuffix = Position.of(position).hasTheGoose() ? ", The Goose." : "";
        return name + gooseSuffix;
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
