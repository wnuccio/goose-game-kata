package _1_actions.player.move.rules.goose;

import _2_domain.movement.Movement;
import _2_domain.movement.PlayerTurn;
import _2_domain.player.Players;
import _2_domain.player.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static _1_actions.player.move.rules.first.FirstMovementRuleTest.turn;
import static org.assertj.core.api.Assertions.assertThat;

class GooseRuleTest {
    Players players = new Players();
    GooseRule rule = new GooseRule(players);
    LinkedList<Movement> movements = new LinkedList<>();

    @Test
    void move_again_when_lands_on_a_goose_position() {
        Assertions.assertThat(Position.position(5).hasTheGoose()).isTrue();
        players.setPositionOf("Pippo", Position.position(5));

        PlayerTurn turn = turn("Pippo", 3, 4);
        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(Position.position(12));

        Movement movement = turn.movements().get(0);
        Assertions.assertThat(movement.startPosition()).isEqualTo(Position.position(5));
        Assertions.assertThat(movement.finalPosition()).isEqualTo(Position.position(12));
    }

    @Test
    void move_again_more_times_while_landing_on_gooses() {
        Assertions.assertThat(Position.position(14).hasTheGoose()).isTrue();
        Assertions.assertThat(Position.position(18).hasTheGoose()).isTrue();
        Assertions.assertThat(Position.position(22).hasTheGoose()).isFalse();
        players.setPositionOf("Pippo", Position.position(14));

        PlayerTurn turn = turn("Pippo", 2, 2);
        rule.apply(turn);

        assertThat(turn.movements().size()).isEqualTo(2);
        Movement movement = turn.movements().get(0);
        Movement movement2 = turn.movements().get(1);
        Assertions.assertThat(movement.startPosition()).isEqualTo(Position.position(14));
        Assertions.assertThat(movement.finalPosition()).isEqualTo(Position.position(18));
        Assertions.assertThat(movement2.startPosition()).isEqualTo(Position.position(18));
        Assertions.assertThat(movement2.finalPosition()).isEqualTo(Position.position(22));

        assertThat(players.positionOf("Pippo")).isEqualTo(Position.position(22));
    }

    @Test
    void do_not_move_again_when_not_on_a_goose_position() {
        Assertions.assertThat(Position.position(10).hasTheGoose()).isFalse();
        players.setPositionOf("Pippo", Position.position(10));

        PlayerTurn turn = turn("Pippo", 3, 4);
        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(Position.position(10));

        assertThat(turn.movements().isEmpty()).isTrue();
    }
}