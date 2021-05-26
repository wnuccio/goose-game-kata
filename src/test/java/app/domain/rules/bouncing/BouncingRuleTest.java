package app.domain.rules.bouncing;

import app.domain.movement.Movement;
import app.domain.movement.PlayerOnTurn;
import app.domain.player.Board;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BouncingRuleTest {
    Board board = new Board();
    BouncingRule rule = new BouncingRule(board);
    PlayerOnTurn playerOnTurn = mock(PlayerOnTurn.class);

    ArgumentCaptor<Movement> movement = ArgumentCaptor.forClass(Movement.class);

    @Test
    void correct_position_over_63_with_bouncing() {
        when(playerOnTurn.position()).thenReturn(board.position(69));

        rule.apply(playerOnTurn);

        verify(playerOnTurn).applyMovement(movement.capture());

        assertThat(movement.getValue().startPosition()).isEqualTo(board.win());
        assertThat(movement.getValue().finalPosition()).isEqualTo(board.position(57));
    }

    @Test
    void do_not_correct_position_before_63() {
        when(playerOnTurn.position()).thenReturn(board.position(60));

        rule.apply(playerOnTurn);

        verify(playerOnTurn, never()).applyMovement(any());
    }

    @Test
    void do_not_correct_position_equals_to_63() {
        when(playerOnTurn.position()).thenReturn(board.position(63));

        rule.apply(playerOnTurn);

        verify(playerOnTurn, never()).applyMovement(any());
    }
}