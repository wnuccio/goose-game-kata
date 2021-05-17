package goosegame.config.reset_game;

import goosegame.ApplicationDriver;
import goosegame.ApplicationRunner;
import goosegame.TestConfiguration;
import goosegame.config.add_player.AddPlayerDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationRunnerAcceptanceTest {
    private ApplicationRunner applicationRunner;
    private ApplicationDriver driver;

    @BeforeEach
    void setUp() {
        TestConfiguration configuration = new TestConfiguration();
        applicationRunner = configuration.applicationRunner();
        driver = configuration.applicationDriver();
    }

    @Test
    void run_the_application() {
        assertThat(applicationRunner.isApplicationRunning()).isFalse();

        applicationRunner.runApplication();

        assertThat(applicationRunner.isApplicationRunning()).isTrue();
    }

    @Test
    void stop_the_application() {
        applicationRunner.runApplication();

        assertThat(applicationRunner.isApplicationRunning()).isTrue();

        driver.acceptInputNoOutput("stop game");

        assertThat(applicationRunner.isApplicationRunning()).isFalse();
    }

    @Test
    void reset_the_application_allowing_adding_twice_the_same_player_() {
        AddPlayerDriver addPlayerDriver = new AddPlayerDriver(driver);

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
