package _1_actions.player.move.rules.bridge;

import _1_actions.player.move.presenter.PresentableMovement;
import _2_domain.MoveCommand;
import _2_domain.Players;
import _2_domain.Position;

import java.util.LinkedList;

public class JumpOnBridgeRule {
    private final Players players;

    public JumpOnBridgeRule(Players players) {

        this.players = players;
    }

    public void apply(MoveCommand command, LinkedList<PresentableMovement> movements) {
        Position lastPosition = players.positionOf(command.player());

        PresentableMovement movement = new JumpOnBridgeMovement();

        if (lastPosition.equals(movement.startPosition())) {
            movements.add(movement);
            players.setPositionOf(command.player(), movement.finalPosition());
        }
    }
}
