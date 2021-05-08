package usecase.move_player;

import main.test.DiceForTest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import usecase.Presenter;
import usecase.add_player.Players;

import static main.test.DiceForTest.dice;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MovePlayerUseCaseTest {
    private Players players = new Players();
    private DiceForTest dice = new DiceForTest(5, 6);
    private Presenter presenter = mock(Presenter.class);
    private MovePlayerUseCase useCase = new MovePlayerUseCase(players, dice, presenter);
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

        useCase.acceptCommand(move("Pippo", 4, 1));

        assertThat(players.positionOf("Pippo")).isEqualTo(5);
        verify(presenter).presentMovement(movementCaptor.capture());
        assertThat(movementCaptor.getValue().player()).isEqualTo("Pippo");
        assertThat(movementCaptor.getValue().fromPosition()).isEqualTo(0);
        assertThat(movementCaptor.getValue().firstDice()).isEqualTo(4);
        assertThat(movementCaptor.getValue().secondDice()).isEqualTo(1);
    }

    @Test
    void build_a_movement_with_a_rolled_dice_when_command_does_not_specify_any_dice() {
        dice.values(5, 6);
        players.setPositionOf("Pippo", 0);

        useCase.acceptCommand(move("Pippo"));

        assertThat(players.positionOf("Pippo")).isEqualTo(11);
        verify(presenter).presentMovement(movementCaptor.capture());
        assertThat(movementCaptor.getValue().firstDice()).isEqualTo(5);
        assertThat(movementCaptor.getValue().secondDice()).isEqualTo(6);
    }

    @Test
    void compute_movement_on_the_bridge() {
        players.setPositionOf("Pippo", 4);

        useCase.acceptCommand(move("Pippo", 1, 1));

        assertThat(players.positionOf("Pippo")).isEqualTo(12);
        verify(presenter).presentMovement(movementCaptor.capture());

        Movement movement = movementCaptor.getValue();
        assertThat(movement.toPosition()).isEqualTo(12);
        assertThat(movement.isJumpOnBridge()).isTrue();
    }

    private MoveCommand move(String player, int first, int second) {
        return new MoveCommand(player, dice(first, second));
    }

    private MoveCommand move(String player) {
        return new MoveCommand(player);
    }
}