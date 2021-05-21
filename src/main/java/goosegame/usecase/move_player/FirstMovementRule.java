package goosegame.usecase.move_player;

import goosegame.domain.Players;
import goosegame.domain.Position;

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
