package dispatcher;

import add_player.AddPlayerUseCase;
import move_player.MovePlayerUseCase;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UseCaseDispatcherTest {
    AddPlayerUseCase addPlayerUseCase = mock(AddPlayerUseCase.class);
    MovePlayerUseCase movePlayerUseCase = mock(MovePlayerUseCase.class);
    UseCaseDispatcher dispatcher = new UseCaseDispatcher(addPlayerUseCase, movePlayerUseCase);

    @Test
    void invoke_add_player_use_case_for_the_matching_command() {
        dispatcher.acceptCommand("add player Pippo");

        verify(addPlayerUseCase).acceptCommand("add player Pippo");
    }

    @Test
    void invoke_move_player_use_case_for_the_matching_command() {
        dispatcher.acceptCommand("move Pippo 4, 2");

        verify(movePlayerUseCase).acceptCommand("move Pippo 4, 2");
    }


}