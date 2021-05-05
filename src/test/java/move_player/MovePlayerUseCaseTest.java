package move_player;

import add_player.OutputBoundary;
import add_player.Players;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovePlayerUseCaseTest {

    @Test
    void moves_a_player_from_start_to_the_specified_position() {

        Players players = mock(Players.class);
        OutputBoundary outputBoundary = mock(OutputBoundary.class);
        MovePlayerUseCase useCase = new MovePlayerUseCase(players, outputBoundary);

        when(players.positionOf("Pippo")).thenReturn(0);

        useCase.acceptCommand("move Pippo 4, 2");

        verify(players).move("Pippo", 6);
        verify(outputBoundary).writeOutputLine ("Pippo rolls 4, 2. Pippo moves from Start to 6");
    }
}