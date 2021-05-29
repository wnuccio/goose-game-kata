package app.domain.rules.bridge;

import app.domain.PositionRule;
import app.domain.movement.Movement;
import app.domain.movement.PlayerOnTurn;
import app.domain.player.Board;

public class JumpOnBridgeRule implements PositionRule {
    private final Board board;

    public JumpOnBridgeRule(Board board) {
        this.board = board;
    }

    @Override
    public void applyTo(PlayerOnTurn playerOnTurn) {
        Movement movement = new JumpOnBridgeMovement(board);
        playerOnTurn.applyMovement(movement);
    }
}
