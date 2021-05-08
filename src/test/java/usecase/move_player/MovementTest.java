package usecase.move_player;

import org.junit.jupiter.api.Test;

import static main.test.DiceForTest.dice;
import static org.assertj.core.api.Assertions.assertThat;
import static usecase.move_player.Movement.WIN_POSITION;

class MovementTest {

    @Test
    void final_position_is_the_sum_of_start_position_and_dice_total() {
        Movement movement = Movement.of("Pippo").givenRoll(dice(4, 2)).from(0).end();

        assertThat(movement.toPosition()).isEqualTo(6);
    }

    @Test
    void is_victory_when_final_position_is_win_position() {
        Movement movement = Movement.of("Pippo").givenRoll(dice(1, 2)).from(60).end();

        assertThat(movement.toPosition()).isEqualTo(WIN_POSITION);
        assertThat(movement.isVictory()).isTrue();
    }

    @Test
    void is_bouncing_when_the_movement_goes_over_win_position() {
        Movement movement = Movement.of("Pippo").givenRoll(dice(2, 3)).from(60).end();

        assertThat(movement.toPosition()).isEqualTo(61);
        assertThat(movement.isBouncing()).isTrue();
    }

}