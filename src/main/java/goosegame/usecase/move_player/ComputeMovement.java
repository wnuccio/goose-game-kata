package goosegame.usecase.move_player;

import goosegame.domain.Players;
import goosegame.domain.Position;

import java.util.ArrayList;
import java.util.List;

import static goosegame.domain.Position.BRIDGE;
import static goosegame.domain.Position.BRIDGE_TARGET;

public class ComputeMovement {
    private final Players players;
    private ArrayList<Movement> movements;

    public ComputeMovement(Players players) {
        this.players = players;
    }

    public List<Movement> fromCommand(MoveCommand command) {
        movements = new ArrayList<>();

        FirstMovement firstMovement = applyFirstMovementRule(command);
        Movement movement = applyBouncingRule(command, firstMovement);
        movement = applyBridgeRule(command, movement);
        movement = applyGooseRule(command, movement);
        applyPlayerSwitchRule(command, movement);

        return movements;
    }

    private FirstMovement applyFirstMovementRule(MoveCommand command) {
        Position startPosition = players.positionOf(command.player());
        Position finalPosition = startPosition.plus(command.diceTotal());

        FirstMovement movement = new FirstMovement(startPosition, finalPosition);

        String player = command.player();
        players.setPositionOf(player, finalPosition);
        movements.add(movement);
        return movement;
    }

    private Movement applyBouncingRule(MoveCommand command, Movement lastMovement) {
        if (!lastMovement.isOverTheVictory()) return lastMovement;

        Position finalPosition = lastMovement.bouncedPosition();
        players.setPositionOf(command.player(), finalPosition);

        Movement bouncing = new BouncingMovement(lastMovement.finalPosition(), finalPosition);

        movements.add(bouncing);
        return bouncing;
    }

    private Movement applyBridgeRule(MoveCommand command, Movement lastMovement) {
        if (!lastMovement.finalPosition().equals(BRIDGE)) return lastMovement;

        players.setPositionOf(command.player(), BRIDGE_TARGET);

        Movement jumpOnBridgeMovement = new JumpOnBridgeMovement(BRIDGE, BRIDGE_TARGET);

        movements.add(jumpOnBridgeMovement);
        return jumpOnBridgeMovement;
    }

    private Movement applyGooseRule(MoveCommand command, Movement lastMovement) {
        if (!lastMovement.endsOnGoose()) return lastMovement;

        Position finalPosition = lastMovement.finalPosition().plus(command.diceTotal());
        players.setPositionOf(command.player(), finalPosition);

        Movement gooseMovement = new GooseMovement(lastMovement.finalPosition(), finalPosition);

        movements.add(gooseMovement);
        return applyGooseRule(command, gooseMovement);
    }

    private Movement applyPlayerSwitchRule(MoveCommand command, Movement lastMovement) {
        List<String> ecounteredPlayers = players.playersOnSamePositionOf(command.player());
        if (ecounteredPlayers.isEmpty()) return lastMovement;

        String unluckyPlayer = ecounteredPlayers.get(0);

        Position unluckyPlayerPosition = players.positionOf(unluckyPlayer);
        Position otherPlayerPreviousPosition = lastMovement.startPosition();
        SwitchMovement switchMovement = new SwitchMovement(
                unluckyPlayer,
                unluckyPlayerPosition,
                otherPlayerPreviousPosition
        );
        players.setPositionOf(unluckyPlayer, otherPlayerPreviousPosition);

        movements.add(switchMovement);
        return lastMovement;
    }
}
