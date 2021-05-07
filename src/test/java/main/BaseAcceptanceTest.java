package main;

import main.reset_game.ResetGameDriver;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseAcceptanceTest {
    protected ApplicationDriver game;

    @BeforeEach
    final void init() {
        ApplicationRunner.runApplicationOnFirstDemand();
        game = new ApplicationDriver();
        new ResetGameDriver(game).resetGame();
    }
}
