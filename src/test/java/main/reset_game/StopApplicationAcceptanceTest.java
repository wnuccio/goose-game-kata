package main.reset_game;

import main.ApplicationDriver;
import main.ApplicationRunner;
import org.junit.jupiter.api.Test;

public class StopApplicationAcceptanceTest {
    private ResetDriver resetDriver = new ResetDriver(new ApplicationDriver());

    @Test
    void after_stop_command_the_application_is_no_more_running() {
        ApplicationRunner.runApplication();

        resetDriver.verifyGameRunning();

        resetDriver.stopGame();

        resetDriver.verifyGameStopped();
    }
}
