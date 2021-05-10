package usecase.add_player;

import domain.Players;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayersTest {

    @Test
    void position_of_a_new_player_is_zero() {
        Players players = new Players().addPlayer("Pippo");

        assertThat(players.positionOf("Pippo")).isEqualTo(0);
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

        players.setPositionOf("Pippo", 60);

        assertThat(players.positionOf("Pippo")).isEqualTo(60);
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
}