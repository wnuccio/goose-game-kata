package _1_actions.player.move.rules.bouncing;

import _2_domain.movement.MoveCommand;
import _2_domain.movement.PresentableMovement;
import _2_domain.player.Players;
import _2_domain.player.Position;

import java.util.LinkedList;

public class BouncingRule {
    private final Players players;

    public BouncingRule(Players players) {

        this.players = players;
    }

    public void apply(MoveCommand command, LinkedList<PresentableMovement> movements) {
        Position lastPosition = players.positionOf(command.player());

        if ( ! lastPosition.isOverTheVictory()) return;

        Position finalPosition = lastPosition.bounced();
        players.setPositionOf(command.player(), finalPosition);

        PresentableMovement bouncing = new BouncingMovement(finalPosition);

        movements.add(bouncing);
    }
}