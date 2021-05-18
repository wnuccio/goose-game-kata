package goosegame.boundary.output;

import goosegame.domain.Position;
import goosegame.usecase.move_player.*;

import java.util.HashMap;

import static goosegame.domain.Position.*;
import static java.lang.String.format;

public class OutputMovementPresenter implements MovementPresenter {
    private final OutputBoundary outputBoundary;

    private MovementView movementView;
    private StringBuilder outputBuilder;

    public OutputMovementPresenter(OutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void presentMovement(MovementView movementView) {
        this.movementView = movementView;
        this.outputBuilder = new StringBuilder();

        for (Movement m: movementView.movements()) {
            m.present(this);
        }
        outputBoundary.writeOutputLine(outputBuilder.toString());
    }

    @Override
    public void presentFirstMovement(FirstMovement movement) {
        int firstDice = movementView.firstDice();
        int secondDice = movementView.secondDice();
        String playerRolls = format("%s rolls %s, %s" + ". ", player(), firstDice, secondDice);
        String playerMoves = format("%s moves from %s to %s", player(), positionName(movement.startPosition()), positionName(movement.finalPosition()));
        String playerWins = movement.isVictory() ? format(". %s Wins!!", player()) : "";

        outputBuilder.append(playerRolls + playerMoves + playerWins);
    }

    @Override
    public void presentJumpOnBridge(JumpOnBridgeMovement movement) {
        String playerJumps = format(". %s jumps to 12", player());

        outputBuilder.append(playerJumps);
    }

    @Override
    public void presentBouncing(BouncingMovement movement) {
        String playerBounces = format(". %s bounces! %s returns to %s",
                player(),
                player(),
                positionName(movement.finalPosition()));

        outputBuilder.append(playerBounces);
    }

    @Override
    public void presentSwitchMovement(SwitchMovement movement) {
        String playerSwitch = format(". On %s there is %s, who returns to %s",
                positionName(movement.startPosition()),
                movement.switchedPlayer(),
                positionName(movement.finalPosition()));

        outputBuilder.append(playerSwitch);
    }

    @Override
    public void presentRepeatOnGoose(GooseMovement movement) {
        String playerMovesAgain = format(" %s moves again and goes to %s",
                player(),
                positionName(movement.finalPosition()));

        outputBuilder.append(playerMovesAgain);
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

    private String player() {
        return movementView.player();
    }
}
