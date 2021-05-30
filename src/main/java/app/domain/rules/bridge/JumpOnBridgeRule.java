package app.domain.rules.bridge;

import app.domain.player.*;

public class JumpOnBridgeRule implements PositionRule {
    private final Board board;

    public JumpOnBridgeRule(Board board) {
        this.board = board;
    }

    @Override
    public void applyTo(PlayerOnTurn playerOnTurn) {
        Position position = playerOnTurn.position();
        Position finalPosition = board.position(12);
        Movement movement = new JumpOnBridgeMovement(position, finalPosition);
        playerOnTurn.applyMovement(movement);
    }
}
