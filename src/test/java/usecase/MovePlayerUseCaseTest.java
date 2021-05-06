package usecase;

import org.junit.jupiter.api.Test;
import player.Players;

import static org.mockito.Mockito.*;
import static usecase.Movement.of;

class MovePlayerUseCaseTest {

    private Players players = mock(Players.class);
    private Presenter presenter = mock(Presenter.class);
    private MovePlayerUseCase useCase = new MovePlayerUseCase(players, presenter);

    @Test
    void moves_a_player_from_start_to_the_specified_position() {
        when(players.contains("Pippo")).thenReturn(true);
        when(players.positionOf("Pippo")).thenReturn(0);

        useCase.acceptCommand("move Pippo 4, 2");

        verify(players).setPositionOf("Pippo", 6);
        verify(presenter).presentMovement(of("Pippo").givenRoll(4, 2).from(0).to(6));
    }

    @Test
    void present_a_victory_when_player_moves_to_the_ending_position() {
        when(players.contains("Pippo")).thenReturn(true);
        when(players.positionOf("Pippo")).thenReturn(60);

        useCase.acceptCommand("move Pippo 1, 2");

        verify(players).setPositionOf("Pippo", 63);
        verify(presenter).presentMovement(of("Pippo").givenRoll(1, 2).from(60).to(63).setVictory(true));
    }

    @Test
    void present_an_error_when_the_command_specifies_a_not_present_player() {
        when(players.contains("Pippo")).thenReturn(false);

        useCase.acceptCommand("move Pippo 1, 2");

        verify(presenter).presentNoSuchPlayerError("Pippo");
    }
}