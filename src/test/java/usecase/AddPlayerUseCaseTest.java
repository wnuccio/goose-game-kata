package usecase;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import player.Players;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AddPlayerUseCaseTest {
    private Players players = new Players();
    private Presenter presenter = mock(Presenter.class);
    private AddPlayerUseCase useCase = new AddPlayerUseCase(players, presenter);

    @Test
    void add_a_new_player_when_an_add_player_command_is_provided() {
        useCase.acceptCommand("add player Pippo");

        verify(presenter).presentPlayers("Pippo");
    }

    @Test
    void add_two_new_players() {
        useCase.acceptCommand("add player Pippo");
        useCase.acceptCommand("add player Pluto");

        verify(presenter).presentPlayers("Pippo", "Pluto");
    }

    @Test
    void do_not_add_the_same_player_more_times() {
        useCase.acceptCommand("add player Pippo");
        useCase.acceptCommand("add player Pippo");

        InOrder inOrder = Mockito.inOrder(presenter);
        inOrder.verify(presenter).presentPlayers("Pippo");
        inOrder.verify(presenter).presentExistingPlayerError("Pippo");
    }
}