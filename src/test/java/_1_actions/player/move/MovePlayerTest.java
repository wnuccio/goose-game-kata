package _1_actions.player.move;

import _1_actions.player.move.presenter.MovementPresenter;
import _1_actions.player.move.presenter.MovementView;
import _1_actions.player.move.rules.RuleProcessor;
import _2_domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MovePlayerTest {
    private final Players players = new Players();
    private final MovementPresenter presenter = mock(MovementPresenter.class);
    RuleProcessor ruleProcessor = mock(RuleProcessor.class);
    private final MovePlayer movePlayer = new MovePlayer(players, ruleProcessor, presenter);
    private final ArgumentCaptor<MovementView> movementCaptor = ArgumentCaptor.forClass(MovementView.class);

    @Test
    void build_a_movement_view_with_player_name_initial_position_and_dice_values() {
        players.setPositionOf("Pippo", Position.START);
        MoveCommand moveCommand = move("Pippo", 4, 3);
        List<Movement> movements = new ArrayList<>();
        when(ruleProcessor.fromCommand(moveCommand)).thenReturn(movements);

        movePlayer.acceptCommand(moveCommand);

        verify(presenter).presentMovement(movementCaptor.capture());
        assertThat(movementCaptor.getValue().player()).isEqualTo("Pippo");
        assertThat(movementCaptor.getValue().firstDice()).isEqualTo(4);
        assertThat(movementCaptor.getValue().secondDice()).isEqualTo(3);
        Assertions.assertThat(movementCaptor.getValue().movements()).isEqualTo(movements);
    }

    private MoveCommand move(String player, int first, int second) {
        return new MoveCommand(player, Dice.dice(first, second));
    }
}