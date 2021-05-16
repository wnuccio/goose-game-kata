package usecase.move_player;

import domain.Players;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static domain.Dice.dice;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MovePlayerTest {
    private final Players players = new Players();
    private final MovementPresenter presenter = mock(MovementPresenter.class);
    ComputeMovement computeMovement = mock(ComputeMovement.class);
    private final MovePlayer movePlayer = new MovePlayer(players, computeMovement, presenter);
    private final ArgumentCaptor<MovementView> movementCaptor = ArgumentCaptor.forClass(MovementView.class);

    @Test
    void build_a_movement_view_with_player_name_initial_position_and_dice_values() {
        players.setPositionOf("Pippo", 0);
        MoveCommand moveCommand = move("Pippo", 4, 3);
        Movement movement = mock(Movement.class);
        when(computeMovement.fromCommand(moveCommand)).thenReturn(movement);

        movePlayer.acceptCommand(moveCommand);

        verify(computeMovement).fromCommand(moveCommand);
        verify(presenter).presentMovement(movementCaptor.capture());
        assertThat(movementCaptor.getValue().player()).isEqualTo("Pippo");
        assertThat(movementCaptor.getValue().firstDice()).isEqualTo(4);
        assertThat(movementCaptor.getValue().secondDice()).isEqualTo(3);
        assertThat(movementCaptor.getValue().movement()).isEqualTo(movement);
    }

    private MoveCommand move(String player, int first, int second) {
        return new MoveCommand(player, dice(first, second));
    }
}