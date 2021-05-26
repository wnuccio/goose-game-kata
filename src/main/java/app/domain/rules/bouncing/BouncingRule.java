package app.domain.rules.bouncing;

import app.domain.movement.Movement;
import app.domain.movement.PlayerOnTurn;
import app.domain.player.Board;
import app.domain.player.Position;

public class BouncingRule {
    private final Board board;

    public BouncingRule(Board board) {
        this.board = board;
    }

    public void apply(PlayerOnTurn playerOnTurn) {
        Position lastPosition = playerOnTurn.position();

        if ( ! lastPosition.isBeyondWin()) return;

        Position finalPosition = lastPosition.bounced();
        Movement movement = new BouncingMovement(board, finalPosition);

        playerOnTurn.applyMovement(movement);
    }
}
