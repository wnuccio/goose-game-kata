package add_player;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AddPlayerUseCaseTest {

    private Players players = new Players();
    private OutputBoundary outputBoundary = mock(OutputBoundary.class);
    private AddPlayerUseCase useCase = new AddPlayerUseCase(players, outputBoundary);

    @Test
    void add_a_new_player_when_an_add_player_command_is_provided() {
        useCase.acceptCommand("add player Pippo");

        verify(outputBoundary).writeOutputLine("players: Pippo");
    }

    @Test
    void add_two_new_players() {
        useCase.acceptCommand("add player Pippo");
        useCase.acceptCommand("add player Pluto");

        verify(outputBoundary).writeOutputLine("players: Pippo, Pluto");
    }

    @Test
    void do_not_add_the_same_player_more_times() {
        useCase.acceptCommand("add player Pippo");
        useCase.acceptCommand("add player Pippo");

        InOrder inOrder = Mockito.inOrder(outputBoundary);
        inOrder.verify(outputBoundary).writeOutputLine("players: Pippo");
        inOrder.verify(outputBoundary).writeOutputLine("Pippo: already existing player");
    }
}