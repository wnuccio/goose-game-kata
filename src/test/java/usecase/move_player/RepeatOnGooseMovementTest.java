package usecase.move_player;

import org.junit.jupiter.api.Test;

import static domain.Dice.dice;
import static org.assertj.core.api.Assertions.assertThat;
import static usecase.move_player.SimpleMovement.of;

class RepeatOnGooseMovementTest {

    @Test
    void when_on_goose_repeat_the_movement_with_the_same_dice_total() {
        SimpleMovement movement = of("Pippo").from(0).givenRoll(dice(3, 2)).end();
        RepeatOnGooseMovement repeatMovement = new RepeatOnGooseMovement(movement);

        assertThat(repeatMovement.finalPosition()).isEqualTo(10);
    }
}