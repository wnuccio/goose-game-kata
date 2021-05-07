package main;

import main.helpers.AddPlayerDriver;
import main.helpers.ApplicationDriver;
import main.helpers.BaseAcceptanceTest;
import org.junit.jupiter.api.Test;

public class ResetGameAcceptanceTest extends BaseAcceptanceTest {

    private ApplicationDriver appDriver = new ApplicationDriver();
    private AddPlayerDriver driver = new AddPlayerDriver(appDriver);

    @Test
    void after_reset_an_existing_player_becomes_a_new_player() {
        String output = driver.addPlayer("Pippo");
        driver.verifyPlayersAdded(output, "Pippo");

        output = driver.addPlayer("Pippo");
        driver.verifyAlreadyExistingPlayer(output, "Pippo");

        appDriver.resetGame();

        output = driver.addPlayer("Pippo");
        driver.verifyPlayersAdded(output, "Pippo");
    }
}
