package main;

import main.reset_game.ResetDriver;
import main.test.ApplicationDriver;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseAcceptanceTest {

    @BeforeEach
    final void init() {
        ApplicationRunner.runApplicationOnFirstDemand();
        new ResetDriver(new ApplicationDriver()).resetGame();
    }
}
