package _1_actions.player.move.rules.first;

import _1_actions.player.move.presenter.PresentableMovement;
import _2_domain.MoveCommand;
import _2_domain.Players;
import _2_domain.Position;

import java.util.List;

public class FirstMovementRule {
    private final Players players;

    public FirstMovementRule(Players players) {

        this.players = players;
    }

    public void apply(MoveCommand command, List<PresentableMovement> movements) {
        Position startPosition = players.positionOf(command.player());
        Position finalPosition = startPosition.plus(command.diceTotal());

        FirstMovement movement = new FirstMovement(startPosition, finalPosition);

        String player = command.player();
        players.setPositionOf(player, finalPosition);
        movements.add(movement);
    }
}
