package app.domain.player;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PositionTest {

    Board board = mock(Board.class);

    private Position position(int value) {
        return new Position(board, value, "aName");
    }

    @Test
    void returns_current_position_value_plus_dice_total_truncated_to_win_value_as_max() {
        Position position3 = position(3);
        Position position9 = position(9);
        Position position10 = position(10);
        when(board.win()).thenReturn(position10);

        when(board.position(9)).thenReturn(position9);
        when(board.position(10)).thenReturn(position10);

        assertThat(position3.plus(new Dice(2, 4))).isEqualTo(position9);
        assertThat(position3.plus(new Dice(3, 4))).isEqualTo(position10);
        assertThat(position3.plus(new Dice(3, 6))).isEqualTo(position10);
    }

    @Test
    void returns_residual_movement_for_given_dice() {
        when(board.win()).thenReturn(position(10));

        assertThat(position(7).residualMovementFor(new Dice(1, 1))).isEqualTo(0);
        assertThat(position(7).residualMovementFor(new Dice(1, 2))).isEqualTo(0);
        assertThat(position(7).residualMovementFor(new Dice(1, 3))).isEqualTo(1);
    }

    @Test
    void returns_current_position_value_minus_given_value() {
        when(board.position(7)).thenReturn(position(7));
        assertThat(position(7).minus(0).value).isEqualTo(7);

        when(board.position(4)).thenReturn(position(4));
        assertThat(position(7).minus(3).value).isEqualTo(4);
    }

    @Test
    void position_63_is_win_position() {
        when(board.win()).thenReturn(position(63));

        assertThat(position(63).isWin()).isTrue();
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
}
