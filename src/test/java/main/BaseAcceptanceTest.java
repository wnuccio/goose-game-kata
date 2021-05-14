package main;

import main.reset_game.ResetDriver;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseAcceptanceTest {

    private ApplicationDriver driver;

    @BeforeEach
    final void init() {
        driver = ApplicationRunner.runApplication();
        new ResetDriver(driver).resetGame();
    }

    protected ApplicationDriver driver() { return driver; }
}
