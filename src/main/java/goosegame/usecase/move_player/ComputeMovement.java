package goosegame.usecase.move_player;

import goosegame.domain.Players;
import goosegame.domain.Position;

import java.util.LinkedList;
import java.util.List;

import static goosegame.domain.Position.BRIDGE;
import static goosegame.domain.Position.BRIDGE_TARGET;

public class ComputeMovement {
    private final Players players;

    public ComputeMovement(Players players) {
        this.players = players;
    }

    public List<Movement> fromCommand(MoveCommand command) {
        LinkedList<Movement> movements = new LinkedList<>();

        new FirstMovementRule(players).apply(command, movements);
        new BouncingRule(players).apply(command, movements);
        applyBridgeRule(command, movements);
        applyGooseRule(command, movements);
        applyPlayerSwitchRule(command, movements);

        return movements;
    }

    private Movement applyBridgeRule(MoveCommand command, LinkedList<Movement> movements) {
        Movement lastMovement = movements.getLast();
        if (!lastMovement.finalPosition().equals(BRIDGE)) return lastMovement;

        players.setPositionOf(command.player(), BRIDGE_TARGET);

        Movement jumpOnBridgeMovement = new JumpOnBridgeMovement();

        movements.add(jumpOnBridgeMovement);
        return jumpOnBridgeMovement;
    }

    private Movement applyGooseRule(MoveCommand command, LinkedList<Movement> movements) {
        Movement lastMovement = movements.getLast();
        if (!lastMovement.endsOnGoose()) return lastMovement;

        Position finalPosition = lastMovement.finalPosition().plus(command.diceTotal());
        players.setPositionOf(command.player(), finalPosition);

        Movement gooseMovement = new GooseMovement(lastMovement.finalPosition(), finalPosition);

        movements.add(gooseMovement);
        return applyGooseRule(command, movements);
    }

    private Movement applyPlayerSwitchRule(MoveCommand command, LinkedList<Movement> movements) {
        Movement lastMovement = movements.getLast();
        List<String> encounteredPlayers = players.playersOnSamePositionOf(command.player());
        if (encounteredPlayers.isEmpty()) return lastMovement;

        String unluckyPlayer = encounteredPlayers.get(0);

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
