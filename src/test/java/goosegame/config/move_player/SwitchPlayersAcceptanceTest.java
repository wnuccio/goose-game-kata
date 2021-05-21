package goosegame.config.move_player;

import goosegame.config.ApplicationDriver;
import goosegame.config.BaseAcceptanceTest;
import goosegame.config.DiceRollerStub;
import goosegame.config.add_player.AddPlayerDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SwitchPlayersAcceptanceTest extends BaseAcceptanceTest {
    private AddPlayerDriver addPlayerDriver;
    private MovePlayerDriver movePlayerDriver;
    private DiceRollerStub diceRollerStub;

    @BeforeEach
    void setUp() {
        ApplicationDriver driver = driver();
        addPlayerDriver = new AddPlayerDriver(driver);
        movePlayerDriver = new MovePlayerDriver(driver);
        diceRollerStub = diceRollerStub();
    }

    @Test
    void switch_players_positions_on_encounter() {
        addPlayerDriver.addPlayers("Pippo", "Pluto");
        movePlayerDriver.moveOnPositions15_17("Pippo", "Pluto");
        diceRollerStub.onNextRollReturns(1, 1);

        String output = movePlayerDriver.movePlayer("Pippo");
        assertThat(output).isEqualTo(
                "Pippo rolls 1, 1. Pippo moves from 15 to 17. On 17 there is Pluto, who returns to 15");
    }
}
