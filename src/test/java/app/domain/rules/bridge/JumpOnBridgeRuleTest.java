package app.domain.rules.bridge;

import app.domain.movement.Movement;
import app.domain.movement.PlayerOnTurn;
import app.domain.player.Board;
import app.domain.player.Players;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static app.domain.rules.first.FirstMovementRuleTest.playerOnTurn;
import static org.assertj.core.api.Assertions.assertThat;

class JumpOnBridgeRuleTest {
    Players players = new Players();
    Board board = new Board();
    JumpOnBridgeRule rule = new JumpOnBridgeRule(board);
    LinkedList<Movement> movements = new LinkedList<>();

    @Test
    void jump_from_position_6_to_position_12() {
        players.setPositionOf("Pippo", board.position(6));

        PlayerOnTurn turn = playerOnTurn(players, "Pippo", 1, 1);
        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(board.position(12));

        Movement movement = turn.movements().get(0);
        assertThat(movement.startPosition()).isEqualTo(board.position(6));
        assertThat(movement.finalPosition()).isEqualTo(board.position(12));
    }

    @Test
    void remain_on_same_position_if_not_applicable() {
        players.setPositionOf("Pippo", board.position(7));

        PlayerOnTurn turn = playerOnTurn(players, "Pippo", 1, 1);
        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(board.position(7));

        assertThat(turn.movements().isEmpty()).isTrue();
    }
}