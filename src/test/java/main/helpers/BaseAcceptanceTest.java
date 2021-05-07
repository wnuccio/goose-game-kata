package main.helpers;

import org.junit.jupiter.api.BeforeEach;

public abstract class BaseAcceptanceTest {
    protected ApplicationDriver game;

    @BeforeEach
    final void init() {
        ApplicationRunner.runApplication();
        game = new ApplicationDriver();
        game.resetGame();
    }
}
