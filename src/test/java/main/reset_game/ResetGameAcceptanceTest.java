package main.reset_game;

import main.BaseAcceptanceTest;
import main.add_player.AddPlayerDriver;
import main.test.ApplicationDriver;
import org.junit.jupiter.api.Test;

public class ResetGameAcceptanceTest extends BaseAcceptanceTest {

    private ApplicationDriver appDriver = new ApplicationDriver();
    private AddPlayerDriver driver = new AddPlayerDriver(appDriver);
    private ResetGameDriver resetGameDriver = new ResetGameDriver(appDriver);

    @Test
    void after_reset_an_existing_player_becomes_a_new_player() {
        String output = driver.addPlayer("Pippo");
        driver.verifyPlayersAdded(output, "Pippo");

        output = driver.addPlayer("Pippo");
        driver.verifyAlreadyExistingPlayer(output, "Pippo");

        resetGameDriver.resetGame();

        output = driver.addPlayer("Pippo");
        driver.verifyPlayersAdded(output, "Pippo");
    }
}
