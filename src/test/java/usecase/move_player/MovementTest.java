package usecase.move_player;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MovementTest {
    @Test
    void movement_is_victory_when_towards_win_position() {
        Movement movement = Movement.of("aPlayer").from(60).to(Movement.WIN_POSITION);

        assertThat(movement.isVictory()).isTrue();
    }
}