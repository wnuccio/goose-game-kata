package boundary.output;

import domain.Position;
import usecase.move_player.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static domain.Position.*;
import static java.lang.String.format;

public class OutputMovementPresenter implements MovementPresenter {
    private final OutputBoundary outputBoundary;

    private MovementView movementView;
    private StringBuilder stringBuilder;

    public OutputMovementPresenter(OutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    private LinkedList<Movement> expandDecoratedMovementToList(Movement currentMovement) {
        if ( ! currentMovement.hasPreviousMovement()) {
            LinkedList<Movement> firstMovement = new LinkedList<>();
            firstMovement.add(currentMovement);
            return firstMovement;
        }

        LinkedList<Movement> movements = new LinkedList<>();
        LinkedList<Movement> previousMovements = expandDecoratedMovementToList(currentMovement.previousMovement());
        movements.addAll(previousMovements);
        movements.addLast(currentMovement);
        return movements;
    }

    @Override
    public void presentMovement(MovementView movementView) {
        this.movementView = movementView;
        this.stringBuilder = new StringBuilder();

        List<Movement> movements = expandDecoratedMovementToList(movementView.movement());
        for (Movement m: movements) {
            m.present(this);
        }
        outputBoundary.writeOutputLine(stringBuilder.toString());
    }

    @Override
    public void presentSimpleMovement(SimpleMovement movement) {
        String playerRolls = format("%s rolls %s, %s" + ". ", player(), firstDice(), secondDice());
        String playerMoves = format("%s moves from %s to %s", player(), positionName(movement.startPosition()), positionName(movement.finalPosition()));
        String playerWins = movement.isVictory() ? format(". %s Wins!!", player()) : "";

        stringBuilder.append(playerRolls + playerMoves + playerWins);
    }

    @Override
    public void presentJumpOnBridge(FurtherMovement movement) {
        String playerJumps = format(". %s jumps to 12", player());

        stringBuilder.append(playerJumps);
    }

    @Override
    public void presentBouncing(FurtherMovement movement) {
        String playerBounces = format(". %s bounces! %s returns to %s",
                player(),
                player(),
                positionName(movement.finalPosition()));

        stringBuilder.append(playerBounces);
    }

    @Override
    public void presentPlayerSwitching(MovementWithSwitch movement) {
        String playerSwitch = format(". On %s there is %s, who returns to %s",
                positionName(movement.finalPosition()),
                movement.switchedPlayer(),
                positionName(movement.startPosition()));

        stringBuilder.append(playerSwitch);
    }

    @Override
    public void presentRepeatOnGoose(FurtherMovement movement) {
        String playerMovesAgain = format(" %s moves again and goes to %s",
                player(),
                positionName(movement.finalPosition()));

        stringBuilder.append(playerMovesAgain);
    }

    private String positionName(Position position) {
        if (position.isOverTheVictory()) return WIN.value();

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
