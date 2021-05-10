package main.reset_game;

import main.ApplicationRunner;
import main.test.ApplicationDriver;
import org.junit.jupiter.api.Test;

public class StopGameAcceptanceTest {
    private ResetGameDriver resetGameDriver = new ResetGameDriver(new ApplicationDriver());

    @Test
    void after_stop_command_the_application_is_no_more_running() {
        ApplicationRunner.runApplicationOnFirstDemand();

        resetGameDriver.verifyGameRunning();

        resetGameDriver.stopGame();

        resetGameDriver.verifyGameStopped();
    }
}
