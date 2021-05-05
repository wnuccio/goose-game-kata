package add_player;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AddPlayerUseCaseTest {

    @Test
    void add_a_new_player_when_an_add_player_command_is_provided() {
        Players players = mock(Players.class);
        OutputBoundary outputBoundary = mock(OutputBoundary.class);
        AddPlayerUseCase useCase = new AddPlayerUseCase(players, outputBoundary);

        useCase.acceptCommand("add player Pippo");

        verify(players).addPlayer("Pippo");
        verify(outputBoundary).writeOutputLine("players: Pippo");
    }
}