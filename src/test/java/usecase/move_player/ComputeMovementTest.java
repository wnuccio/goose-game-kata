package usecase.move_player;

import org.junit.jupiter.api.Test;
import usecase.add_player.Players;

import static main.test.DiceForTest.dice;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static usecase.move_player.Movement.of;

public class ComputeMovementTest {
    Players players = mock(Players.class);
    private ComputeMovement computeMovement = new ComputeMovement(players);

    @Test
    void compute_regular_movement() {
        Movement movement = computeMovement.doComputationFor("Pippo", dice(4, 2));

        assertThat(movement).isEqualTo(of("Pippo").givenRoll(4, 2).from(0).to(6));
    }

    @Test
    void compute_movement_to_ending_position() {
        when(players.positionOf("Pippo")).thenReturn(60);

        Movement movement = computeMovement.doComputationFor("Pippo", dice(1, 2));

        assertThat(movement).isEqualTo(of("Pippo").givenRoll(1, 2).from(60).to(63).setVictory(true));
    }
    
    @Test
    void compute_movement_with_bouncing() {
        when(players.positionOf("Pippo")).thenReturn(60);

        Movement movement = computeMovement.doComputationFor("Pippo", dice(2, 3));

        assertThat(movement).isEqualTo(of("Pippo").givenRoll(2, 3).from(60).to(61).setVictory(false).setBouncing(true));
    }
}
