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
        movement = applyGooseRule(movement, command.diceTotal());
        movement = applyPlayerSwitchRule(movement, command.player());

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

    private Movement applyGooseRule(Movement currentMovement, int diceTotal) {
        if (! currentMovement.endsOnGoose()) return currentMovement;

        Movement repeatedMovement = after(currentMovement)
                .becauseOf(REPEAT_ON_GOOSE)
                .goToPosition(currentMovement.finalPosition() + diceTotal);

        return applyGooseRule(repeatedMovement, diceTotal);
    }

    private Movement applyPlayerSwitchRule(Movement movement, String player) {
        List<String> ecounteredPlayers = players.playersOnPosition(movement.finalPosition());
        if (ecounteredPlayers.isEmpty()) return movement;
        String unluckyPlayer = ecounteredPlayers.get(0);

        players.setPositionOf(unluckyPlayer, players.positionOf(player));
        return new MovementWithSwitch(unluckyPlayer, movement);
    }
}
