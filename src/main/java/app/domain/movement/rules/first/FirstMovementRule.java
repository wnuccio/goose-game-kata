package app.domain.movement.rules.first;

import app.domain.movement.MoveCommand;
import app.domain.movement.Movement;
import app.domain.player.Players;
import app.domain.player.Position;

import java.util.List;

public class FirstMovementRule {
    private final Players players;

    public FirstMovementRule(Players players) {

        this.players = players;
    }

    public void apply(MoveCommand command, List<Movement> movements) {
        Position startPosition = players.positionOf(command.player());
        Position finalPosition = startPosition.plus(command.diceTotal());

        FirstMovement movement = new FirstMovement(startPosition, finalPosition);

        String player = command.player();
        players.setPositionOf(player, finalPosition);
        movements.add(movement);
    }
}
