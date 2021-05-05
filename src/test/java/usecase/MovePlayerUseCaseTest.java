package usecase;

import player.Players;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MovePlayerUseCaseTest {

    @Test
    void moves_a_player_from_start_to_the_specified_position() {
        Players players = new Players().addPlayer("Pippo");
        Presenter presenter = mock(Presenter.class);

        MovePlayerUseCase useCase = new MovePlayerUseCase(players, presenter);

        useCase.acceptCommand("move Pippo 4, 2");

        Movement movement = new Movement("Pippo").givenRoll(4, 2).from(0).to(6);
        verify(presenter).presentMovement(movement);
    }
}