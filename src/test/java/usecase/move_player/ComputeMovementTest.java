package usecase.move_player;

import domain.Players;
import domain.Position;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import static domain.Dice.dice;
import static domain.Position.BRIDGE_TARGET;
import static domain.Position.START;
import static org.assertj.core.api.Assertions.assertThat;

class ComputeMovementTest {
    Players players = new Players();
    ComputeMovement computeMovement = new ComputeMovement(players);

    @Test
    void build_a_movement_with_player_name_initial_position_and_dice_values() {
        String pippo = "Pippo";
        int position = 0;
        players.setPositionOf(pippo, Position.of(position));

        Movement movement = computeMovement.fromCommand(move("Pippo", 4, 3));

        AssertionsForClassTypes.assertThat(players.positionOf("Pippo")).isEqualTo(Position.of(7));

        assertThat(movement.startPosition()).isEqualTo(START);
        assertThat(movement.finalPosition()).isEqualTo(Position.of(7));
        assertThat(movement.hasPreviousMovement()).isFalse();
    }

    @Test
    void repeat_movement_on_bouncing() {
        players.setPositionOf("Pippo", Position.of(62));

        Movement movement = computeMovement.fromCommand(move("Pippo", 3, 4));

        AssertionsForClassTypes.assertThat(players.positionOf("Pippo")).isEqualTo(Position.of(57));
        assertThat(movement.startPosition()).isEqualTo(Position.of(62));
        assertThat(movement.finalPosition()).isEqualTo(Position.of(57));
        assertThat(movement.hasPreviousMovement()).isTrue();
    }

    @Test
    void repeat_movement_on_the_bridge() {
        players.setPositionOf("Pippo", Position.of(4));

        Movement movement = computeMovement.fromCommand(move("Pippo", 1, 1));

        AssertionsForClassTypes.assertThat(players.positionOf("Pippo")).isEqualTo(Position.of(BRIDGE_TARGET.value()));
        assertThat(movement.startPosition()).isEqualTo(Position.of(4));
        assertThat(movement.finalPosition()).isEqualTo(BRIDGE_TARGET);
        assertThat(movement.hasPreviousMovement()).isTrue();
    }

    @Test
    void repeat_movement_on_the_goose() {
        players.setPositionOf("Pippo", Position.of(3));

        Movement movement = computeMovement.fromCommand(move("Pippo", 1, 1));

        AssertionsForClassTypes.assertThat(players.positionOf("Pippo")).isEqualTo(Position.of(7));
        assertThat(movement.startPosition()).isEqualTo(Position.of(3));
        assertThat(movement.finalPosition()).isEqualTo(Position.of(7));
        assertThat(movement.hasPreviousMovement()).isTrue();
    }

    @Test
    void repeat_movement_on_the_goose_more_times() {
        players.setPositionOf("Pippo", Position.of(10));

        Movement movement = computeMovement.fromCommand(move("Pippo", 2, 2));

        AssertionsForClassTypes.assertThat(players.positionOf("Pippo")).isEqualTo(Position.of(22));
        assertThat(movement.startPosition()).isEqualTo(Position.of(10));
        assertThat(movement.finalPosition()).isEqualTo(Position.of(22));
        assertThat(movement.hasPreviousMovement()).isTrue();
    }

    @Test
    void switch_players_on_encounter() {
        players.setPositionOf("Pippo", Position.of(15));
        players.setPositionOf("Pluto", Position.of(17));

        Movement movement = computeMovement.fromCommand(move("Pippo", 1, 1));

        assertThat(movement.startPosition()).isEqualTo(Position.of(15));
        assertThat(movement.finalPosition()).isEqualTo(Position.of(17));
        assertThat(movement instanceof MovementWithSwitch).isTrue();

        MovementWithSwitch movementWithSwitch = (MovementWithSwitch)movement;
        assertThat(movementWithSwitch.hasPreviousMovement()).isFalse();
        assertThat(movementWithSwitch.startPosition()).isEqualTo(Position.of(15));
        assertThat(movementWithSwitch.finalPosition()).isEqualTo(Position.of(17));
        assertThat(movementWithSwitch.switchedPlayer()).isEqualTo("Pluto");

        AssertionsForClassTypes.assertThat(players.positionOf("Pippo")).isEqualTo(Position.of(17));
        AssertionsForClassTypes.assertThat(players.positionOf("Pluto")).isEqualTo(Position.of(15));
    }



    private MoveCommand move(String player, int first, int second) {
        return new MoveCommand(player, dice(first, second));
    }
}