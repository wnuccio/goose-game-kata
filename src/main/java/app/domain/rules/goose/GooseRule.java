package app.domain.rules.goose;

import app.domain.player.Movement;
import app.domain.player.PlayerOnTurn;
import app.domain.player.Position;
import app.domain.player.PositionRule;

public class GooseRule implements PositionRule {

    @Override
    public void applyTo(PlayerOnTurn playerOnTurn) {
        Position position = playerOnTurn.position();
        Position finalPosition = position.plusTruncatedToWin(playerOnTurn.dice());
        Movement movement = new GooseMovement(position, finalPosition);
        playerOnTurn.applyMovement(movement);
    }
}
