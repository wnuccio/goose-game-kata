package usecase.move_player;

import org.junit.jupiter.api.Test;

import static main.test.DiceForTest.dice;
import static org.assertj.core.api.Assertions.assertThat;
import static usecase.move_player.Movement.WIN_POSITION;

class MovementTest {

    @Test
    void compute_position_in_regular_movement() {
        Movement movement = Movement.of("Pippo").givenRoll(dice(4, 2)).from(0).end();

        assertThat(movement.toPosition()).isEqualTo(6);
    }

    @Test
    void compute_ending_position() {
        Movement movement = Movement.of("Pippo").givenRoll(dice(1, 2)).from(60).end();

        assertThat(movement.toPosition()).isEqualTo(WIN_POSITION);
        assertThat(movement.isVictory()).isTrue();
    }

    @Test
    void compute_movement_with_bouncing() {
        Movement movement = Movement.of("Pippo").givenRoll(dice(2, 3)).from(60).end();

        assertThat(movement.toPosition()).isEqualTo(61);
        assertThat(movement.isBouncing()).isTrue();
    }

}