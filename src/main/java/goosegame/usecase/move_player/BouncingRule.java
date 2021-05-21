package goosegame.usecase.move_player;

import goosegame.domain.Players;
import goosegame.domain.Position;

import java.util.LinkedList;

public class BouncingRule {
    private final Players players;

    public BouncingRule(Players players) {

        this.players = players;
    }

    public void apply(MoveCommand command, LinkedList<Movement> movements) {
        Position lastPosition = players.positionOf(command.player());

        if ( ! lastPosition.isOverTheVictory()) return;

        Position finalPosition = lastPosition.bounced();
        players.setPositionOf(command.player(), finalPosition);

        Movement bouncing = new BouncingMovement(finalPosition);

        movements.add(bouncing);
    }
}
