package goosegame.usecase.move_player;

import goosegame.domain.Players;
import org.junit.jupiter.api.Test;

import java.util.List;

import static goosegame.domain.Dice.dice;
import static goosegame.domain.Position.*;
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
    }

    @Test
    void repeat_movement_on_bouncing() {
        players.setPositionOf("Pippo", position(62));

        List<Movement> movements = computeMovement.fromCommand(move("Pippo", 3, 4));

        assertThat(players.positionOf("Pippo")).isEqualTo(position(57));

        assertThat(movements.size()).isEqualTo(2);
        Movement firstMovement = movements.get(0);
        Movement bouncing = movements.get(1);

        assertThat(firstMovement.startPosition()).isEqualTo(position(62));
        assertThat(firstMovement.finalPosition()).isEqualTo(position(69));
        assertThat(bouncing.startPosition()).isEqualTo(position(69));
        assertThat(bouncing.finalPosition()).isEqualTo(position(57));
    }

    @Test
    void repeat_movement_on_the_bridge() {
        players.setPositionOf("Pippo", position(4));

        List<Movement> movements = computeMovement.fromCommand(move("Pippo", 1, 1));

        assertThat(players.positionOf("Pippo")).isEqualTo(BRIDGE_TARGET);

        assertThat(movements.size()).isEqualTo(2);
        Movement firstMovement = movements.get(0);
        Movement jumpOnBridge = movements.get(1);

        assertThat(firstMovement.startPosition()).isEqualTo(position(4));
        assertThat(firstMovement.finalPosition()).isEqualTo(BRIDGE);
        assertThat(jumpOnBridge.startPosition()).isEqualTo(BRIDGE);
        assertThat(jumpOnBridge.finalPosition()).isEqualTo(BRIDGE_TARGET);
    }

    @Test
    void repeat_movement_on_the_goose() {
        players.setPositionOf("Pippo", position(3));

        List<Movement> movements = computeMovement.fromCommand(move("Pippo", 1, 1));

        assertThat(players.positionOf("Pippo")).isEqualTo(position(7));

        assertThat(movements.size()).isEqualTo(2);
        Movement firstMovement = movements.get(0);
        Movement repeatOnGoose = movements.get(1);

        assertThat(firstMovement.startPosition()).isEqualTo(position(3));
        assertThat(firstMovement.finalPosition()).isEqualTo(position(5));
        assertThat(repeatOnGoose.startPosition()).isEqualTo(position(5));
        assertThat(repeatOnGoose.finalPosition()).isEqualTo(position(7));
    }

    @Test
    void repeat_movement_on_the_goose_more_times() {
        players.setPositionOf("Pippo", position(10));

        List<Movement> movements = computeMovement.fromCommand(move("Pippo", 2, 2));

        assertThat(players.positionOf("Pippo")).isEqualTo(position(22));

        assertThat(movements.size()).isEqualTo(3);
        Movement firstMovement = movements.get(0);
        Movement firstGoose = movements.get(1);
        Movement secondGoose = movements.get(2);

        assertThat(firstMovement.startPosition()).isEqualTo(position(10));
        assertThat(firstMovement.finalPosition()).isEqualTo(position(14));
        assertThat(firstGoose.startPosition()).isEqualTo(position(14));
        assertThat(firstGoose.finalPosition()).isEqualTo(position(18));
        assertThat(secondGoose.startPosition()).isEqualTo(position(18));
        assertThat(secondGoose.finalPosition()).isEqualTo(position(22));
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
        assertThat(movementWithSwitch.startPosition()).isEqualTo(position(17));
        assertThat(movementWithSwitch.finalPosition()).isEqualTo(position(15));
        assertThat(movementWithSwitch.switchedPlayer()).isEqualTo("Pluto");

        assertThat(players.positionOf("Pippo")).isEqualTo(position(17));
        assertThat(players.positionOf("Pluto")).isEqualTo(position(15));
    }

    private MoveCommand move(String player, int first, int second) {
        return new MoveCommand(player, dice(first, second));
    }
}