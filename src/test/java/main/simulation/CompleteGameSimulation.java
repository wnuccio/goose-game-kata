package main.simulation;

import main.ApplicationDriver;
import main.TestConfiguration;
import main.add_player.AddPlayerDriver;
import main.move_player.MovePlayerDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompleteGameSimulation {

    private MovePlayerDriver movePlayer;

    @BeforeEach
    void setUp() {
        TestConfiguration configuration = new TestConfiguration();
        configuration.applicationRunner().runApplication();

        ApplicationDriver driver = configuration.applicationDriver();
        new AddPlayerDriver(driver).addPlayers("Walter", "Andrea");

        movePlayer = new MovePlayerDriver(driver);
    }

    @Test
    void complete_game() {

        while(true) {
            String outputWalter = movePlayer.movePlayer("Walter");
            if (outputWalter.contains("Wins")) break;

            String outputAndrea = movePlayer.movePlayer("Andrea");
            if (outputAndrea.contains("Wins")) break;
        }
    }
}
