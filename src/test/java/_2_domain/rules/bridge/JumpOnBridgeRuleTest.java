package _2_domain.rules.bridge;

import _2_domain.movement.Movement;
import _2_domain.movement.PlayerTurn;
import _2_domain.player.Board;
import _2_domain.player.Players;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static _2_domain.rules.first.FirstMovementRuleTest.turn;
import static org.assertj.core.api.Assertions.assertThat;

class JumpOnBridgeRuleTest {
    Players players = new Players();
    Board board = new Board();
    JumpOnBridgeRule rule = new JumpOnBridgeRule(board, players);
    LinkedList<Movement> movements = new LinkedList<>();

    @Test
    void jump_from_position_6_to_position_12() {
        players.setPositionOf("Pippo", board.position(6));

        PlayerTurn turn = turn("Pippo", 1, 1);
        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(board.position(12));

        Movement movement = turn.movements().get(0);
        assertThat(movement.startPosition()).isEqualTo(board.position(6));
        assertThat(movement.finalPosition()).isEqualTo(board.position(12));
    }

    @Test
    void remain_on_same_position_if_not_applicable() {
        players.setPositionOf("Pippo", board.position(7));

        PlayerTurn turn = turn("Pippo", 1, 1);
        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(board.position(7));

        assertThat(turn.movements().isEmpty()).isTrue();
    }
}