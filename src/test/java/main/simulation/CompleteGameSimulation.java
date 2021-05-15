package main.simulation;

import boundary.random.RandomDiceRoller;
import main.ApplicationDriver;
import main.ApplicationRunner;
import main.GooseGameAppBuilderForTest;
import main.SharedMemory;
import main.add_player.AddPlayerDriver;
import main.move_player.MovePlayerDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompleteGameSimulation {

    private MovePlayerDriver movePlayer;

    @BeforeEach
    void setUp() {
        SharedMemory sharedMemory = new SharedMemory();
        GooseGameAppBuilderForTest appBuilder = new GooseGameAppBuilderForTest(sharedMemory, new RandomDiceRoller());
        new ApplicationRunner(appBuilder).runApplication();

        ApplicationDriver driver = new ApplicationDriver(sharedMemory);
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
