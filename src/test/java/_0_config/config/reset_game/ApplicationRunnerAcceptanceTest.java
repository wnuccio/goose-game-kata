package _0_config.config.reset_game;

import _0_config.config.ApplicationDriver;
import _0_config.config.ApplicationRunner;
import _0_config.config.TestConfiguration;
import _0_config.config.add_player.AddPlayerDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationRunnerAcceptanceTest {
    private ApplicationRunner applicationRunner;
    private ApplicationDriver driver;
    private AddPlayerDriver addPlayerDriver;

    @BeforeEach
    void setUp() {
        TestConfiguration configuration = new TestConfiguration();
        applicationRunner = configuration.applicationRunner();
        driver = configuration.applicationDriver();
        addPlayerDriver = new AddPlayerDriver(driver);
    }

    @Test
    void stop_the_application() {
        applicationRunner.runApplication();

        addPlayerDriver.addPlayer("Pippo");

        driver.acceptInputNoOutput("stop game");

        assertThrows(IllegalStateException.class, () -> addPlayerDriver.addPlayer("Pluto"));
    }

    @Test
    void reset_the_application_allowing_adding_twice_the_same_player_() {

        applicationRunner.runApplication();

        String output = addPlayerDriver.addPlayer("Pippo");
        addPlayerDriver.verifyPlayersAdded(output, "Pippo");

        output = addPlayerDriver.addPlayer("Pippo");
        addPlayerDriver.verifyAlreadyExistingPlayer(output, "Pippo");

        driver.acceptInputNoOutput("reset game");

        output = addPlayerDriver.addPlayer("Pippo");
        addPlayerDriver.verifyPlayersAdded(output, "Pippo");
    }

}
