package app.domain.movement.rules.bouncing;

import app.domain.movement.MoveCommand;
import app.domain.movement.Movement;
import app.domain.player.Players;
import app.domain.player.Position;

import java.util.LinkedList;

public class BouncingRule {
    private final Players players;

    public BouncingRule(Players players) {

        this.players = players;
    }

    public void apply(MoveCommand command, LinkedList<Movement> movements) {
        Position lastPosition = players.positionOf(command.player());

        if ( ! lastPosition.isOverTheVictory()) return;

        Position finalPosition = lastPosition.bounced();
        players.setPositionOf(command.player(), finalPosition);

        Movement bouncing = new BouncingMovement(finalPosition);

        movements.add(bouncing);
    }
}
