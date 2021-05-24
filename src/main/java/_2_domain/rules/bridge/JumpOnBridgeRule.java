package _2_domain.rules.bridge;

import _2_domain.movement.Movement;
import _2_domain.movement.PlayerTurn;
import _2_domain.player.Board;
import _2_domain.player.Players;
import _2_domain.player.Position;

public class JumpOnBridgeRule {
    private final Board board;
    private final Players players;

    public JumpOnBridgeRule(Board board, Players players) {
        this.board = board;
        this.players = players;
    }

    public void apply(PlayerTurn turn) {
        Position lastPosition = players.positionOf(turn.player());

        Movement movement = new JumpOnBridgeMovement(board);

        if (lastPosition.equals(movement.startPosition())) {
            turn.add(movement);
            players.setPositionOf(turn.player(), movement.finalPosition());
        }
    }
}
