package usecase.move_player;

import org.junit.jupiter.api.Test;

import static domain.Dice.dice;
import static domain.Position.WIN_POSITION;
import static org.assertj.core.api.Assertions.assertThat;
import static usecase.move_player.SimpleMovement.of;

class SimpleMovementTest {

    @Test
    void final_position_is_the_sum_of_start_position_and_dice_total() {
        SimpleMovement movement = of("Pippo").from(0).givenRoll(dice(4, 2)).end();

        assertThat(movement.finalPosition()).isEqualTo(6);
    }

    @Test
    void is_victory_when_final_position_is_win_position() {
        SimpleMovement movement = of("Pippo").from(60).givenRoll(dice(1, 2)).end();

        assertThat(movement.finalPosition()).isEqualTo(WIN_POSITION);
        assertThat(movement.isVictory()).isTrue();
    }

    @Test
    void is_bouncing_when_the_movement_goes_over_win_position() {
        SimpleMovement movement = of("Pippo").from(60).givenRoll(dice(2, 3)).end();

        assertThat(movement.finalPosition()).isEqualTo(61);
        assertThat(movement.isBouncing()).isTrue();
    }

    @Test
    void ends_on_goose_when_the_final_position_has_a_goose() {
        // positions with goose: 5, 9, 14, 18, 23, 27
        assertThat(of("Pippo").from(3).givenRoll(dice(1, 1)).end().endsOnGoose()).isTrue();
        assertThat(of("Pippo").from(7).givenRoll(dice(1, 1)).end().endsOnGoose()).isTrue();
        assertThat(of("Pippo").from(12).givenRoll(dice(1, 1)).end().endsOnGoose()).isTrue();
        assertThat(of("Pippo").from(16).givenRoll(dice(1, 1)).end().endsOnGoose()).isTrue();
        assertThat(of("Pippo").from(21).givenRoll(dice(1, 1)).end().endsOnGoose()).isTrue();
        assertThat(of("Pippo").from(25).givenRoll(dice(1, 1)).end().endsOnGoose()).isTrue();
    }
}