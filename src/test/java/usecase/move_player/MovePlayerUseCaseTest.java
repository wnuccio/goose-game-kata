package usecase.move_player;

import main.test.DiceForTest;
import org.junit.jupiter.api.Test;
import usecase.Presenter;
import usecase.add_player.Players;

import static main.test.DiceForTest.dice;
import static org.mockito.Mockito.*;
import static usecase.move_player.Movement.of;

class MovePlayerUseCaseTest {

    private Players players = mock(Players.class);
    private DiceForTest dice = new DiceForTest(5, 6);
    private ComputeMovement computeMovement = mock(ComputeMovement.class);
    private Presenter presenter = mock(Presenter.class);
    private MovePlayerUseCase useCase = new MovePlayerUseCase(players, dice, computeMovement, presenter);

    @Test
    void moves_a_player_from_start_to_the_specified_position() {
        when(players.contains("Pippo")).thenReturn(true);
        Movement movement = of("Pippo").givenRoll(4, 2).from(0).to(6);
        when(computeMovement.doComputationFor("Pippo", dice(4, 2))).thenReturn(movement);

        useCase.acceptCommand("move Pippo 4, 2");

        verify(players).setPositionOf("Pippo", 6);
        verify(presenter).presentMovement(movement);
    }

    @Test
    void present_a_victory_when_player_moves_to_the_ending_position() {
        when(players.contains("Pippo")).thenReturn(true);
        Movement movement = of("Pippo").givenRoll(1, 2).from(60).to(63).setVictory(true);
        when(computeMovement.doComputationFor("Pippo", dice(1, 2))).thenReturn(movement);

        useCase.acceptCommand("move Pippo 1, 2");

        verify(players).setPositionOf("Pippo", 63);
        verify(presenter).presentMovement(movement);
    }

    @Test
    void present_an_error_when_the_command_specifies_a_not_present_player() {
        when(players.contains("Pippo")).thenReturn(false);

        useCase.acceptCommand("move Pippo 1, 2");

        verify(presenter).presentNoSuchPlayerError("Pippo");
    }

    @Test
    void present_a_bouncing_when_player_moves_over_the_ending_position() {
        when(players.contains("Pippo")).thenReturn(true);
        Movement movement = of("Pippo").givenRoll(2, 3)
                .from(60).to(61)
                .setVictory(false)
                .setBouncing(true);
        when(computeMovement.doComputationFor("Pippo", dice(2, 3))).thenReturn(movement);

        useCase.acceptCommand("move Pippo 2, 3");

        verify(players).setPositionOf("Pippo", 61);
        verify(presenter).presentMovement(movement);
    }

    @Test
    void compute_movement_by_rolling_dice_when_command_does_not_specify_any_value() {
        dice.values(5, 6);

        when(players.contains("Pippo")).thenReturn(true);
        Movement movement = of("Pippo").givenRoll(5, 6).from(0).to(11);
        when(computeMovement.doComputationFor("Pippo", dice(5, 6))).thenReturn(movement);

        useCase.acceptCommand("move Pippo");
    }
}