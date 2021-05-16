package usecase.move_player;

import domain.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionTest {
    @Test
    void positions_having_the_goose() {
        assertThat(Position.of(5).hasTheGoose()).isTrue();
        assertThat(Position.of(9).hasTheGoose()).isTrue();
        assertThat(Position.of(14).hasTheGoose()).isTrue();
        assertThat(Position.of(18).hasTheGoose()).isTrue();
        assertThat(Position.of(23).hasTheGoose()).isTrue();
        assertThat(Position.of(27).hasTheGoose()).isTrue();
    }

    @Test
    void position_63_is_win_position() {
        assertThat(Position.of(63).isWin()).isTrue();
    }

    @Test
    void a_position_is_over_the_victory_when_has_a_value_greater_than_63() {
        assertThat(Position.of(67).isOverTheVictory()).isTrue();
        assertThat(Position.of(60).isOverTheVictory()).isFalse();
        assertThat(Position.of(63).isOverTheVictory()).isFalse();
    }
}
