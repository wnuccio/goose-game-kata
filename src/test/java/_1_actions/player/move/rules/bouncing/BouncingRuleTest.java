package _1_actions.player.move.rules.bouncing;

import _1_actions.player.move.rules.first.FirstMovementRuleTest;
import _2_domain.movement.Movement;
import _2_domain.player.Players;
import _2_domain.player.Position;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

class BouncingRuleTest {
    Players players = new Players();
    BouncingRule rule = new BouncingRule(players);
    LinkedList<Movement> movements = new LinkedList<>();

    @Test
    void correct_position_over_63_with_bouncing() {
        players.setPositionOf("Pippo", Position.position(69));

        rule.apply(FirstMovementRuleTest.move("Pippo", 3, 4), movements);

        assertThat(players.positionOf("Pippo")).isEqualTo(Position.position(57));

        Movement movement = movements.get(0);
        assertThat(movement.startPosition()).isEqualTo(Position.WIN);
        assertThat(movement.finalPosition()).isEqualTo(Position.position(57));
    }

    @Test
    void do_not_correct_position_before_63() {
        players.setPositionOf("Pippo", Position.position(60));

        rule.apply(FirstMovementRuleTest.move("Pippo", 3, 4), movements);

        assertThat(players.positionOf("Pippo")).isEqualTo(Position.position(60));
        assertThat(movements.isEmpty()).isTrue();
    }

    @Test
    void do_not_correct_position_equals_to_63() {
        players.setPositionOf("Pippo", Position.WIN);

        rule.apply(FirstMovementRuleTest.move("Pippo", 3, 4), movements);

        assertThat(players.positionOf("Pippo")).isEqualTo(Position.WIN);
        assertThat(movements.isEmpty()).isTrue();
    }
}