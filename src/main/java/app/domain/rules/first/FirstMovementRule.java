package app.domain.rules.first;

import app.domain.movement.PlayerTurn;
import app.domain.player.Position;

public class FirstMovementRule {

    public void apply(PlayerTurn turn) {
        Position startPosition = turn.positionOfPlayer();
        Position finalPosition = startPosition.plus(turn.diceTotal());

        FirstMovement movement = new FirstMovement(startPosition, finalPosition);

        turn.add(movement);
        turn.setPositionOfPlayer(movement.finalPosition());
    }
}
