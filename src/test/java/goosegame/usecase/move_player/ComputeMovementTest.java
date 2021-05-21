package goosegame.usecase.move_player;

import goosegame.domain.Players;
import org.junit.jupiter.api.Test;

import java.util.List;

import static goosegame.domain.Position.position;
import static goosegame.usecase.move_player.FirstMovementRuleTest.move;
import static org.assertj.core.api.Assertions.assertThat;

class ComputeMovementTest {
    Players players = new Players();
    ComputeMovement computeMovement = new ComputeMovement(players);

    @Test
    void repeat_movement_on_bouncing() {
        players.setPositionOf("Pippo", position(62));

        List<Movement> movements = computeMovement.fromCommand(move("Pippo", 3, 4));

        assertThat(movements.size()).isEqualTo(2);
        assertThat(movements.get(0) instanceof FirstMovement).isTrue();
        assertThat(movements.get(1) instanceof BouncingMovement).isTrue();
    }

    @Test
    void repeat_movement_on_the_bridge() {
        players.setPositionOf("Pippo", position(4));

        List<Movement> movements = computeMovement.fromCommand(move("Pippo", 1, 1));

        assertThat(movements.size()).isEqualTo(2);
        assertThat(movements.get(0) instanceof FirstMovement).isTrue();
        assertThat(movements.get(1) instanceof JumpOnBridgeMovement).isTrue();
    }

    @Test
    void repeat_movement_on_the_goose() {
        players.setPositionOf("Pippo", position(3));

        List<Movement> movements = computeMovement.fromCommand(move("Pippo", 1, 1));

        assertThat(movements.size()).isEqualTo(2);
        assertThat(movements.get(0) instanceof FirstMovement).isTrue();
        assertThat(movements.get(1) instanceof GooseMovement).isTrue();
    }

    @Test
    void switch_players_on_encounter() {
        players.setPositionOf("Pippo", position(15));
        players.setPositionOf("Pluto", position(17));

        List<Movement> movements = computeMovement.fromCommand(move("Pippo", 1, 1));

        assertThat(movements.size()).isEqualTo(2);
        assertThat(movements.get(0) instanceof FirstMovement).isTrue();
        assertThat(movements.get(1) instanceof SwitchMovement).isTrue();
    }
}