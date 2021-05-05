import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class AddPlayerAcceptanceTest extends BaseAcceptanceTest {

    @Test
    void app_accepts_add_player_command_and_responds_with_an_output() {
        gooseGame.acceptInput("add player Pippo");
        String output = gooseGame.waitForOutput();

        assertThat(output).isEqualTo("players: Pippo");

        gooseGame.acceptInput("add player Pluto");

        output = gooseGame.waitForOutput();
        assertThat(output).isEqualTo("players: Pippo, Pluto");
    }

    @Test
    void app_rejected_a_duplicated_add_player_command_with_an_error_message() {
        gooseGame.acceptInput("add player Pippo");
        gooseGame.acceptInput("add player Pippo");

        gooseGame.waitForOutput(); // ignore first output
        String secondOutput = gooseGame.waitForOutput();

        assertThat(secondOutput).isEqualTo("Pippo: already existing player");
    }
}
