package _1_actions.player.move.rules.first;

import _2_domain.movement.PlayerTurn;
import _2_domain.player.Players;
import _2_domain.player.Position;

public class FirstMovementRule {
    private final Players players;

    public FirstMovementRule(Players players) {

        this.players = players;
    }

    public void apply(PlayerTurn turn) {
        Position startPosition = players.positionOf(turn.player());
        Position finalPosition = startPosition.plus(turn.diceTotal());

        FirstMovement movement = new FirstMovement(startPosition, finalPosition);

        players.setPositionOf(turn.player(), finalPosition);
        turn.add(movement);
    }
}
