package boundary.output;

import domain.Position;
import usecase.move_player.*;

import java.util.HashMap;

import static domain.Position.*;
import static java.lang.String.format;

public class OutputMovementPresenter implements MovementPresenter {
    private final OutputBoundary outputBoundary;
    private MovementView movementView;

    public OutputMovementPresenter(OutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void presentMovement(MovementView movementView) {
        this.movementView = movementView;
        movementView.present(this);
    }

    private String playerRolls() {
        return format("%s rolls %s, %s" + ". ", player(), firstDice(), secondDice());
    }

    private String playerMoves(Position from, Position to) {
        return format("%s moves from %s to %s", player(), positionName(from), positionName(to));
    }

    @Override
    public void presentSimpleMovement(SimpleMovement movement) {
        String playerMoves = playerMoves(movement.startPosition(), movement.finalPosition());
        String playerWins = movement.isVictory() ? format(". %s Wins!!", player()) : "";

        outputBoundary.writeOutputLine(playerRolls() + playerMoves + playerWins);
    }

    @Override
    public void presentJumpOnBridge(FurtherMovement movement) {
        String playerMoves = playerMoves(movement.startPosition(), BRIDGE);
        String playerJumps = format(". %s jumps to 12", player());

        outputBoundary.writeOutputLine(playerRolls() + playerMoves + playerJumps);
    }

    @Override
    public void presentBouncing(FurtherMovement movement) {
        String playerMoves = playerMoves(movement.startPosition(), WIN);
        String playerBounces = format(". %s bounces! %s returns to %s", player(), player(), movement.finalPosition().value());

        outputBoundary.writeOutputLine( playerRolls() + playerMoves + playerBounces);
    }

    @Override
    public void presentPlayerSwitching(MovementWithSwitch movement) {
        String playerSwitch = format(". On %s there is %s, who returns to %s",
                positionName(movement.finalPosition()),
                movement.switchedPlayer(),
                movement.startPosition().value());

        outputBoundary.writeOutputLine(
                playerRolls() +
                        playerMoves(movement.startPosition(), movement.finalPosition()) +
                        playerSwitch);
    }

    @Override
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

    private String positionName(Position position) {
        HashMap<Position, String> names = new HashMap<>();
        names.put(START, "Start");
        names.put(BRIDGE, "The Bridge");

        String name = names.getOrDefault(position, position.value());
        String gooseSuffix = position.hasTheGoose() ? ", The Goose." : "";
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
