package usecase.move_player;

import domain.Players;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.Dice.dice;
import static domain.Position.*;
import static org.assertj.core.api.Assertions.assertThat;

class ComputeMovementTest {
    Players players = new Players();
    ComputeMovement computeMovement = new ComputeMovement(players);

    @Test
    void build_a_movement_with_player_name_initial_position_and_dice_values() {
        players.setPositionOf("Pippo", position(0));

        List<Movement> movements = computeMovement.fromCommand(move("Pippo", 4, 3));

        assertThat(players.positionOf("Pippo")).isEqualTo(position(7));

        assertThat(movements.size()).isEqualTo(1);
        Movement movement = movements.get(0);
        assertThat(movement.startPosition()).isEqualTo(START);
        assertThat(movement.finalPosition()).isEqualTo(position(7));
        assertThat(movement.hasPreviousMovement()).isFalse();
    }

    @Test
    void repeat_movement_on_bouncing() {
        players.setPositionOf("Pippo", position(62));

        List<Movement> movements = computeMovement.fromCommand(move("Pippo", 3, 4));

        assertThat(players.positionOf("Pippo")).isEqualTo(position(57));

        assertThat(movements.size()).isEqualTo(2);
        Movement movement1 = movements.get(0);
        Movement movement2 = movements.get(1);

        assertThat(movement1.startPosition()).isEqualTo(position(62));
        assertThat(movement1.finalPosition()).isEqualTo(position(69));
        assertThat(movement2.startPosition()).isEqualTo(position(62));
        assertThat(movement2.finalPosition()).isEqualTo(position(57));
    }

    @Test
    void repeat_movement_on_the_bridge() {
        players.setPositionOf("Pippo", position(4));

        List<Movement> movements = computeMovement.fromCommand(move("Pippo", 1, 1));

        assertThat(players.positionOf("Pippo")).isEqualTo(BRIDGE_TARGET);

        assertThat(movements.size()).isEqualTo(2);
        Movement movement1 = movements.get(0);
        Movement movement2 = movements.get(1);

        assertThat(movement1.startPosition()).isEqualTo(position(4));
        assertThat(movement1.finalPosition()).isEqualTo(BRIDGE);
        assertThat(movement2.startPosition()).isEqualTo(position(4));
        assertThat(movement2.finalPosition()).isEqualTo(BRIDGE_TARGET);
    }

    @Test
    void repeat_movement_on_the_goose() {
        players.setPositionOf("Pippo", position(3));

        List<Movement> movements = computeMovement.fromCommand(move("Pippo", 1, 1));

        assertThat(players.positionOf("Pippo")).isEqualTo(position(7));

        assertThat(movements.size()).isEqualTo(2);
        Movement movement1 = movements.get(0);
        Movement movement2 = movements.get(1);

        assertThat(movement1.startPosition()).isEqualTo(position(3));
        assertThat(movement1.finalPosition()).isEqualTo(position(5));
        assertThat(movement2.startPosition()).isEqualTo(position(3));
        assertThat(movement2.finalPosition()).isEqualTo(position(7));
    }

    @Test
    void repeat_movement_on_the_goose_more_times() {
        players.setPositionOf("Pippo", position(10));

        List<Movement> movements = computeMovement.fromCommand(move("Pippo", 2, 2));

        assertThat(players.positionOf("Pippo")).isEqualTo(position(22));

        assertThat(movements.size()).isEqualTo(3);
        Movement movement1 = movements.get(0);
        Movement movement2 = movements.get(1);
        Movement movement3 = movements.get(2);

        assertThat(movement1.startPosition()).isEqualTo(position(10));
        assertThat(movement1.finalPosition()).isEqualTo(position(14));
        assertThat(movement2.startPosition()).isEqualTo(position(10));
        assertThat(movement2.finalPosition()).isEqualTo(position(18));
        assertThat(movement3.startPosition()).isEqualTo(position(10));
        assertThat(movement3.finalPosition()).isEqualTo(position(22));
    }

    @Test
    void switch_players_on_encounter() {
        players.setPositionOf("Pippo", position(15));
        players.setPositionOf("Pluto", position(17));

        List<Movement> movements = computeMovement.fromCommand(move("Pippo", 1, 1));

        assertThat(movements.size()).isEqualTo(2);
        Movement movement1 = movements.get(0);
        Movement movement2 = movements.get(1);

        assertThat(movement1.startPosition()).isEqualTo(position(15));
        assertThat(movement1.finalPosition()).isEqualTo(position(17));

        assertThat(movement2 instanceof MovementWithSwitch).isTrue();

        MovementWithSwitch movementWithSwitch = (MovementWithSwitch)movement2;
        assertThat(movementWithSwitch.startPosition()).isEqualTo(position(15));
        assertThat(movementWithSwitch.finalPosition()).isEqualTo(position(17));
        assertThat(movementWithSwitch.switchedPlayer()).isEqualTo("Pluto");

        assertThat(players.positionOf("Pippo")).isEqualTo(position(17));
        assertThat(players.positionOf("Pluto")).isEqualTo(position(15));
    }

    private MoveCommand move(String player, int first, int second) {
        return new MoveCommand(player, dice(first, second));
    }
}