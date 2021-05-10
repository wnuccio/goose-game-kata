package main.reset_game;

import main.ApplicationRunner;
import main.test.ApplicationDriver;
import org.junit.jupiter.api.Test;

public class ExitGameAcceptanceTest {
    private ResetGameDriver resetGameDriver = new ResetGameDriver(new ApplicationDriver());

    @Test
    void after_reset_an_existing_player_becomes_a_new_player() {
        ApplicationRunner.runApplicationOnFirstDemand();

        resetGameDriver.verifyGameRunning();

        resetGameDriver.stopGame();

        resetGameDriver.verifyGameStopped();
    }
}
