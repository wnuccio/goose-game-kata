package app.domain;

import app.domain.player.Board;
import app.domain.player.Player;
import app.domain.player.Players;
import app.domain.player.Position;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayersTest {
    private final Board board = new Board();
    private final Players players = new Players();

    @Test
    void find_player_by_name() {
        players.add(new Player("Pippo"));

        Player player = players.findByName("Pippo");

        assertThat(player.name()).isEqualTo("Pippo");
    }

    @Test
    void raises_an_exception_when_asked_for_a_position_of_an_absent_players() {
        assertThrows(NoSuchElementException.class, () -> {
            Players emptyPlayerCollection = new Players();

            emptyPlayerCollection.positionOf("anyPlayer");
        });
    }

    @Test
    void change_position_of_a_player() {
        players.setPositionOf("Pippo", position(60));
        players.setPositionOf("Pippo", position(60));

        assertThat(players.positionOf("Pippo")).isEqualTo(position(60));
    }

    @Test
    void remove_all_players_on_clear() {
        players.setPositionOf("Pippo", position(1));
        players.setPositionOf("Pluto", position(2));

        assertThat(players.contains("Pippo")).isEqualTo(true);
        assertThat(players.contains("Pluto")).isEqualTo(true);

        players.clear();

        assertThat(players.contains("Pippo")).isEqualTo(false);
        assertThat(players.contains("Pluto")).isEqualTo(false);
    }

    private Position position(int i) {
        return board.position(i);
    }
}