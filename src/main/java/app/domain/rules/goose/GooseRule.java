package app.domain.rules.goose;

import app.domain.PositionRule;
import app.domain.movement.Movement;
import app.domain.movement.PlayerOnTurn;
import app.domain.player.Position;

public class GooseRule implements PositionRule {

    @Override
    public void applyTo(PlayerOnTurn playerOnTurn) {
        Position position = playerOnTurn.position();
        Position finalPosition = position.plus(playerOnTurn.dice());

        Movement movement = new GooseMovement(position, finalPosition);
        playerOnTurn.applyMovement(movement);
    }
}
