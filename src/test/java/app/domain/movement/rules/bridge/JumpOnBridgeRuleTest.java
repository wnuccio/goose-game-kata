package app.domain.movement.rules.bridge;

import app.domain.movement.Movement;
import app.domain.player.Players;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static app.domain.movement.rules.first.FirstMovementRuleTest.move;
import static app.domain.player.Position.position;
import static org.assertj.core.api.Assertions.assertThat;

class JumpOnBridgeRuleTest {
    Players players = new Players();
    JumpOnBridgeRule rule = new JumpOnBridgeRule(players);
    LinkedList<Movement> movements = new LinkedList<>();

    @Test
    void jump_from_position_6_to_position_12() {
        players.setPositionOf("Pippo", position(6));

        rule.apply(move("Pippo", 1, 1), movements);

        assertThat(players.positionOf("Pippo")).isEqualTo(position(12));

        Movement movement = movements.get(0);
        assertThat(movement.startPosition()).isEqualTo(position(6));
        assertThat(movement.finalPosition()).isEqualTo(position(12));
    }

    @Test
    void remain_on_same_position_if_not_applicable() {
        players.setPositionOf("Pippo", position(7));

        rule.apply(move("Pippo", 1, 1), movements);

        assertThat(players.positionOf("Pippo")).isEqualTo(position(7));

        assertThat(movements.isEmpty()).isTrue();
    }
}