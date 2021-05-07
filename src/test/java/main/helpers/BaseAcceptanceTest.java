package main.helpers;

import org.junit.jupiter.api.BeforeEach;

public abstract class BaseAcceptanceTest {
    protected static ApplicationDriver game;

    @BeforeEach
    void setUp() {
        driver().resetGame();
    }

    final protected ApplicationDriver driver() {
        if (game == null) {
            ApplicationRunner applicationRunner = new ApplicationRunner();
            applicationRunner.runApplication();
            game = new ApplicationDriver();
        }
        return game;
    }
}
