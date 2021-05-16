package usecase.move_player;

import domain.Players;
import domain.Position;

import java.util.ArrayList;
import java.util.List;

import static domain.Position.BRIDGE;
import static domain.Position.BRIDGE_TARGET;
import static usecase.move_player.FurtherMovementBuilder.after;
import static usecase.move_player.MovementType.*;
import static usecase.move_player.SimpleMovement.movement;

public class ComputeMovement {
    private final Players players;
    private ArrayList<Movement> movements;

    public ComputeMovement(Players players) {
        this.players = players;
    }

    public List<Movement> fromCommand(MoveCommand command) {
        movements = new ArrayList<>();

        SimpleMovement firstMovement = applyFirstMovementRule(command);
        Movement movement = applyBouncingRule(command, firstMovement);
        movement = applyBridgeRule(command, movement);
        movement = applyGooseRule(command, movement);
        applyPlayerSwitchRule(command, movement);

        return movements;
    }

    private SimpleMovement applyFirstMovementRule(MoveCommand command) {
        Position startPosition = players.positionOf(command.player());
        Position finalPosition = startPosition.plus(command.diceTotal());

        SimpleMovement movement = movement()
                .from(startPosition)
                .to(finalPosition)
                .givenRoll(command.dice())
                .end();

        String player = command.player();
        players.setPositionOf(player, finalPosition);
        movements.add(movement);
        return movement;
    }

    private Movement applyBouncingRule(MoveCommand command, SimpleMovement firstMovement) {
        if (firstMovement.isOverTheVictory()) {
            Position finalPosition = firstMovement.bouncedPosition();
            players.setPositionOf(command.player(), finalPosition);

            Movement bouncing = after(firstMovement)
                    .becauseOf(BOUNCING)
                    .goToPosition(finalPosition);
            movements.add(bouncing);
            return bouncing;
        }

        return firstMovement;
    }

    private Movement applyBridgeRule(MoveCommand command, Movement movement) {
        if (movement.finalPosition().equals(BRIDGE)) {
            players.setPositionOf(command.player(), BRIDGE_TARGET);

            Movement jumpOnBridge = after(movement)
                    .becauseOf(JUMP_ON_BRIDGE)
                    .goToPosition(BRIDGE_TARGET);
            movements.add(jumpOnBridge);
            return jumpOnBridge;
        }

        return movement;
    }

    private Movement applyGooseRule(MoveCommand command, Movement currentMovement) {
        if (! currentMovement.endsOnGoose()) return currentMovement;

        Position finalPosition = currentMovement.finalPosition().plus(command.diceTotal());
        players.setPositionOf(command.player(), finalPosition);

        Movement gooseMovement = after(currentMovement)
                .becauseOf(REPEAT_ON_GOOSE)
                .goToPosition(finalPosition);

        movements.add(gooseMovement);
        return applyGooseRule(command, gooseMovement);
    }

    private void applyPlayerSwitchRule(MoveCommand command, Movement movement) {
        List<String> ecounteredPlayers = players.playersOnSamePositionOf(command.player());
        if (ecounteredPlayers.isEmpty()) return;
        String unluckyPlayer = ecounteredPlayers.get(0);

        Position previousPositionOfCurrentPlayer = movement.startPosition();
        players.setPositionOf(unluckyPlayer, previousPositionOfCurrentPlayer);
        MovementWithSwitch movementWithSwitch = new MovementWithSwitch(unluckyPlayer, movement);
        movements.add(movementWithSwitch);
    }
}
