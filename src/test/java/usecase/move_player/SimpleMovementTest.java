package usecase.move_player;

import org.junit.jupiter.api.Test;

import static main.test.DiceForTest.dice;
import static org.assertj.core.api.Assertions.assertThat;
import static usecase.move_player.SimpleMovement.WIN_POSITION;

class SimpleMovementTest {

    @Test
    void final_position_is_the_sum_of_start_position_and_dice_total() {
        SimpleMovement movement = SimpleMovement.of("Pippo").from(0).givenRoll(dice(4, 2)).end();

        assertThat(movement.toPosition()).isEqualTo(6);
    }

    @Test
    void is_victory_when_final_position_is_win_position() {
        SimpleMovement movement = SimpleMovement.of("Pippo").from(60).givenRoll(dice(1, 2)).end();

        assertThat(movement.toPosition()).isEqualTo(WIN_POSITION);
        assertThat(movement.isVictory()).isTrue();
    }

    @Test
    void is_bouncing_when_the_movement_goes_over_win_position() {
        SimpleMovement movement = SimpleMovement.of("Pippo").from(60).givenRoll(dice(2, 3)).end();

        assertThat(movement.toPosition()).isEqualTo(61);
        assertThat(movement.isBouncing()).isTrue();
    }

}