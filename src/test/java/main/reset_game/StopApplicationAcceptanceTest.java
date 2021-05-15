package main.reset_game;

import main.ApplicationDriver;
import main.ApplicationRunner;
import main.GooseGameAppBuilderForTest;
import main.SharedMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StopApplicationAcceptanceTest {
    private ResetDriver resetDriver;
    private ApplicationRunner applicationRunner;
    private ApplicationDriver driver;

    @BeforeEach
    void setUp() {
        SharedMemory sharedMemory = new SharedMemory();
        GooseGameAppBuilderForTest builder = new GooseGameAppBuilderForTest(sharedMemory, null);
        applicationRunner = new ApplicationRunner(builder);

        driver = new ApplicationDriver(sharedMemory);
        resetDriver = new ResetDriver(driver);
    }

    @Test
    void after_stop_command_the_application_is_no_more_running() {
        assertThat(applicationRunner.isApplicationRunning()).isFalse();

        applicationRunner.runApplication();

        assertThat(applicationRunner.isApplicationRunning()).isTrue();

        driver.acceptInput("stop game");

        assertThat(applicationRunner.isApplicationRunning()).isFalse();
    }
}
