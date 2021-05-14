package main;

import main.reset_game.ResetDriver;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseAcceptanceTest {

    @BeforeEach
    final void init() {
        ApplicationRunner.runApplication();
        new ResetDriver(new ApplicationDriver()).resetGame();
    }
}
