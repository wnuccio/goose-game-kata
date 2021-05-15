package main.reset_game;

import main.ApplicationDriver;
import main.ApplicationRunner;
import main.GooseGameAppBuilderForTest;
import main.SharedMemory;
import main.add_player.AddPlayerDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StopApplicationAcceptanceTest {
    private ApplicationRunner applicationRunner;
    private ApplicationDriver driver;

    @BeforeEach
    void setUp() {
        SharedMemory sharedMemory = new SharedMemory();
        GooseGameAppBuilderForTest builder = new GooseGameAppBuilderForTest(sharedMemory, null);
        applicationRunner = new ApplicationRunner(builder);

        driver = new ApplicationDriver(sharedMemory);
    }

    @Test
    void after_stop_command_the_application_is_no_more_running() {
        assertThat(applicationRunner.isApplicationRunning()).isFalse();

        applicationRunner.runApplication();

        assertThat(applicationRunner.isApplicationRunning()).isTrue();

        driver.acceptInput("stop game");

        assertThat(applicationRunner.isApplicationRunning()).isFalse();
    }

    @Test
    void after_reset_an_existing_player_becomes_a_new_player() {
        applicationRunner.runApplication();
        AddPlayerDriver addPlayerDriver = new AddPlayerDriver(driver);

        String output = addPlayerDriver.addPlayer("Pippo");
        addPlayerDriver.verifyPlayersAdded(output, "Pippo");

        output = addPlayerDriver.addPlayer("Pippo");
        addPlayerDriver.verifyAlreadyExistingPlayer(output, "Pippo");

        driver.acceptInput("reset game");

        output = addPlayerDriver.addPlayer("Pippo");
        addPlayerDriver.verifyPlayersAdded(output, "Pippo");
    }
}
