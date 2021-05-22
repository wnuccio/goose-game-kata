package _1_actions.player.move.rules.bridge;

import _2_domain.movement.Movement;
import _2_domain.movement.PlayerTurn;
import _2_domain.player.Players;
import _2_domain.player.Position;

public class JumpOnBridgeRule {
    private final Players players;

    public JumpOnBridgeRule(Players players) {

        this.players = players;
    }

    public void apply(PlayerTurn turn) {
        Position lastPosition = players.positionOf(turn.player());

        Movement movement = new JumpOnBridgeMovement();

        if (lastPosition.equals(movement.startPosition())) {
            turn.add(movement);
            players.setPositionOf(turn.player(), movement.finalPosition());
        }
    }
}
