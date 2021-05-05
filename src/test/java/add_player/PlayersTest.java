package add_player;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayersTest {

    @Test
    void returns_all_player_names_separated_by_comma() {
        Players players = new Players()
                .addPlayer("Pippo")
                .addPlayer("Pluto")
                .addPlayer("Paperino");

        assertThat(players.allNamesSeparatedByComma()).isEqualTo("Pippo, Pluto, Paperino");
    }
}