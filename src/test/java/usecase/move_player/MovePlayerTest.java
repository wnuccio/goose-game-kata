package usecase.move_player;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import usecase.Presenter;
import usecase.add_player.Players;

import static main.test.DiceForTest.dice;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static usecase.move_player.SimpleMovement.BRIDGE_TARGET;

class MovePlayerTest {
    private Players players = new Players();
    private Presenter presenter = mock(Presenter.class);
    private MovePlayer useCase = new MovePlayer(players, presenter);
    private ArgumentCaptor<Movement> movementCaptor = ArgumentCaptor.forClass(Movement.class);

    @Test
    void present_an_error_when_the_command_specifies_a_not_present_player() {
        players.clear();

        useCase.acceptCommand(move("Pippo", 1, 2));

        verify(presenter).presentNoSuchPlayerError("Pippo");
    }

    @Test
    void build_a_movement_with_player_name_initial_position_and_dice_values() {
        players.setPositionOf("Pippo", 0);

        useCase.acceptCommand(move("Pippo", 4, 3));

        assertThat(players.positionOf("Pippo")).isEqualTo(7);
        verify(presenter).presentMovement(movementCaptor.capture());
        assertThat(movementCaptor.getValue().player()).isEqualTo("Pippo");
        assertThat(movementCaptor.getValue().fromPosition()).isEqualTo(0);
        assertThat(movementCaptor.getValue().firstDice()).isEqualTo(4);
        assertThat(movementCaptor.getValue().secondDice()).isEqualTo(3);
    }

    @Test
    void repeat_movement_on_the_bridge() {
        players.setPositionOf("Pippo", 4);

        useCase.acceptCommand(move("Pippo", 1, 1));

        assertThat(players.positionOf("Pippo")).isEqualTo(BRIDGE_TARGET);
        verify(presenter).presentMovement(movementCaptor.capture());

        Movement movement = movementCaptor.getValue();
        assertThat(movement.isJumpOnBridge()).isTrue();
    }

    @Test
    void repeat_movement_on_the_goose() {
        players.setPositionOf("Pippo", 3);

        useCase.acceptCommand(move("Pippo", 1, 1));

        assertThat(players.positionOf("Pippo")).isEqualTo(7);
        verify(presenter).presentMovement(movementCaptor.capture());

        Movement movement = movementCaptor.getValue();
        assertThat(movement.isRepeatOnGoose()).isTrue();
    }

    private MoveCommand move(String player, int first, int second) {
        return new MoveCommand(player, dice(first, second));
    }

    private MoveCommand move(String player) {
        return new MoveCommand(player);
    }
}