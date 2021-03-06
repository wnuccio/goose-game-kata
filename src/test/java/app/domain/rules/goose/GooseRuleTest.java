package app.domain.rules.goose;

import app.domain.player.Board;
import app.domain.player.Dice;
import app.domain.player.PlayerOnTurn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static app.domain.player.BoardForTests.standardBoard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GooseRuleTest {
    Board board;
    GooseRule rule = new GooseRule();

    @BeforeEach
    void setUp() {
        board = standardBoard();
    }

    @Test
    void apply_a_movement_based_on_dice_to_the_player() {
        PlayerOnTurn playerOnTurn = mock(PlayerOnTurn.class);
        when(playerOnTurn.position()).thenReturn(board.position(5));
        when(playerOnTurn.dice()).thenReturn(new Dice(3, 4));

        rule.applyTo(playerOnTurn);

        ArgumentCaptor<GooseMovement> movement = ArgumentCaptor.forClass(GooseMovement.class);
        verify(playerOnTurn).applyMovement(movement.capture());

        assertThat(movement.getValue().startPosition()).isEqualTo(board.position(5));
        assertThat(movement.getValue().finalPosition()).isEqualTo(board.position(12));
    }
}