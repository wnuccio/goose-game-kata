package app.domain.rules.bridge;

import app.domain.player.Movement;
import app.domain.player.PlayerOnTurn;
import app.domain.player.Position;
import app.domain.player.PositionRule;

public class JumpOnBridgeRule implements PositionRule {
    private Position finalPosition;

    public JumpOnBridgeRule(Position finalPosition) {
        this.finalPosition = finalPosition;
    }

    @Override
    public void applyTo(PlayerOnTurn playerOnTurn) {
        Position position = playerOnTurn.position();
        Movement movement = new JumpOnBridgeMovement(position, this.finalPosition);
        playerOnTurn.applyMovement(movement);
    }
}
