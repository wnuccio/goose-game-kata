package usecase.move_player;

import domain.Players;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import usecase.Presenter;

import static domain.Dice.dice;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MovePlayerTest {
    private Players players = new Players();
    private Presenter presenter = mock(Presenter.class);
    ComputeMovement computeMovement = mock(ComputeMovement.class);
    private MovePlayer movePlayer = new MovePlayer(players, computeMovement, presenter);
    private ArgumentCaptor<MovementView> movementCaptor = ArgumentCaptor.forClass(MovementView.class);

    @Test
    void present_an_error_when_the_command_specifies_a_not_present_player() {
        players.clear();

        movePlayer.acceptCommand(move("Pippo", 1, 2));

        verify(presenter).presentNoSuchPlayerError("Pippo");
    }

    @Test
    void build_a_movement_view_with_player_name_initial_position_and_dice_values() {
        players.setPositionOf("Pippo", 0);
        MoveCommand moveCommand = move("Pippo", 4, 3);
        Movement movement = mock(Movement.class);
        when(computeMovement.acceptCommand(moveCommand)).thenReturn(movement);

        movePlayer.acceptCommand(moveCommand);

        verify(computeMovement).acceptCommand(moveCommand);
        verify(presenter).presentMovement(movementCaptor.capture());
        assertThat(movementCaptor.getValue().player()).isEqualTo("Pippo");
        assertThat(movementCaptor.getValue().startPosition()).isEqualTo(0);
        assertThat(movementCaptor.getValue().firstDice()).isEqualTo(4);
        assertThat(movementCaptor.getValue().secondDice()).isEqualTo(3);
        assertThat(movementCaptor.getValue().movement()).isEqualTo(movement);
    }

    private MoveCommand move(String player, int first, int second) {
        return new MoveCommand(player, dice(first, second));
    }
}