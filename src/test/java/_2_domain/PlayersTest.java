package _2_domain;

import _2_domain.player.Board;
import _2_domain.player.Players;
import _2_domain.player.Position;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayersTest {
    private final Board board = new Board();
    private final Players players = new Players();

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

    @Test
    void find_any_other_player_on_given_position() {
        players.setPositionOf("Pippo", position(15));
        players.setPositionOf("Pluto", position(15));
        players.setPositionOf("Topolino", position(15));
        players.setPositionOf("Paperino", position(10));

        assertThat(players.playersOnSamePositionOf("Pippo").size()).isEqualTo(2);
        assertThat(players.playersOnSamePositionOf("Pippo").containsAll(asList("Pluto", "Topolino"))).isTrue();
        assertThat(players.playersOnSamePositionOf("Paperino")).isEqualTo(emptyList());
    }

    private Position position(int i) {
        return board.position(i);
    }
}