package _2_domain.rules.goose;

import _2_domain.movement.Movement;
import _2_domain.movement.PlayerTurn;
import _2_domain.player.Board;
import _2_domain.player.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static _2_domain.rules.first.FirstMovementRuleTest.turn;
import static org.assertj.core.api.Assertions.assertThat;

class GooseRuleTest {
    Board board = new Board();
    Players players = new Players();
    GooseRule rule = new GooseRule(players);
    LinkedList<Movement> movements = new LinkedList<>();

    @Test
    void move_again_when_lands_on_a_goose_position() {
        Assertions.assertThat(board.position(5).hasTheGoose()).isTrue();
        players.setPositionOf("Pippo", board.position(5));

        PlayerTurn turn = turn("Pippo", 3, 4);
        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(board.position(12));

        Movement movement = turn.movements().get(0);
        Assertions.assertThat(movement.startPosition()).isEqualTo(board.position(5));
        Assertions.assertThat(movement.finalPosition()).isEqualTo(board.position(12));
    }

    @Test
    void move_again_more_times_while_landing_on_gooses() {
        Assertions.assertThat(board.position(14).hasTheGoose()).isTrue();
        Assertions.assertThat(board.position(18).hasTheGoose()).isTrue();
        Assertions.assertThat(board.position(22).hasTheGoose()).isFalse();
        players.setPositionOf("Pippo", board.position(14));

        PlayerTurn turn = turn("Pippo", 2, 2);
        rule.apply(turn);

        assertThat(turn.movements().size()).isEqualTo(2);
        Movement movement = turn.movements().get(0);
        Movement movement2 = turn.movements().get(1);
        Assertions.assertThat(movement.startPosition()).isEqualTo(board.position(14));
        Assertions.assertThat(movement.finalPosition()).isEqualTo(board.position(18));
        Assertions.assertThat(movement2.startPosition()).isEqualTo(board.position(18));
        Assertions.assertThat(movement2.finalPosition()).isEqualTo(board.position(22));

        assertThat(players.positionOf("Pippo")).isEqualTo(board.position(22));
    }

    @Test
    void do_not_move_again_when_not_on_a_goose_position() {
        Assertions.assertThat(board.position(10).hasTheGoose()).isFalse();
        players.setPositionOf("Pippo", board.position(10));

        PlayerTurn turn = turn("Pippo", 3, 4);
        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(board.position(10));

        assertThat(turn.movements().isEmpty()).isTrue();
    }
}