package _2_domain.rules.bouncing;

import _2_domain.movement.Movement;
import _2_domain.movement.PlayerTurn;
import _2_domain.player.Board;
import _2_domain.player.Players;
import _2_domain.player.Position;

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
