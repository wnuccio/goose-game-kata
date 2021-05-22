package _1_actions.player.move.rules.bouncing;

import _2_domain.movement.Movement;
import _2_domain.movement.PlayerTurn;
import _2_domain.player.Players;
import _2_domain.player.Position;
import org.junit.jupiter.api.Test;

import static _1_actions.player.move.rules.first.FirstMovementRuleTest.turn;
import static org.assertj.core.api.Assertions.assertThat;

class BouncingRuleTest {
    Players players = new Players();
    BouncingRule rule = new BouncingRule(players);

    @Test
    void correct_position_over_63_with_bouncing() {
        players.setPositionOf("Pippo", Position.position(69));

        PlayerTurn turn = turn("Pippo", 3, 4);
        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(Position.position(57));

        Movement movement = turn.movements().get(0);
        assertThat(movement.startPosition()).isEqualTo(Position.WIN);
        assertThat(movement.finalPosition()).isEqualTo(Position.position(57));
    }

    @Test
    void do_not_correct_position_before_63() {
        players.setPositionOf("Pippo", Position.position(60));

        PlayerTurn turn = turn("Pippo", 3, 4);
        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(Position.position(60));
        assertThat(turn.movements().isEmpty()).isTrue();
    }

    @Test
    void do_not_correct_position_equals_to_63() {
        players.setPositionOf("Pippo", Position.WIN);

        PlayerTurn turn = turn("Pippo", 3, 4);
        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(Position.WIN);
        assertThat(turn.movements().isEmpty()).isTrue();
    }
}