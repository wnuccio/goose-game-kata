package goosegame.usecase.move_player;

import goosegame.domain.Players;
import goosegame.domain.Position;

import java.util.ArrayList;
import java.util.List;

import static goosegame.domain.Position.BRIDGE;
import static goosegame.domain.Position.BRIDGE_TARGET;
import static goosegame.usecase.move_player.FurtherMovementBuilder.after;
import static goosegame.usecase.move_player.MovementType.*;
import static goosegame.usecase.move_player.SimpleMovement.movement;

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

    private Movement applyBouncingRule(MoveCommand command, Movement lastMovement) {
        if (lastMovement.isOverTheVictory()) {
            Position finalPosition = lastMovement.bouncedPosition();
            players.setPositionOf(command.player(), finalPosition);

            Movement bouncing = after(lastMovement)
                    .becauseOf(BOUNCING)
                    .goToPosition(finalPosition);
            movements.add(bouncing);
            return bouncing;
        }

        return lastMovement;
    }

    private Movement applyBridgeRule(MoveCommand command, Movement lastMovement) {
        if (lastMovement.finalPosition().equals(BRIDGE)) {
            players.setPositionOf(command.player(), BRIDGE_TARGET);

            Movement jumpOnBridge = after(lastMovement)
                    .becauseOf(JUMP_ON_BRIDGE)
                    .goToPosition(BRIDGE_TARGET);
            movements.add(jumpOnBridge);
            return jumpOnBridge;
        }

        return lastMovement;
    }

    private Movement applyGooseRule(MoveCommand command, Movement lastMovement) {
        if (! lastMovement.endsOnGoose()) return lastMovement;

        Position finalPosition = lastMovement.finalPosition().plus(command.diceTotal());
        players.setPositionOf(command.player(), finalPosition);

        Movement gooseMovement = after(lastMovement)
                .becauseOf(REPEAT_ON_GOOSE)
                .goToPosition(finalPosition);

        movements.add(gooseMovement);
        return applyGooseRule(command, gooseMovement);
    }

    private void applyPlayerSwitchRule(MoveCommand command, Movement lastMovement) {
        List<String> ecounteredPlayers = players.playersOnSamePositionOf(command.player());
        if (ecounteredPlayers.isEmpty()) return;
        String unluckyPlayer = ecounteredPlayers.get(0);

        Position previousPositionOfCurrentPlayer = lastMovement.startPosition();
        players.setPositionOf(unluckyPlayer, previousPositionOfCurrentPlayer);
        MovementWithSwitch movementWithSwitch = new MovementWithSwitch(unluckyPlayer, lastMovement);
        movements.add(movementWithSwitch);
    }
}
