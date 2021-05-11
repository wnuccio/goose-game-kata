package usecase.move_player;

import domain.Players;

import java.util.List;

import static domain.Position.*;
import static usecase.move_player.FurtherMovementBuilder.after;
import static usecase.move_player.MovementType.*;
import static usecase.move_player.SimpleMovement.movement;

public class ComputeMovement {
    private Players players;

    public ComputeMovement(Players players) {
        this.players = players;
    }

    public Movement fromCommand(MoveCommand command) {
        SimpleMovement firstMovement = buildFirstMovement(command);

        Movement movement = applyBouncingRule(firstMovement);
        movement = applyBridgeRule(movement);
        movement = applyGooseRule(movement);
        movement = applyPlayerSwitchRule(movement);

        players.setPositionOf(command.player(), movement.finalPosition());
        return movement;
    }

    private SimpleMovement buildFirstMovement(MoveCommand command) {
        return movement()
                .from(players.positionOf(command.player()))
                .givenRoll(command.dice())
                .end();
    }

    private Movement applyBouncingRule(SimpleMovement firstMovement) {
        if (firstMovement.finalPosition() > WIN_POSITION) {
            return after(firstMovement)
                    .becauseOf(BOUNCING)
                    .goToPosition(WIN_POSITION - (firstMovement.finalPosition() - WIN_POSITION));
        }

        return firstMovement;
    }

    private Movement applyBridgeRule(Movement movement) {
        if (movement.finalPosition() == BRIDGE) {
            return after(movement)
                    .becauseOf(JUMP_ON_BRIDGE)
                    .goToPosition(BRIDGE_TARGET);
        }

        return movement;
    }

    private Movement applyGooseRule(Movement previousMovement) {
        if (! previousMovement.endsOnGoose()) return previousMovement;

        Movement repeatedMovement = after(previousMovement)
                .becauseOf(REPEAT_ON_GOOSE)
                .goToPosition(previousMovement.finalPosition() + previousMovement.diceTotal());

        return applyGooseRule(repeatedMovement);
    }

    private Movement applyPlayerSwitchRule(Movement movement) {
        List<String> ecounteredPlayers = players.playersOnPosition(movement.finalPosition());
        if (ecounteredPlayers.isEmpty()) return movement;
        String unluckyPlayer = ecounteredPlayers.get(0);

        players.setPositionOf(unluckyPlayer, movement.startPosition());
        return new MovementWithSwitch(unluckyPlayer, movement);
    }
}
