package usecase.move_player;

import main.test.DiceForTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComputeMovementWithDiceTest {

    @Test
    void compute_movement_throwing_dice() {
        Dice fixedDice = new DiceForTest(1, 2);
        ComputeMovement computeMovement = mock(ComputeMovement.class);
        ComputeMovementWithDice computeMovementWithDice = new ComputeMovementWithDice(computeMovement, fixedDice);

        Movement computedMovement = Movement.of("Pippo");

        when(computeMovement.doComputationFor("Pippo", 1, 2)).thenReturn(computedMovement);

        Movement movement = computeMovementWithDice.doComputationFor("Pippo");

        assertThat(movement).isEqualTo(computedMovement);
    }

}
