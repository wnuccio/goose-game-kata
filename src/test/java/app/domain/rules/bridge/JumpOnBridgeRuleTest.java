package app.domain.rules.bridge;

import app.domain.player.Board;
import app.domain.player.BoardBuilder;
import app.domain.player.PlayerOnTurn;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JumpOnBridgeRuleTest {
    Board board = BoardBuilder.standardBoard();
    JumpOnBridgeRule rule = new JumpOnBridgeRule(board.position(12));

    @Test
    void apply_a_movement_towards_position_12_to_the_player() {
        PlayerOnTurn playerOnTurn = mock(PlayerOnTurn.class);
        when(playerOnTurn.position()).thenReturn(board.position(6));

        rule.applyTo(playerOnTurn);

        ArgumentCaptor<JumpOnBridgeMovement> movement = ArgumentCaptor.forClass(JumpOnBridgeMovement.class);
        verify(playerOnTurn).applyMovement(movement.capture());

        assertThat(movement.getValue().startPosition()).isEqualTo(board.position(6));
        assertThat(movement.getValue().finalPosition()).isEqualTo(board.position(12));
    }
}