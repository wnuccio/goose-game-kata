package goosegame.domain;

import org.junit.jupiter.api.Test;

import static goosegame.domain.Position.START;
import static goosegame.domain.Position.position;
import static org.assertj.core.api.Assertions.assertThat;

public class PositionTest {

    @Test
    void position_plus_number_returns_new_position() {
        assertThat(START.plus(3)).isEqualTo(position(3));
        assertThat(position(3).plus(4)).isEqualTo(position(7));
    }

    @Test
    void positions_having_the_goose() {
        assertThat(position(5).hasTheGoose()).isTrue();
        assertThat(position(9).hasTheGoose()).isTrue();
        assertThat(position(14).hasTheGoose()).isTrue();
        assertThat(position(18).hasTheGoose()).isTrue();
        assertThat(position(23).hasTheGoose()).isTrue();
        assertThat(position(27).hasTheGoose()).isTrue();
    }

    @Test
    void position_63_is_win_position() {
        assertThat(position(63).isWin()).isTrue();
    }

    @Test
    void a_position_is_over_the_victory_when_has_a_value_greater_than_63() {
        assertThat(position(67).isOverTheVictory()).isTrue();
        assertThat(position(60).isOverTheVictory()).isFalse();
        assertThat(position(63).isOverTheVictory()).isFalse();
    }

    @Test
    void return_a_bounced_position() {
        assertThat(position(65).bounced()).isEqualTo(position(61));
        assertThat(position(67).bounced()).isEqualTo(position(59));
        assertThat(position(60).bounced()).isEqualTo(position(60));
        assertThat(position(63).bounced()).isEqualTo(position(63));
    }
}
