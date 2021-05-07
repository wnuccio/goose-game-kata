package main;

import main.helpers.BaseAcceptanceTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class AddPlayerAcceptanceTest extends BaseAcceptanceTest {

    @Test
    void app_accepts_add_player_command_and_responds_with_an_output() {
        String output = game.addPlayer("Pippo");

        assertThat(output).isEqualTo("players: Pippo");

        output = game.addPlayer("Pluto");

        assertThat(output).isEqualTo("players: Pippo, Pluto");
    }

    @Test
    void app_rejected_a_duplicated_add_player_command_with_an_error_message() {
        game.addPlayer("Pippo");
        String output = game.addPlayer("Pippo");

        assertThat(output).isEqualTo("Pippo: already existing player");
    }
}
