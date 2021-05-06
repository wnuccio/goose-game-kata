package usecase;

import usecase.AddPlayerUseCase;
import usecase.MovePlayerUseCase;
import org.junit.jupiter.api.Test;
import usecase.UseCaseDispatcher;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    void raise_an_exception_when_command_is_not_recognized() {
        assertThrows(Exception.class,
                () -> dispatcher.acceptCommand("unknown command"));
    }
}