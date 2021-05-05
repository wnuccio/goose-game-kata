import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class AddPlayerAcceptanceTest {

    private static ApplicationRunner applicationRunner;

    @BeforeAll
    static void setUp() {
        applicationRunner = new ApplicationRunner();
        applicationRunner.runApplication();
    }

    @AfterAll
    static void tearDown() {
        applicationRunner.stopApplication();
    }

    @Test
    void app_accepts_add_player_command_and_responds_with_an_output() {
        applicationRunner.acceptInput("add player Pippo");
        String output = applicationRunner.waitForOutput();

        assertThat(output).isEqualTo("players: Pippo");

        applicationRunner.acceptInput("add player Pluto");

        output = applicationRunner.waitForOutput();
        assertThat(output).isEqualTo("players: Pippo, Pluto");
    }

    @Test
    void app_rejected_a_duplicated_add_player_command_with_an_error_message() {
        applicationRunner.acceptInput("add player Pippo");
        applicationRunner.acceptInput("add player Pippo");

        applicationRunner.waitForOutput(); // ignore first output
        String secondOutput = applicationRunner.waitForOutput();

        assertThat(secondOutput).isEqualTo("Pippo: already existing player");
    }
}
