package app.domain;

import app.domain.player.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {

    Board board = new Board();

    @Test
    void position_plus_number_returns_new_position() {
        Assertions.assertThat(board.position(0).plus(3)).isEqualTo(board.position(3));
        Assertions.assertThat(board.position(3).plus(4)).isEqualTo(board.position(7));
    }

    @Test
    void positions_having_the_goose() {
        Assertions.assertThat(board.position(5).hasTheGoose()).isTrue();
        Assertions.assertThat(board.position(9).hasTheGoose()).isTrue();
        Assertions.assertThat(board.position(14).hasTheGoose()).isTrue();
        Assertions.assertThat(board.position(18).hasTheGoose()).isTrue();
        Assertions.assertThat(board.position(23).hasTheGoose()).isTrue();
        Assertions.assertThat(board.position(27).hasTheGoose()).isTrue();
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
}
