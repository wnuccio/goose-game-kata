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

}
