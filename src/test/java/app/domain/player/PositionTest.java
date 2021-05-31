package app.domain.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PositionTest {

    Board board = mock(Board.class);

    Position position3 = position(3);
    Position position4 = position(4);
    Position position7 = position(7);
    Position position9 = position(9);
    Position position10 = position(10);

    @BeforeEach
    void setUp() {
        when(board.position(7)).thenReturn(position7);
        when(board.position(4)).thenReturn(position4);
        when(board.position(9)).thenReturn(position9);
        when(board.position(10)).thenReturn(position10);
    }

    @Test
    void win_position_is_defined_on_the_board() {
        when(board.win()).thenReturn(position10);

        assertThat(position10.isWin()).isTrue();
        assertThat(position4.isWin()).isFalse();
    }

    @Test
    void returns_current_position_value_plus_dice_total_truncated_to_win_value_as_max() {
        when(board.win()).thenReturn(position10);

        assertThat(position3.plus(new Dice(2, 4))).isEqualTo(position9);
        assertThat(position3.plus(new Dice(3, 4))).isEqualTo(position10);
        assertThat(position3.plus(new Dice(3, 6))).isEqualTo(position10);
    }

    @Test
    void returns_residual_movement_for_given_dice() {
        when(board.win()).thenReturn(position(10));

        assertThat(position7.residualMovementFor(new Dice(1, 1))).isEqualTo(0);
        assertThat(position7.residualMovementFor(new Dice(1, 2))).isEqualTo(0);
        assertThat(position7.residualMovementFor(new Dice(1, 3))).isEqualTo(1);
    }

    @Test
    void returns_current_position_value_minus_given_value() {
        assertThat(position7.minus(0)).isEqualTo(position7);
        assertThat(position7.minus(3)).isEqualTo(position4);
    }

    @Test
    void applies_attached_rule_if_any() {
        Position position = position(6);
        PositionRule positionRule = mock(PositionRule.class);
        position.attachRule(positionRule);

        PlayerOnTurn playerOnTurn = mock(PlayerOnTurn.class);
        position.applyAttachedRule(playerOnTurn);

        verify(positionRule).applyTo(playerOnTurn);
    }

    private Position position(int value) {
        return new Position(board, value, "aName");
    }
}
