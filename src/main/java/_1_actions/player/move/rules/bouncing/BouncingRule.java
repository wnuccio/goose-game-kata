package _1_actions.player.move.rules.bouncing;

import _1_actions.player.move.presenter.PresentableMovement;
import _2_domain.MoveCommand;
import _2_domain.Players;
import _2_domain.Position;

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
