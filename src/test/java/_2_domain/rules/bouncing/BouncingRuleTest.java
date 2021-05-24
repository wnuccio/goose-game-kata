package _2_domain.rules.bouncing;

import _2_domain.movement.Movement;
import _2_domain.movement.PlayerTurn;
import _2_domain.player.Board;
import _2_domain.player.Players;
import org.junit.jupiter.api.Test;

import static _2_domain.rules.first.FirstMovementRuleTest.turn;
import static org.assertj.core.api.Assertions.assertThat;

class BouncingRuleTest {
    private final Board board = new Board();
    Players players = new Players();
    BouncingRule rule = new BouncingRule(board, players);

    @Test
    void correct_position_over_63_with_bouncing() {
        players.setPositionOf("Pippo", board.position(69));

        PlayerTurn turn = turn("Pippo", 3, 4);
        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(board.position(57));

        Movement movement = turn.movements().get(0);
        assertThat(movement.startPosition()).isEqualTo(board.winPosition());
        assertThat(movement.finalPosition()).isEqualTo(board.position(57));
    }

    @Test
    void do_not_correct_position_before_63() {
        players.setPositionOf("Pippo", board.position(60));

        PlayerTurn turn = turn("Pippo", 3, 4);
        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(board.position(60));
        assertThat(turn.movements().isEmpty()).isTrue();
    }

    @Test
    void do_not_correct_position_equals_to_63() {
        players.setPositionOf("Pippo", board.winPosition());

        PlayerTurn turn = turn("Pippo", 3, 4);
        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(board.winPosition());
        assertThat(turn.movements().isEmpty()).isTrue();
    }
}