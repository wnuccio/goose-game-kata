package app.domain.movement;

import app.domain.movement.rules.bouncing.BouncingMovement;
import app.domain.movement.rules.bridge.JumpOnBridgeMovement;
import app.domain.movement.rules.first.FirstMovement;
import app.domain.movement.rules.goose.GooseMovement;
import app.domain.movement.rules.switchrule.SwitchMovement;
import app.domain.player.Players;
import org.junit.jupiter.api.Test;

import java.util.List;

import static app.domain.movement.rules.first.FirstMovementRuleTest.move;
import static app.domain.player.Position.position;
import static org.assertj.core.api.Assertions.assertThat;

class RuleProcessorTest {
    Players players = new Players();
    RuleProcessor ruleProcessor = new RuleProcessor(players);

    @Test
    void repeat_movement_on_bouncing() {
        players.setPositionOf("Pippo", position(62));

        List<Movement> movements = ruleProcessor.fromCommand(move("Pippo", 3, 4));

        assertThat(movements.size()).isEqualTo(2);
        assertThat(movements.get(0) instanceof FirstMovement).isTrue();
        assertThat(movements.get(1) instanceof BouncingMovement).isTrue();
    }

    @Test
    void repeat_movement_on_the_bridge() {
        players.setPositionOf("Pippo", position(4));

        List<Movement> movements = ruleProcessor.fromCommand(move("Pippo", 1, 1));

        assertThat(movements.size()).isEqualTo(2);
        assertThat(movements.get(0) instanceof FirstMovement).isTrue();
        assertThat(movements.get(1) instanceof JumpOnBridgeMovement).isTrue();
    }

    @Test
    void repeat_movement_on_the_goose() {
        players.setPositionOf("Pippo", position(3));

        List<Movement> movements = ruleProcessor.fromCommand(move("Pippo", 1, 1));

        assertThat(movements.size()).isEqualTo(2);
        assertThat(movements.get(0) instanceof FirstMovement).isTrue();
        assertThat(movements.get(1) instanceof GooseMovement).isTrue();
    }

    @Test
    void switch_players_on_encounter() {
        players.setPositionOf("Pippo", position(15));
        players.setPositionOf("Pluto", position(17));

        List<Movement> movements = ruleProcessor.fromCommand(move("Pippo", 1, 1));

        assertThat(movements.size()).isEqualTo(2);
        assertThat(movements.get(0) instanceof FirstMovement).isTrue();
        assertThat(movements.get(1) instanceof SwitchMovement).isTrue();
    }
}