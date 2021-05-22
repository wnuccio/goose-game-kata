package _1_actions.player.move.rules.goose;

import _1_actions.player.move.presenter.PresentableMovement;
import _2_domain.MoveCommand;
import _2_domain.Players;
import _2_domain.Position;

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
