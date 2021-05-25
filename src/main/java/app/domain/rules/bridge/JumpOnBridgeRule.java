package app.domain.rules.bridge;

import app.domain.movement.Movement;
import app.domain.movement.PlayerOnTurn;
import app.domain.player.Board;
import app.domain.player.Position;

public class JumpOnBridgeRule {
    private final Board board;

    public JumpOnBridgeRule(Board board) {
        this.board = board;
    }

    public void apply(PlayerOnTurn playerOnTurn) {
        Position lastPosition = playerOnTurn.positionOfPlayer();

        if ( ! lastPosition.equals(board.bridge())) return;

        Movement movement = new JumpOnBridgeMovement(board);
        playerOnTurn.applyMovement(movement);
    }
}
