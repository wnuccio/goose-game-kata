package _2_domain.rules;

import _2_domain.movement.PlayerTurn;
import _2_domain.player.Players;
import _2_domain.player.Position;
import _2_domain.rules.bouncing.BouncingMovement;
import _2_domain.rules.bridge.JumpOnBridgeMovement;
import _2_domain.rules.first.FirstMovement;
import _2_domain.rules.goose.GooseMovement;
import _2_domain.rules.switchrule.SwitchMovement;
import org.junit.jupiter.api.Test;

import static _2_domain.rules.first.FirstMovementRuleTest.turn;
import static org.assertj.core.api.Assertions.assertThat;

class RuleProcessorTest {
    Players players = new Players();
    RuleProcessor ruleProcessor = new RuleProcessor(players);

    @Test
    void repeat_movement_on_bouncing() {
        players.setPositionOf("Pippo", Position.position(62));

        PlayerTurn turn = turn("Pippo", 3, 4);
        ruleProcessor.computeMovementsFor(turn);

        assertThat(turn.movements().size()).isEqualTo(2);
        assertThat(turn.movements().get(0) instanceof FirstMovement).isTrue();
        assertThat(turn.movements().get(1) instanceof BouncingMovement).isTrue();
    }

    @Test
    void repeat_movement_on_the_bridge() {
        players.setPositionOf("Pippo", Position.position(4));
        PlayerTurn turn = turn("Pippo", 1, 1);

        ruleProcessor.computeMovementsFor(turn);

        assertThat(turn.movements().size()).isEqualTo(2);
        assertThat(turn.movements().get(0) instanceof FirstMovement).isTrue();
        assertThat(turn.movements().get(1) instanceof JumpOnBridgeMovement).isTrue();
    }

    @Test
    void repeat_movement_on_the_goose() {
        players.setPositionOf("Pippo", Position.position(3));

        PlayerTurn turn = turn("Pippo", 1, 1);
        ruleProcessor.computeMovementsFor(turn);

        assertThat(turn.movements().size()).isEqualTo(2);
        assertThat(turn.movements().get(0) instanceof FirstMovement).isTrue();
        assertThat(turn.movements().get(1) instanceof GooseMovement).isTrue();
    }

    @Test
    void switch_players_on_encounter() {
        players.setPositionOf("Pippo", Position.position(15));
        players.setPositionOf("Pluto", Position.position(17));

        PlayerTurn turn = turn("Pippo", 1, 1);
        ruleProcessor.computeMovementsFor(turn);

        assertThat(turn.movements().size()).isEqualTo(2);
        assertThat(turn.movements().get(0) instanceof FirstMovement).isTrue();
        assertThat(turn.movements().get(1) instanceof SwitchMovement).isTrue();
    }
}