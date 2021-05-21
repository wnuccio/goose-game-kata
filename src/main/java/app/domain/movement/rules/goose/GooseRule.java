package app.domain.movement.rules.goose;

import app.domain.movement.MoveCommand;
import app.domain.movement.Movement;
import app.domain.player.Players;
import app.domain.player.Position;

import java.util.LinkedList;

public class GooseRule {
    private final Players players;

    public GooseRule(Players players) {

        this.players = players;
    }

    public void apply(MoveCommand command, LinkedList<Movement> movements) {
        Position position = players.positionOf(command.player());

        if ( ! position.hasTheGoose()) return;

        Position finalPosition = position.plus(command.diceTotal());

        players.setPositionOf(command.player(), finalPosition);

        Movement gooseMovement = new GooseMovement(position, finalPosition);
        movements.add(gooseMovement);

        this.apply(command, movements);
    }
}
