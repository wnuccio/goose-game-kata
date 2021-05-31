package app.domain.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayersTest {
    private final Players players = new Players();

    @Test
    void find_player_by_name() {
        Player pippo = new Player("Pippo", null);
        players.add(pippo);

        Player player = players.find("Pippo");

        assertThat(player.name()).isEqualTo("Pippo");
    }

    @Test
    void raises_an_exception_when_asked_for_an_absent_players() {
        assertThrows(NoSuchElementException.class, () -> {
            Players emptyPlayerCollection = new Players();

            emptyPlayerCollection.find("anyPlayer");
        });
    }

    @Test
    void remove_all_players_on_clear() {
        players.add(new Player("Pippo", null));
        players.add(new Player("Pluto", null));

        assertThat(players.contains("Pippo")).isEqualTo(true);
        assertThat(players.contains("Pluto")).isEqualTo(true);

        players.clear();

        assertThat(players.contains("Pippo")).isEqualTo(false);
        assertThat(players.contains("Pluto")).isEqualTo(false);
    }

    @Test
    void find_any_other_player_on_the_same_position_of_a_give_player() {
        Board board = new Board();
        Player pippo = new Player("Pippo", board.position(15));
        Player pluto = new Player("Pluto", board.position(15));
        Player topolino = new Player("Topolino", board.position(15));
        Player paperino = new Player("Paperino", board.position(10));
        players.add(pippo);
        players.add(pluto);
        players.add(topolino);
        players.add(paperino);

        List<Player> encounteredOpponents = players.opponentsOnSamePositionOf(pippo);

        Assertions.assertThat(encounteredOpponents.size()).isEqualTo(2);
        Assertions.assertThat(encounteredOpponents.containsAll(asList(pluto, topolino))).isTrue();
    }


}
