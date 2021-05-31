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
    void position_plus_dice_returns_current_position_value_plus_dice_total() {
        when(board.position(9)).thenReturn(position(9));

        assertThat(position(3).plus(new Dice(2, 4)).value).isEqualTo(9);
    }

    @Test
    void position_is_beyond_win_when_added_to_given_dice() {
        when(board.win()).thenReturn(position(10));

        assertThat(position(7).isBeyondWinFor(new Dice(1, 2))).isFalse();
        assertThat(position(7).isBeyondWinFor(new Dice(2, 2))).isTrue();
    }

    @Test
    void position_63_is_win_position() {
        when(board.win()).thenReturn(position(63));

        assertThat(position(63).isWin()).isTrue();
    }

    @Test
    void a_position_is_over_the_victory_when_has_a_value_greater_than_63() {
        when(board.win()).thenReturn(position(63));

        assertThat(position(67).isBeyondWin()).isTrue();
        assertThat(position(60).isBeyondWin()).isFalse();
        assertThat(position(63).isBeyondWin()).isFalse();
    }

    @Test
    void return_a_bounced_position() {
        when(board.win()).thenReturn(position(63));

        when(board.position(61)).thenReturn(position(61));
        assertThat(position(65).bounced().value).isEqualTo(61);

        when(board.position(63)).thenReturn(position(63));
        assertThat(position(63).bounced().value).isEqualTo(63);

        when(board.position(60)).thenReturn(position(60));
        assertThat(position(60).bounced().value).isEqualTo(60);
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
