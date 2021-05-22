package _1_actions.player.move.rules.bouncing;

import _2_domain.movement.Movement;
import _2_domain.movement.PlayerTurn;
import _2_domain.player.Players;
import _2_domain.player.Position;

public class BouncingRule {
    private final Players players;

    public BouncingRule(Players players) {

        this.players = players;
    }

    public void apply(PlayerTurn turn) {
        Position lastPosition = players.positionOf(turn.player());

        if ( ! lastPosition.isOverTheVictory()) return;

        Position finalPosition = lastPosition.bounced();
        players.setPositionOf(turn.player(), finalPosition);

        Movement bouncing = new BouncingMovement(finalPosition);

        turn.add(bouncing);
    }
}
