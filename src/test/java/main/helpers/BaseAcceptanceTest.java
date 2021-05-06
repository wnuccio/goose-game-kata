package main.helpers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseAcceptanceTest {
    protected static ApplicationDriver game;

    @BeforeAll
    static void runApplication() {
        game = new ApplicationDriver(new ApplicationRunner());
        game.runApplication();
    }

    @AfterAll
    static void stopApplication() {
        game.stopApplication();
    }

    @BeforeEach
    void setUp() {
        game.resetGame();
    }

    protected String addPlayer(String playerName) {
        return game.addPlayer(playerName);
    }

}
