package app.domain.rules.bridge;

import app.domain.movement.Movement;
import app.domain.movement.PlayerOnTurn;
import app.domain.player.Board;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JumpOnBridgeRuleTest {
    Board board = new Board();
    JumpOnBridgeRule rule = new JumpOnBridgeRule(board);
    PlayerOnTurn playerOnTurn = mock(PlayerOnTurn.class);

    ArgumentCaptor<Movement> movement = ArgumentCaptor.forClass(Movement.class);

    @Test
    void jump_from_position_6_to_position_12() {
        when(playerOnTurn.positionOfPlayer()).thenReturn(board.position(6));

        rule.apply(playerOnTurn);

        verify(playerOnTurn).applyMovement(movement.capture());

        assertThat(movement.getValue().startPosition()).isEqualTo(board.position(6));
        assertThat(movement.getValue().finalPosition()).isEqualTo(board.position(12));
    }

    @Test
    void remain_on_same_position_if_not_applicable() {
        when(playerOnTurn.positionOfPlayer()).thenReturn(board.position(7));

        rule.apply(playerOnTurn);

        verify(playerOnTurn, never()).applyMovement(any());
    }
}