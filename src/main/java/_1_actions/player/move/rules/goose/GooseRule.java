package _1_actions.player.move.rules.goose;

import _2_domain.movement.MoveCommand;
import _2_domain.movement.PresentableMovement;
import _2_domain.player.Players;
import _2_domain.player.Position;

import java.util.LinkedList;

public class GooseRule {
    private final Players players;

    public GooseRule(Players players) {

        this.players = players;
    }

    public void apply(MoveCommand command, LinkedList<PresentableMovement> movements) {
        Position position = players.positionOf(command.player());

        if ( ! position.hasTheGoose()) return;

        Position finalPosition = position.plus(command.diceTotal());

        players.setPositionOf(command.player(), finalPosition);

        PresentableMovement gooseMovement = new GooseMovement(position, finalPosition);
        movements.add(gooseMovement);

        this.apply(command, movements);
    }
}
