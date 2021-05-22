package _2_domain;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static _2_domain.Position.position;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayersTest {

    @Test
    void position_of_a_new_player_is_zero() {
        Players players = new Players().addPlayer("Pippo");

        String pippo = "Pippo";
        int expected = 0;
        assertThat(players.positionOf(pippo)).isEqualTo(position(expected));
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
        Players players = new Players().addPlayer("Pippo");

        players.setPositionOf("Pippo", position(60));

        assertThat(players.positionOf("Pippo")).isEqualTo(position(60));
    }

    @Test
    void remove_all_players_on_clear() {
        Players players = new Players().addPlayer("Pippo").addPlayer("Pluto");

        assertThat(players.contains("Pippo")).isEqualTo(true);
        assertThat(players.contains("Pluto")).isEqualTo(true);

        players.clear();

        assertThat(players.contains("Pippo")).isEqualTo(false);
        assertThat(players.contains("Pluto")).isEqualTo(false);
    }

    @Test
    void find_any_other_player_on_given_position() {
        Players players = new Players();
        players.setPositionOf("Pippo", position(15));
        players.setPositionOf("Pluto", position(15));
        players.setPositionOf("Topolino", position(15));
        players.setPositionOf("Paperino", position(10));

        assertThat(players.playersOnSamePositionOf("Pippo").size()).isEqualTo(2);
        assertThat(players.playersOnSamePositionOf("Pippo").containsAll(asList("Pluto", "Topolino"))).isTrue();
        assertThat(players.playersOnSamePositionOf("Paperino")).isEqualTo(emptyList());
    }
}