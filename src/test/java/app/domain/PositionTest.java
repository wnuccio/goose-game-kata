package app.domain;

import app.domain.player.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PositionTest {

    Board board = new Board();

    @Test
    void position_plus_dice_returns_current_position_value_plus_dice_total() {
        Assertions.assertThat(board.position(0).plus(new Dice(3, 4))).isEqualTo(board.position(7));
        Assertions.assertThat(board.position(3).plus(new Dice(2, 4))).isEqualTo(board.position(9));
    }

    @Test
    void position_63_is_win_position() {
        Assertions.assertThat(board.position(63).isWin()).isTrue();
    }

    @Test
    void a_position_is_over_the_victory_when_has_a_value_greater_than_63() {
        Assertions.assertThat(board.position(67).isBeyondWin()).isTrue();
        Assertions.assertThat(board.position(60).isBeyondWin()).isFalse();
        Assertions.assertThat(board.position(63).isBeyondWin()).isFalse();
    }

    @Test
    void return_a_bounced_position() {
        Assertions.assertThat(board.position(65).bounced()).isEqualTo(board.position(61));
        Assertions.assertThat(board.position(67).bounced()).isEqualTo(board.position(59));
        Assertions.assertThat(board.position(60).bounced()).isEqualTo(board.position(60));
        Assertions.assertThat(board.position(63).bounced()).isEqualTo(board.position(63));
    }

    @Test
    void applies_attached_rule_if_any() {
        Position position = new Board().position(6);
        PositionRule positionRule = mock(PositionRule.class);
        position.attachRule(positionRule);

        PlayerOnTurn playerOnTurn = mock(PlayerOnTurn.class);
        position.applyAttachedRule(playerOnTurn);

        verify(positionRule).applyTo(playerOnTurn);
    }
}
