package main.reset_game;

import main.BaseAcceptanceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StopApplicationAcceptanceTest extends BaseAcceptanceTest {
    private ResetDriver resetDriver;

    @BeforeEach
    void setUp() {
        resetDriver = new ResetDriver(driver());
    }

    @Test
    void after_stop_command_the_application_is_no_more_running() {
//        ApplicationRunner.runApplication();

        assertThat(super.applicationRunner.isApplicationRunning()).isTrue();
//        resetDriver.verifyGameRunning();

        resetDriver.stopGame();

//        resetDriver.verifyGameStopped();
        assertThat(super.applicationRunner.isApplicationRunning()).isFalse();
    }
}
