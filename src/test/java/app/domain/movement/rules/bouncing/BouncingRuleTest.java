package app.domain.movement.rules.bouncing;

import app.domain.movement.Movement;
import app.domain.player.Players;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static app.domain.movement.rules.first.FirstMovementRuleTest.move;
import static app.domain.player.Position.WIN;
import static app.domain.player.Position.position;
import static org.assertj.core.api.Assertions.assertThat;

class BouncingRuleTest {
    Players players = new Players();
    BouncingRule rule = new BouncingRule(players);
    LinkedList<Movement> movements = new LinkedList<>();

    @Test
    void correct_position_over_63_with_bouncing() {
        players.setPositionOf("Pippo", position(69));

        rule.apply(move("Pippo", 3, 4), movements);

        assertThat(players.positionOf("Pippo")).isEqualTo(position(57));

        Movement movement = movements.get(0);
        assertThat(movement.startPosition()).isEqualTo(WIN);
        assertThat(movement.finalPosition()).isEqualTo(position(57));
    }

    @Test
    void do_not_correct_position_before_63() {
        players.setPositionOf("Pippo", position(60));

        rule.apply(move("Pippo", 3, 4), movements);

        assertThat(players.positionOf("Pippo")).isEqualTo(position(60));
        assertThat(movements.isEmpty()).isTrue();
    }

    @Test
    void do_not_correct_position_equals_to_63() {
        players.setPositionOf("Pippo", WIN);

        rule.apply(move("Pippo", 3, 4), movements);

        assertThat(players.positionOf("Pippo")).isEqualTo(WIN);
        assertThat(movements.isEmpty()).isTrue();
    }
}