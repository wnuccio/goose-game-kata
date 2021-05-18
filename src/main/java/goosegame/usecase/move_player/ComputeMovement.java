package goosegame.usecase.move_player;

import goosegame.domain.Players;
import goosegame.domain.Position;

import java.util.ArrayList;
import java.util.List;

import static goosegame.domain.Position.BRIDGE;
import static goosegame.domain.Position.BRIDGE_TARGET;
import static goosegame.usecase.move_player.FurtherMovementBuilder.furtherMovement;
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
        if (!lastMovement.isOverTheVictory()) return lastMovement;

        Position finalPosition = lastMovement.bouncedPosition();
        players.setPositionOf(command.player(), finalPosition);

        Movement bouncing = furtherMovement()
                .becauseOf(BOUNCING)
                .from(lastMovement.finalPosition())
                .goTo(finalPosition);

        movements.add(bouncing);
        return bouncing;
    }

    private Movement applyBridgeRule(MoveCommand command, Movement lastMovement) {
        if (!lastMovement.finalPosition().equals(BRIDGE)) return lastMovement;

        players.setPositionOf(command.player(), BRIDGE_TARGET);

        Movement jumpOnBridge = furtherMovement()
                .becauseOf(JUMP_ON_BRIDGE)
                .from(lastMovement.finalPosition())
                .goTo(BRIDGE_TARGET);

        movements.add(jumpOnBridge);
        return jumpOnBridge;
    }

    private Movement applyGooseRule(MoveCommand command, Movement lastMovement) {
        if (!lastMovement.endsOnGoose()) return lastMovement;

        Position finalPosition = lastMovement.finalPosition().plus(command.diceTotal());
        players.setPositionOf(command.player(), finalPosition);

        Movement gooseMovement = furtherMovement()
                .becauseOf(REPEAT_ON_GOOSE)
                .from(lastMovement.finalPosition())
                .goTo(finalPosition);

        movements.add(gooseMovement);
        return applyGooseRule(command, gooseMovement);
    }

    private Movement applyPlayerSwitchRule(MoveCommand command, Movement lastMovement) {
        List<String> ecounteredPlayers = players.playersOnSamePositionOf(command.player());
        if (ecounteredPlayers.isEmpty()) return lastMovement;

        String unluckyPlayer = ecounteredPlayers.get(0);

        Position unluckyPlayerPosition = players.positionOf(unluckyPlayer);
        Position otherPlayerPreviousPosition = lastMovement.startPosition();
        MovementWithSwitch movementWithSwitch = new MovementWithSwitch(
                unluckyPlayer,
                unluckyPlayerPosition,
                otherPlayerPreviousPosition
        );
        players.setPositionOf(unluckyPlayer, otherPlayerPreviousPosition);

        movements.add(movementWithSwitch);
        return lastMovement;
    }
}
