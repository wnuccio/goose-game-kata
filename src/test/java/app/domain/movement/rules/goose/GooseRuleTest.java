package app.domain.movement.rules.goose;

import app.domain.movement.Movement;
import app.domain.player.Players;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static app.domain.movement.rules.first.FirstMovementRuleTest.move;
import static app.domain.player.Position.position;
import static org.assertj.core.api.Assertions.assertThat;

class GooseRuleTest {
    Players players = new Players();
    GooseRule rule = new GooseRule(players);
    LinkedList<Movement> movements = new LinkedList<>();

    @Test
    void move_again_when_lands_on_a_goose_position() {
        assertThat(position(5).hasTheGoose()).isTrue();
        players.setPositionOf("Pippo", position(5));

        rule.apply(move("Pippo", 3, 4), movements);

        assertThat(players.positionOf("Pippo")).isEqualTo(position(12));

        Movement movement = movements.get(0);
        assertThat(movement.startPosition()).isEqualTo(position(5));
        assertThat(movement.finalPosition()).isEqualTo(position(12));
    }

    @Test
    void move_again_more_times_while_landing_on_gooses() {
        assertThat(position(14).hasTheGoose()).isTrue();
        assertThat(position(18).hasTheGoose()).isTrue();
        assertThat(position(22).hasTheGoose()).isFalse();
        players.setPositionOf("Pippo", position(14));

        rule.apply(move("Pippo", 2, 2), movements);

        assertThat(movements.size()).isEqualTo(2);
        Movement movement = movements.get(0);
        Movement movement2 = movements.get(1);
        assertThat(movement.startPosition()).isEqualTo(position(14));
        assertThat(movement.finalPosition()).isEqualTo(position(18));
        assertThat(movement2.startPosition()).isEqualTo(position(18));
        assertThat(movement2.finalPosition()).isEqualTo(position(22));

        assertThat(players.positionOf("Pippo")).isEqualTo(position(22));
    }

    @Test
    void do_not_move_again_when_not_on_a_goose_position() {
        assertThat(position(10).hasTheGoose()).isFalse();
        players.setPositionOf("Pippo", position(10));

        rule.apply(move("Pippo", 3, 4), movements);

        assertThat(players.positionOf("Pippo")).isEqualTo(position(10));

        assertThat(movements.isEmpty()).isTrue();
    }
}