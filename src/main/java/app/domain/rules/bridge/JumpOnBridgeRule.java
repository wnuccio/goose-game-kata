package app.domain.rules.bridge;

import app.domain.player.Board;
import app.domain.player.Movement;
import app.domain.player.PlayerOnTurn;
import app.domain.player.PositionRule;

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
