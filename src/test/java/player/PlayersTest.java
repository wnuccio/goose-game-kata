package player;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayersTest {

    @Test
    void returns_empty_string_for_empty_player_list() {
        assertThat(new Players().allNamesSeparatedByComma()).isEqualTo("");
    }

    @Test
    void returns_just_one_name_for_one_player() {
        Players onePlayer = new Players().addPlayer("Player1");

        assertThat(onePlayer.allNamesSeparatedByComma()).isEqualTo("Player1");
    }

    @Test
    void returns_all_player_names_separated_by_comma() {
        Players players = new Players()
                .addPlayer("Pippo")
                .addPlayer("Pluto")
                .addPlayer("Paperino");

        String playerList = players.allNamesSeparatedByComma();
        assertThat(playerList.contains("Pippo")).isEqualTo(true);
        assertThat(playerList.contains("Pluto")).isEqualTo(true);
        assertThat(playerList.contains("Paperino")).isEqualTo(true);
    }

    @Test
    void position_of_a_new_player_is_zero() {
        Players players = new Players().addPlayer("Pippo");

        assertThat(players.positionOf("Pippo")).isEqualTo(0);
    }

    @Test
    void change_position_of_a_player_given_two_dice() {
        Players players = new Players().addPlayer("Pippo");

        int newPosition = players.moveOnRoll("Pippo", 6, 4);

        assertThat(newPosition).isEqualTo(10);

        newPosition = players.moveOnRoll("Pippo", 3, 1);

        assertThat(newPosition).isEqualTo(14);
    }
}