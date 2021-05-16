package usecase.move_player;

import org.junit.jupiter.api.Test;

import static domain.Dice.dice;
import static org.assertj.core.api.Assertions.assertThat;
import static usecase.move_player.SimpleMovement.movement;

class SimpleMovementTest {

    @Test
    void final_position_is_the_sum_of_start_position_and_dice_total() {
        SimpleMovement movement = movement().from(0).givenRoll(dice(4, 2)).end();

        assertThat(movement.finalPosition()).isEqualTo(6);
    }
}