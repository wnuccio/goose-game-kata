package app.domain.rules.goose;

import app.domain.movement.Dice;
import app.domain.movement.Movement;
import app.domain.movement.PlayerOnTurn;
import app.domain.player.Board;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GooseRuleTest {
    Board board = new Board();
    GooseRule rule = new GooseRule();
    PlayerOnTurn playerOnTurn = mock(PlayerOnTurn.class);

    ArgumentCaptor<Movement> movement = ArgumentCaptor.forClass(Movement.class);

    @Test
    void move_again_when_lands_on_a_goose_position() {
        when(playerOnTurn.position()).thenReturn(board.position(5));
        when(playerOnTurn.isOnTheGoose())
                .thenReturn(true)
                .thenReturn(false);
        when(playerOnTurn.dice()).thenReturn(new Dice(3, 4));

        rule.apply(playerOnTurn);

        verify(playerOnTurn).applyMovement(movement.capture());

        assertThat(movement.getValue().startPosition()).isEqualTo(board.position(5));
        assertThat(movement.getValue().finalPosition()).isEqualTo(board.position(12));
    }

    @Test
    void move_again_more_times_while_landing_on_gooses() {
        when(playerOnTurn.position())
                .thenReturn(board.position(14))
                .thenReturn(board.position(18));

        when(playerOnTurn.isOnTheGoose())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);
        when(playerOnTurn.dice()).thenReturn(new Dice(2, 2));

        rule.apply(playerOnTurn);

        verify(playerOnTurn, times(2)).applyMovement(movement.capture());

        assertThat(movement.getAllValues().get(0).startPosition()).isEqualTo(board.position(14));
        assertThat(movement.getAllValues().get(0).finalPosition()).isEqualTo(board.position(18));
        assertThat(movement.getAllValues().get(1).startPosition()).isEqualTo(board.position(18));
        assertThat(movement.getAllValues().get(1).finalPosition()).isEqualTo(board.position(22));
    }

    @Test
    void do_not_move_again_when_not_on_a_goose_position() {
        when(playerOnTurn.isOnTheGoose()).thenReturn(false);

        rule.apply(playerOnTurn);

        verify(playerOnTurn, never()).applyMovement(any());
    }
}