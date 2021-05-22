package _2_domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    void position_plus_number_returns_new_position() {
        Assertions.assertThat(Position.START.plus(3)).isEqualTo(Position.position(3));
        Assertions.assertThat(Position.position(3).plus(4)).isEqualTo(Position.position(7));
    }

    @Test
    void positions_having_the_goose() {
        Assertions.assertThat(Position.position(5).hasTheGoose()).isTrue();
        Assertions.assertThat(Position.position(9).hasTheGoose()).isTrue();
        Assertions.assertThat(Position.position(14).hasTheGoose()).isTrue();
        Assertions.assertThat(Position.position(18).hasTheGoose()).isTrue();
        Assertions.assertThat(Position.position(23).hasTheGoose()).isTrue();
        Assertions.assertThat(Position.position(27).hasTheGoose()).isTrue();
    }

    @Test
    void position_63_is_win_position() {
        Assertions.assertThat(Position.position(63).isWin()).isTrue();
    }

    @Test
    void a_position_is_over_the_victory_when_has_a_value_greater_than_63() {
        Assertions.assertThat(Position.position(67).isOverTheVictory()).isTrue();
        Assertions.assertThat(Position.position(60).isOverTheVictory()).isFalse();
        Assertions.assertThat(Position.position(63).isOverTheVictory()).isFalse();
    }

    @Test
    void return_a_bounced_position() {
        Assertions.assertThat(Position.position(65).bounced()).isEqualTo(Position.position(61));
        Assertions.assertThat(Position.position(67).bounced()).isEqualTo(Position.position(59));
        Assertions.assertThat(Position.position(60).bounced()).isEqualTo(Position.position(60));
        Assertions.assertThat(Position.position(63).bounced()).isEqualTo(Position.position(63));
    }
}
