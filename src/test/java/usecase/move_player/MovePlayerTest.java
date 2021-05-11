package usecase.move_player;

import domain.Players;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import usecase.Presenter;

import static domain.Dice.dice;
import static domain.Position.BRIDGE_TARGET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MovePlayerTest {
    private Players players = new Players();
    private Presenter presenter = mock(Presenter.class);
    private MovePlayer useCase = new MovePlayer(players, presenter);
    private ArgumentCaptor<MovementView> movementCaptor = ArgumentCaptor.forClass(MovementView.class);

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
        assertThat(movementCaptor.getValue().startPosition()).isEqualTo(0);
        assertThat(movementCaptor.getValue().firstDice()).isEqualTo(4);
        assertThat(movementCaptor.getValue().secondDice()).isEqualTo(3);
    }

    @Test
    void repeat_movement_on_bouncing() {
        players.setPositionOf("Pippo", 62);

        useCase.acceptCommand(move("Pippo", 3, 4));

        assertThat(players.positionOf("Pippo")).isEqualTo(57);
        verify(presenter).presentMovement(movementCaptor.capture());

        MovementView movement = movementCaptor.getValue();
        assertThat(movement.type()).isEqualTo(MovementType.BOUNCING);
        assertThat(movement.finalPosition()).isEqualTo(57);
    }

    @Test
    void repeat_movement_on_the_bridge() {
        players.setPositionOf("Pippo", 4);

        useCase.acceptCommand(move("Pippo", 1, 1));

        assertThat(players.positionOf("Pippo")).isEqualTo(BRIDGE_TARGET);
        verify(presenter).presentMovement(movementCaptor.capture());

        MovementView movement = movementCaptor.getValue();
        assertThat(movement.type()).isEqualTo(MovementType.JUMP_ON_BRIDGE);
        assertThat(movement.finalPosition()).isEqualTo(BRIDGE_TARGET);
    }

    @Test
    void repeat_movement_on_the_goose() {
        players.setPositionOf("Pippo", 3);

        useCase.acceptCommand(move("Pippo", 1, 1));

        assertThat(players.positionOf("Pippo")).isEqualTo(7);
        verify(presenter).presentMovement(movementCaptor.capture());

        MovementView movement = movementCaptor.getValue();
        assertThat(movement.type()).isEqualTo(MovementType.REPEAT_ON_GOOSE);
        assertThat(movement.finalPosition()).isEqualTo(7);

    }

    @Test
    void repeat_movement_on_the_goose_more_times() {
        players.setPositionOf("Pippo", 10);

        useCase.acceptCommand(move("Pippo", 2, 2));

        assertThat(players.positionOf("Pippo")).isEqualTo(22);
        verify(presenter).presentMovement(movementCaptor.capture());

        MovementView movement = movementCaptor.getValue();
        assertThat(movement.finalPosition()).isEqualTo(22);
        assertThat(movement.type()).isEqualTo(MovementType.REPEAT_ON_GOOSE);

    }

    private MoveCommand move(String player, int first, int second) {
        return new MoveCommand(player, dice(first, second));
    }
}