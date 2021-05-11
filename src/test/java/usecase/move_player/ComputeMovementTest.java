package usecase.move_player;

import domain.Players;
import org.junit.jupiter.api.Test;

import static domain.Dice.dice;
import static domain.Position.BRIDGE_TARGET;
import static org.assertj.core.api.Assertions.assertThat;

class ComputeMovementTest {
    Players players = new Players();
    ComputeMovement computeMovement = new ComputeMovement(players);

    @Test
    void build_a_movement_with_player_name_initial_position_and_dice_values() {
        players.setPositionOf("Pippo", 0);

        Movement movement = computeMovement.acceptCommand(move("Pippo", 4, 3));

        assertThat(players.positionOf("Pippo")).isEqualTo(7);
        assertThat(movement.startPosition()).isEqualTo(0);
        assertThat(movement.finalPosition()).isEqualTo(7);
        assertThat(movement.hasPreviousMovement()).isFalse();
    }

    @Test
    void repeat_movement_on_bouncing() {
        players.setPositionOf("Pippo", 62);

        Movement movement = computeMovement.acceptCommand(move("Pippo", 3, 4));

        assertThat(players.positionOf("Pippo")).isEqualTo(57);
        assertThat(movement.startPosition()).isEqualTo(62);
        assertThat(movement.finalPosition()).isEqualTo(57);
        assertThat(movement.hasPreviousMovement()).isTrue();
    }

    @Test
    void repeat_movement_on_the_bridge() {
        players.setPositionOf("Pippo", 4);

        Movement movement = computeMovement.acceptCommand(move("Pippo", 1, 1));

        assertThat(players.positionOf("Pippo")).isEqualTo(BRIDGE_TARGET);
        assertThat(movement.startPosition()).isEqualTo(4);
        assertThat(movement.finalPosition()).isEqualTo(BRIDGE_TARGET);
        assertThat(movement.hasPreviousMovement()).isTrue();
    }

    @Test
    void repeat_movement_on_the_goose() {
        players.setPositionOf("Pippo", 3);

        Movement movement = computeMovement.acceptCommand(move("Pippo", 1, 1));

        assertThat(players.positionOf("Pippo")).isEqualTo(7);
        assertThat(movement.startPosition()).isEqualTo(3);
        assertThat(movement.finalPosition()).isEqualTo(7);
        assertThat(movement.hasPreviousMovement()).isTrue();
    }

    @Test
    void repeat_movement_on_the_goose_more_times() {
        players.setPositionOf("Pippo", 10);

        Movement movement = computeMovement.acceptCommand(move("Pippo", 2, 2));

        assertThat(players.positionOf("Pippo")).isEqualTo(22);
        assertThat(movement.startPosition()).isEqualTo(10);
        assertThat(movement.finalPosition()).isEqualTo(22);
        assertThat(movement.hasPreviousMovement()).isTrue();
    }

    private MoveCommand move(String player, int first, int second) {
        return new MoveCommand(player, dice(first, second));
    }
}