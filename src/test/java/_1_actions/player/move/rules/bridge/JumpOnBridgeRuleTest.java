package _1_actions.player.move.rules.bridge;

import _1_actions.player.move.rules.first.FirstMovementRuleTest;
import _2_domain.movement.Movement;
import _2_domain.player.Players;
import _2_domain.player.Position;
import _2_domain.presenter.PresentableMovement;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

class JumpOnBridgeRuleTest {
    Players players = new Players();
    JumpOnBridgeRule rule = new JumpOnBridgeRule(players);
    LinkedList<PresentableMovement> movements = new LinkedList<>();

    @Test
    void jump_from_position_6_to_position_12() {
        players.setPositionOf("Pippo", Position.position(6));

        rule.apply(FirstMovementRuleTest.move("Pippo", 1, 1), movements);

        assertThat(players.positionOf("Pippo")).isEqualTo(Position.position(12));

        Movement movement = movements.get(0);
        assertThat(movement.startPosition()).isEqualTo(Position.position(6));
        assertThat(movement.finalPosition()).isEqualTo(Position.position(12));
    }

    @Test
    void remain_on_same_position_if_not_applicable() {
        players.setPositionOf("Pippo", Position.position(7));

        rule.apply(FirstMovementRuleTest.move("Pippo", 1, 1), movements);

        assertThat(players.positionOf("Pippo")).isEqualTo(Position.position(7));

        assertThat(movements.isEmpty()).isTrue();
    }
}