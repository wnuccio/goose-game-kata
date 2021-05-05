package usecase;

import org.junit.jupiter.api.BeforeEach;
import player.Players;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static usecase.Movement.of;

class MovePlayerUseCaseTest {

    private Players players = mock(Players.class);
    private Presenter presenter = mock(Presenter.class);
    private MovePlayerUseCase useCase = new MovePlayerUseCase(players, presenter);

    @Test
    void moves_a_player_from_start_to_the_specified_position() {
        when(players.positionOf("Pippo")).thenReturn(0);
        when(players.moveOnRoll("Pippo", 4, 2)).thenReturn(6);

        useCase.acceptCommand("move Pippo 4, 2");

        verify(presenter).presentMovement(of("Pippo").givenRoll(4, 2).from(0).to(6));
    }
}