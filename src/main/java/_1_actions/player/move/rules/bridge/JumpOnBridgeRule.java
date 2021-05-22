package _1_actions.player.move.rules.bridge;

import _2_domain.movement.MoveCommand;
import _2_domain.movement.PresentableMovement;
import _2_domain.player.Players;
import _2_domain.player.Position;

import java.util.LinkedList;

public class JumpOnBridgeRule {
    private final Players players;

    public JumpOnBridgeRule(Players players) {

        this.players = players;
    }

    public void apply(MoveCommand command, LinkedList<PresentableMovement> movements) {
        Position lastPosition = players.positionOf(command.player());

        PresentableMovement movement = new JumpOnBridgeMovement();

        if (lastPosition.equals(movement.startPosition())) {
            movements.add(movement);
            players.setPositionOf(command.player(), movement.finalPosition());
        }
    }
}
