package goosegame.boundary.output;

import goosegame.domain.Position;
import goosegame.usecase.move_player.*;

import java.util.HashMap;

import static goosegame.domain.Position.*;
import static java.lang.String.format;

public class OutputMovementPresenter implements MovementPresenter {
    private final OutputBoundary outputBoundary;

    private MovementView movementView;
    private StringBuilder stringBuilder;

    public OutputMovementPresenter(OutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void presentMovement(MovementView movementView) {
        this.movementView = movementView;
        this.stringBuilder = new StringBuilder();

        for (Movement m: movementView.movements()) {
            m.present(this);
        }
        outputBoundary.writeOutputLine(stringBuilder.toString());
    }

    @Override
    public void presentFirstMovement(FirstMovement movement) {
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
                positionName(movement.startPosition()),
                movement.switchedPlayer(),
                positionName(movement.finalPosition()));

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
