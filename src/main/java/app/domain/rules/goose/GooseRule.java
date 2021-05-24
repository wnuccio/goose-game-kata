package app.domain.rules.goose;

import app.domain.movement.Movement;
import app.domain.movement.PlayerTurn;
import app.domain.player.Players;
import app.domain.player.Position;

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
