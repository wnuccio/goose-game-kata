package move_player;

import add_player.OutputBoundary;
import add_player.Players;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MovePlayerUseCaseTest {

    @Test
    void moves_a_player_from_start_to_the_specified_position() {
        OutputBoundary outputBoundary = mock(OutputBoundary.class);
        Players players = new Players().addPlayer("Pippo");

        MovePlayerUseCase useCase = new MovePlayerUseCase(players, outputBoundary);

        useCase.acceptCommand("move Pippo 4, 2");

        verify(outputBoundary).writeOutputLine ("Pippo rolls 4, 2. Pippo moves from Start to 6");
    }
}