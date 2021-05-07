package main.helpers;

import org.junit.jupiter.api.BeforeEach;

public abstract class BaseAcceptanceTest {
    protected static ApplicationDriver game;

    @BeforeEach
    void setUp() {
        if (game == null) {
            ApplicationRunner applicationRunner = new ApplicationRunner();
            applicationRunner.runApplication();
            game = new ApplicationDriver();
        } else

        game.resetGame();
    }
}
