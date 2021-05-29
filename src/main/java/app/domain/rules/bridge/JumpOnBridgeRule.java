package app.domain.rules.bridge;

import app.domain.PositionRule;
import app.domain.movement.Movement;
import app.domain.player.Board;
import app.domain.player.Player;

public class JumpOnBridgeRule implements PositionRule {
    private final Board board;

    public JumpOnBridgeRule(Board board) {
        this.board = board;
    }

    @Override
    public void applyTo(Player player) {
        Movement movement = new JumpOnBridgeMovement(board);
        player.applyMovement(movement);
    }
}
