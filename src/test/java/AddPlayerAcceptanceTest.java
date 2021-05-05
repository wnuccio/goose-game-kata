import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class AddPlayerAcceptanceTest extends BaseAcceptanceTest {

    @Test
    void app_accepts_add_player_command_and_responds_with_an_output() {
        String output = gooseGame.acceptInput("add player Pippo");

        assertThat(output).isEqualTo("players: Pippo");

        output = gooseGame.acceptInput("add player Pluto");

        assertThat(output).isEqualTo("players: Pippo, Pluto");
    }

    @Test
    void app_rejected_a_duplicated_add_player_command_with_an_error_message() {
        gooseGame.acceptInput("add player Pippo");
        String output = gooseGame.acceptInput("add player Pippo");

        assertThat(output).isEqualTo("Pippo: already existing player");
    }
}
