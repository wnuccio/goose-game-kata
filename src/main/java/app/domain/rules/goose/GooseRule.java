package app.domain.rules.goose;

import app.domain.movement.Movement;
import app.domain.movement.PlayerTurn;
import app.domain.player.Position;

public class GooseRule {

    public void apply(PlayerTurn turn) {
        Position position = turn.positionOfPlayer();

        if ( ! position.hasTheGoose()) return;

        Position finalPosition = position.plus(turn.diceTotal());

        Movement movement = new GooseMovement(position, finalPosition);

        turn.add(movement);
        turn.setPositionOfPlayer(movement.finalPosition());

        this.apply(turn);
    }
}
