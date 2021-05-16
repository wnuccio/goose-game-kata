package usecase.move_player;

import domain.Players;

import java.util.List;

import static domain.Position.*;
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
        int startPosition = players.positionOf(command.player());
        int finalPosition = startPosition + command.diceTotal();

        SimpleMovement movement = movement()
                .from(startPosition)
                .to(finalPosition)
                .givenRoll(command.dice())
                .end();

        players.setPositionOf(command.player(), finalPosition);
        return movement;
    }

    private Movement applyBouncingRule(MoveCommand command, SimpleMovement firstMovement) {
        if (firstMovement.isOverTheVictory()) {
            int finalPosition = WIN_POSITION - (firstMovement.finalPosition() - WIN_POSITION);
            players.setPositionOf(command.player(), finalPosition);

            return after(firstMovement)
                    .becauseOf(BOUNCING)
                    .goToPosition(finalPosition);
        }

        return firstMovement;
    }

    private Movement applyBridgeRule(MoveCommand command, Movement movement) {
        if (movement.finalPosition() == BRIDGE) {
            players.setPositionOf(command.player(), BRIDGE_TARGET);

            return after(movement)
                    .becauseOf(JUMP_ON_BRIDGE)
                    .goToPosition(BRIDGE_TARGET);
        }

        return movement;
    }

    private Movement applyGooseRule(MoveCommand command, Movement currentMovement) {
        if (! currentMovement.endsOnGoose()) return currentMovement;

        int finalPosition = currentMovement.finalPosition() + command.diceTotal();
        players.setPositionOf(command.player(), finalPosition);

        Movement repeatedMovement = after(currentMovement)
                .becauseOf(REPEAT_ON_GOOSE)
                .goToPosition(finalPosition);

        return applyGooseRule(command, repeatedMovement);
    }

    private Movement applyPlayerSwitchRule(MoveCommand command, Movement movement) {
        List<String> ecounteredPlayers = players.playersOnSamePositionOf(command.player());
        if (ecounteredPlayers.isEmpty()) return movement;
        String unluckyPlayer = ecounteredPlayers.get(0);

        int previousPositionOfCurrentPlayer = movement.startPosition();
        players.setPositionOf(unluckyPlayer, previousPositionOfCurrentPlayer);
        return new MovementWithSwitch(unluckyPlayer, movement);
    }
}
