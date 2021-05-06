package usecase;

import org.junit.jupiter.api.Test;
import player.Players;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static usecase.Movement.of;

public class ComputeMovementTest {
    Players players = mock(Players.class);
    private ComputeMovement computeMovement = new ComputeMovement(players);

    @Test
    void compute_regular_movement() {
        Movement movement = computeMovement.doComputationFor("Pippo", 4, 2);

        assertThat(movement).isEqualTo(of("Pippo").givenRoll(4, 2).from(0).to(6));
    }

    @Test
    void compute_movement_to_ending_position() {
        when(players.positionOf("Pippo")).thenReturn(60);

        Movement movement = computeMovement.doComputationFor("Pippo", 1, 2);

        assertThat(movement).isEqualTo(of("Pippo").givenRoll(1, 2).from(60).to(63).setVictory(true));
    }
    
    @Test
    void compute_movement_with_bouncing() {
        when(players.positionOf("Pippo")).thenReturn(60);

        Movement movement = computeMovement.doComputationFor("Pippo", 2, 3);

        assertThat(movement).isEqualTo(of("Pippo").givenRoll(2, 3).from(60).to(61).setVictory(false).setBouncing(true));
    }
}
