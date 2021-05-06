package main.helpers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseAcceptanceTest {
    protected static ApplicationDriver gooseGame;

    @BeforeAll
    static void runApplication() {
        gooseGame = new ApplicationDriver(new ApplicationRunner());
        gooseGame.runApplication();
    }

    @AfterAll
    static void stopApplication() {
        gooseGame.stopApplication();
    }

    @BeforeEach
    void setUp() {
        gooseGame.resetGame();
    }

    protected String addPlayer(String playerName) {
        return gooseGame.addPlayer(playerName);
    }

}
