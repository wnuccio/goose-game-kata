package usecase.move_player;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComputeMovementWithDiceTest {

    @Test
    void compute_movement_throwing_dice() {
        Dice constantDice = new Dice(1, 2) {
            @Override
            public Dice roll() {
                return this;
            }
        };

        ComputeMovement computeMovement = mock(ComputeMovement.class);
        ComputeMovementWithDice computeMovementWithDice = new ComputeMovementWithDice(computeMovement, constantDice);

        Movement computedMovement = Movement.of("Pippo");

        when(computeMovement.doComputationFor("Pippo", 1, 2)).thenReturn(computedMovement);

        Movement movement = computeMovementWithDice.doComputationFor("Pippo");

        assertThat(movement).isEqualTo(computedMovement);
    }

}
