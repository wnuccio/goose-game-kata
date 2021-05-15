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

    @BeforeEach
    void setUp() {
        SharedMemory sharedMemory = new SharedMemory();
        GooseGameAppBuilderForTest builder = new GooseGameAppBuilderForTest(sharedMemory, null);
        applicationRunner = new ApplicationRunner(builder);

        resetDriver = new ResetDriver(new ApplicationDriver(sharedMemory));
    }

    @Test
    void after_stop_command_the_application_is_no_more_running() {
        applicationRunner.runApplication();

        assertThat(applicationRunner.isApplicationRunning()).isTrue();

        resetDriver.stopGame();

        assertThat(applicationRunner.isApplicationRunning()).isFalse();
    }
}
