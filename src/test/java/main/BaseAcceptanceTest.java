package main;

import main.reset_game.ResetGameDriver;
import main.test.ApplicationDriver;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseAcceptanceTest {

    @BeforeEach
    final void init() {
        ApplicationRunner.runApplicationOnFirstDemand();
        new ResetGameDriver(new ApplicationDriver()).resetGame();
    }
}
