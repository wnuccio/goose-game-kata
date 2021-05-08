package usecase.move_player;

import main.test.DiceForTest;
import org.junit.jupiter.api.Test;
import usecase.Presenter;
import usecase.add_player.Players;

import static main.test.DiceForTest.dice;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static usecase.move_player.Movement.of;

class MovePlayerUseCaseTest {
    private Players players = new Players();
    private DiceForTest dice = new DiceForTest(5, 6);
    private Presenter presenter = mock(Presenter.class);
    private MovePlayerUseCase useCase = new MovePlayerUseCase(players, dice, presenter);

    @Test
    void moves_a_player_from_start_to_the_specified_position() {
        players.setPositionOf("Pippo", 0);

        useCase.acceptCommand(move("Pippo", 4, 2));

        assertThat(players.positionOf("Pippo")).isEqualTo(6);
        verify(presenter).presentMovement(of("Pippo").givenRoll(dice(4, 2)).from(0).end());
    }

    @Test
    void present_a_victory_when_player_moves_to_the_ending_position() {
        players.setPositionOf("Pippo", 60);

        useCase.acceptCommand(move("Pippo", 1, 2));

        assertThat(players.positionOf("Pippo")).isEqualTo(63);
        verify(presenter).presentMovement(of("Pippo").givenRoll(dice(1, 2)).from(60).end());
    }

    @Test
    void present_an_error_when_the_command_specifies_a_not_present_player() {
        players.clear();

        useCase.acceptCommand(move("Pippo", 1, 2));

        verify(presenter).presentNoSuchPlayerError("Pippo");
    }

    @Test
    void present_a_bouncing_when_player_moves_over_the_ending_position() {
        players.setPositionOf("Pippo", 60);

        useCase.acceptCommand(move("Pippo", 2, 3));

        assertThat(players.positionOf("Pippo")).isEqualTo(61);
        verify(presenter).presentMovement(of("Pippo").givenRoll(dice(2, 3)).from(60).end());
    }

    @Test
    void compute_movement_by_rolling_dice_when_command_does_not_specify_any_value() {
        dice.values(5, 6);
        players.setPositionOf("Pippo", 0);

        useCase.acceptCommand(move("Pippo"));

        assertThat(players.positionOf("Pippo")).isEqualTo(11);
        verify(presenter).presentMovement(of("Pippo").givenRoll(dice(5, 6)).from(0).end());
    }

    private MoveCommand move(String player, int first, int second) {
        return new MoveCommand(player, dice(first, second));
    }

    private MoveCommand move(String player) {
        return new MoveCommand(player);
    }
}