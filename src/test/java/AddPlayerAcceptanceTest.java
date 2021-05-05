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
    }
}
