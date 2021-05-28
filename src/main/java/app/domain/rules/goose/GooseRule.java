package app.domain.rules.goose;

import app.domain.movement.Movement;
import app.domain.movement.PlayerOnTurn;
import app.domain.player.Position;

public class GooseRule {

    public void apply(PlayerOnTurn playerOnTurn) {
        if ( !playerOnTurn.isOnTheGoose()) return;

        Position position = playerOnTurn.position();
        Position finalPosition = position.plus(playerOnTurn.dice());

        Movement movement = new GooseMovement(position, finalPosition);
        playerOnTurn.applyMovement(movement);

        this.apply(playerOnTurn);
    }
}
