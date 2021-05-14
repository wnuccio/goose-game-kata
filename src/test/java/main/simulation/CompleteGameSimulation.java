package main.simulation;

import boundary.random.RandomDiceRoller;
import main.ApplicationDriver;
import main.ApplicationRunner;
import main.add_player.AddPlayerDriver;
import main.move_player.MovePlayerDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompleteGameSimulation {

    private MovePlayerDriver movePlayer;

    @BeforeEach
    void setUp() {
        ApplicationDriver driver = new ApplicationRunner().runApplication(new RandomDiceRoller());
        AddPlayerDriver addPlayer = new AddPlayerDriver(driver);
        addPlayer.addPlayers("Walter", "Andrea");
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
