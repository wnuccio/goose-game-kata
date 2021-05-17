package goosegame.config.add_player;

import goosegame.BaseAcceptanceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddPlayerAcceptanceTest extends BaseAcceptanceTest {
    private AddPlayerDriver driver;

    @BeforeEach
    void setUp() {
        driver = new AddPlayerDriver(driver());
    }

    @Test
    void app_accepts_add_player_command_and_responds_with_an_output() {
        String output = driver.addPlayer("Pippo");
        driver.verifyPlayersAdded(output,"Pippo");

        output = driver.addPlayer("Pluto");
        driver.verifyPlayersAdded(output,"Pippo", "Pluto");
    }

    @Test
    void app_rejected_a_duplicated_add_player_command_with_an_error_message() {
        driver.addPlayer("Pippo");
        String output = driver.addPlayer("Pippo");

        driver.verifyAlreadyExistingPlayer(output, "Pippo");
    }
}
