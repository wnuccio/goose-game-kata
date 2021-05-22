package _1_actions.player.move.rules.goose;

import _2_domain.movement.Movement;
import _2_domain.movement.PlayerTurn;
import _2_domain.player.Players;
import _2_domain.player.Position;

public class GooseRule {
    private final Players players;

    public GooseRule(Players players) {

        this.players = players;
    }

    public void apply(PlayerTurn turn) {
        Position position = players.positionOf(turn.player());

        if ( ! position.hasTheGoose()) return;

        Position finalPosition = position.plus(turn.diceTotal());

        players.setPositionOf(turn.player(), finalPosition);

        Movement gooseMovement = new GooseMovement(position, finalPosition);
        turn.add(gooseMovement);

        this.apply(turn);
    }
}
