package app.domain.rules.bridge;

import app.domain.movement.Movement;
import app.domain.movement.PlayerTurn;
import app.domain.player.Board;
import app.domain.player.Players;
import app.domain.player.Position;

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
