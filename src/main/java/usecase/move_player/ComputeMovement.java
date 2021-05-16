package usecase.move_player;

import domain.Players;
import domain.Position;

import java.util.List;

import static domain.Position.BRIDGE;
import static domain.Position.BRIDGE_TARGET;
import static usecase.move_player.FurtherMovementBuilder.after;
import static usecase.move_player.MovementType.*;
import static usecase.move_player.SimpleMovement.movement;

public class ComputeMovement {
    private final Players players;

    public ComputeMovement(Players players) {
        this.players = players;
    }

    public Movement fromCommand(MoveCommand command) {
        SimpleMovement firstMovement = applyFirstMovementRule(command);
        Movement movement = applyBouncingRule(command, firstMovement);
        movement = applyBridgeRule(command, movement);
        movement = applyGooseRule(command, movement);
        movement = applyPlayerSwitchRule(command, movement);

        return movement;
    }

    private SimpleMovement applyFirstMovementRule(MoveCommand command) {
        Position startPosition = players.positionOf(command.player());
        Position finalPosition = startPosition.plus(command.diceTotal());

        SimpleMovement movement = movement()
                .from(startPosition.value())
                .to(finalPosition.value())
                .givenRoll(command.dice())
                .end();

        String player = command.player();
        players.setPositionOf(player, finalPosition);
        return movement;
    }

    private Movement applyBouncingRule(MoveCommand command, SimpleMovement firstMovement) {
        if (firstMovement.isOverTheVictory()) {
            Position finalPosition = firstMovement.bouncedPosition();
            players.setPositionOf(command.player(), finalPosition);

            return after(firstMovement)
                    .becauseOf(BOUNCING)
                    .goToPosition(finalPosition.value());
        }

        return firstMovement;
    }

    private Movement applyBridgeRule(MoveCommand command, Movement movement) {
        if (movement.finalPosition().equals(BRIDGE)) {
            players.setPositionOf(command.player(), BRIDGE_TARGET);

            return after(movement)
                    .becauseOf(JUMP_ON_BRIDGE)
                    .goToPosition(BRIDGE_TARGET.value());
        }

        return movement;
    }

    private Movement applyGooseRule(MoveCommand command, Movement currentMovement) {
        if (! currentMovement.endsOnGoose()) return currentMovement;

        Position finalPosition = currentMovement.finalPosition().plus(command.diceTotal());
        players.setPositionOf(command.player(), finalPosition);

        Movement repeatedMovement = after(currentMovement)
                .becauseOf(REPEAT_ON_GOOSE)
                .goToPosition(finalPosition.value());

        return applyGooseRule(command, repeatedMovement);
    }

    private Movement applyPlayerSwitchRule(MoveCommand command, Movement movement) {
        List<String> ecounteredPlayers = players.playersOnSamePositionOf(command.player());
        if (ecounteredPlayers.isEmpty()) return movement;
        String unluckyPlayer = ecounteredPlayers.get(0);

        Position previousPositionOfCurrentPlayer = movement.startPosition();
        players.setPositionOf(unluckyPlayer, previousPositionOfCurrentPlayer);
        return new MovementWithSwitch(unluckyPlayer, movement);
    }
}
