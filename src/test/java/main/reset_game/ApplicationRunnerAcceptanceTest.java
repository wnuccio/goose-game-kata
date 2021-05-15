package main.reset_game;

import main.ApplicationDriver;
import main.ApplicationRunner;
import main.GooseGameAppBuilderForTest;
import main.SharedMemory;
import main.add_player.AddPlayerDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationRunnerAcceptanceTest {
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
    void run_the_application() {
        assertThat(applicationRunner.isApplicationRunning()).isFalse();

        applicationRunner.runApplication();

        assertThat(applicationRunner.isApplicationRunning()).isTrue();
    }

    @Test
    void stop_the_application() {
        applicationRunner.runApplication();

        assertThat(applicationRunner.isApplicationRunning()).isTrue();

        driver.acceptInput("stop game");

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

        driver.acceptInput("reset game");

        output = addPlayerDriver.addPlayer("Pippo");
        addPlayerDriver.verifyPlayersAdded(output, "Pippo");
    }
}