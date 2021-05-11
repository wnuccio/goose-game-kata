package main.move_player;

import main.BaseAcceptanceTest;
import main.add_player.AddPlayerDriver;
import main.test.ApplicationDriver;
import main.test.DiceRollerStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SwitchPlayersAcceptanceTest extends BaseAcceptanceTest {
    private AddPlayerDriver addPlayerDriver;
    private MovePlayerDriver movePlayerDriver;

    @BeforeEach
    void setUp() {
        ApplicationDriver driver = new ApplicationDriver();
        addPlayerDriver = new AddPlayerDriver(driver);
        movePlayerDriver = new MovePlayerDriver(driver);
    }

    @Test
    void switch_players_positions_on_encounter() {
        addPlayerDriver.addPlayers("Pippo", "Pluto");
        movePlayerDriver.moveOnPositions15_17("Pippo", "Pluto");
        DiceRollerStub.onNextRollReturns(1, 1);

        String output = movePlayerDriver.movePlayer("Pippo");
        assertThat(output).isEqualTo(
                "Pippo rolls 1, 1. Pippo moves from 15 to 17. On 17 there is Pluto, who returns to 15");
    }
}
