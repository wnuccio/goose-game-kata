package app.domain.game;

import app.domain.player.Players;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GameTest {
    private Players players = mock(Players.class);
    private Game game = new Game(players);

    @Test
    void turn_off_the_game() {
        assertThat(game.isOn()).isTrue();

        game.stop();

        assertThat(game.isOn()).isFalse();
    }

    @Test
    void clears_players_collection_on_reset() {
        game.reset();

        verify(players).clear();
    }
}