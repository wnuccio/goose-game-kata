package app.domain.rules.bridge;

import app.domain.player.Board;
import app.domain.player.Player;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class JumpOnBridgeRuleTest {
    Board board = new Board();
    JumpOnBridgeRule rule = new JumpOnBridgeRule(board);

    @Test
    void apply_a_movement_towards_position_12_to_the_player() {
        Player player = mock(Player.class);

        rule.applyTo(player);

        ArgumentCaptor<JumpOnBridgeMovement> movement = ArgumentCaptor.forClass(JumpOnBridgeMovement.class);
        verify(player).applyMovement(movement.capture());

        assertThat(movement.getValue().startPosition()).isEqualTo(board.position(6));
        assertThat(movement.getValue().finalPosition()).isEqualTo(board.position(12));
    }
}