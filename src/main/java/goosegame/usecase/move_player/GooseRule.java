package goosegame.usecase.move_player;

import goosegame.domain.Players;
import goosegame.domain.Position;

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
