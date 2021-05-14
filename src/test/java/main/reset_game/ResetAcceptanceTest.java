package main.reset_game;

import main.ApplicationDriver;
import main.BaseAcceptanceTest;
import main.add_player.AddPlayerDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResetAcceptanceTest extends BaseAcceptanceTest {

    private ApplicationDriver appDriver;
    private AddPlayerDriver driver;
    private ResetDriver resetDriver;

    @BeforeEach
    void setUp() {
        appDriver = super.driver();
        driver = new AddPlayerDriver(appDriver);
        resetDriver = new ResetDriver(appDriver);
    }

    @Test
    void after_reset_an_existing_player_becomes_a_new_player() {
        String output = driver.addPlayer("Pippo");
        driver.verifyPlayersAdded(output, "Pippo");

        output = driver.addPlayer("Pippo");
        driver.verifyAlreadyExistingPlayer(output, "Pippo");

        resetDriver.resetGame();

        output = driver.addPlayer("Pippo");
        driver.verifyPlayersAdded(output, "Pippo");
    }
}
