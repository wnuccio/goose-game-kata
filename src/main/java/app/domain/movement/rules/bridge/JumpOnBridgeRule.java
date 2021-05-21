package app.domain.movement.rules.bridge;

import app.domain.movement.MoveCommand;
import app.domain.movement.Movement;
import app.domain.player.Players;
import app.domain.player.Position;

import java.util.LinkedList;

public class JumpOnBridgeRule {
    private final Players players;

    public JumpOnBridgeRule(Players players) {

        this.players = players;
    }

    public void apply(MoveCommand command, LinkedList<Movement> movements) {
        Position lastPosition = players.positionOf(command.player());

        Movement movement = new JumpOnBridgeMovement();

        if (lastPosition.equals(movement.startPosition())) {
            movements.add(movement);
            players.setPositionOf(command.player(), movement.finalPosition());
        }
    }
}
