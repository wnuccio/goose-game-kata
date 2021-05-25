package app.domain.rules.first;

import app.domain.movement.PlayerOnTurn;
import app.domain.player.Position;

public class FirstMovementRule {

    public void apply(PlayerOnTurn playerOnTurn) {
        Position startPosition = playerOnTurn.positionOfPlayer();
        Position finalPosition = startPosition.plus(playerOnTurn.diceTotal());

        FirstMovement movement = new FirstMovement(startPosition, finalPosition);
        playerOnTurn.applyMovement(movement);
    }
}
