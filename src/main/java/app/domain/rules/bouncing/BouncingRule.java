package app.domain.rules.bouncing;

import app.domain.movement.Movement;
import app.domain.movement.PlayerTurn;
import app.domain.player.Board;
import app.domain.player.Players;
import app.domain.player.Position;

public class BouncingRule {
    private final Board board;
    private final Players players;

    public BouncingRule(Board board, Players players) {
        this.board = board;
        this.players = players;
    }

    public void apply(PlayerTurn turn) {
        Position lastPosition = players.positionOf(turn.player());

        if ( ! lastPosition.isBeyondWin()) return;

        Position finalPosition = lastPosition.bounced();
        players.setPositionOf(turn.player(), finalPosition);

        Movement bouncing = new BouncingMovement(board, finalPosition);

        turn.add(bouncing);
    }
}
