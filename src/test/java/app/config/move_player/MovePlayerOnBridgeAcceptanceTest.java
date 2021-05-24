package app.config.move_player;

import app.config.ApplicationDriver;
import app.config.BaseAcceptanceTest;
import app.config.DiceRollerStub;
import app.config.add_player.AddPlayerDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MovePlayerOnBridgeAcceptanceTest extends BaseAcceptanceTest {
    private AddPlayerDriver addPlayerDriver;
    private MovePlayerDriver movePlayerDriver;
    DiceRollerStub diceRollerStub;

    @BeforeEach
    void setUp() {
        ApplicationDriver driver = driver();
        addPlayerDriver = new AddPlayerDriver(driver);
        movePlayerDriver = new MovePlayerDriver(driver);
        diceRollerStub = diceRollerStub();
    }

//    If there is one participant "Pippo" on space "4"
//    assuming that the dice get 1 and 1
//    when the user writes: "move Pippo"
//    the system responds: "Pippo rolls 1, 1. Pippo moves from 4 to The Bridge. Pippo jumps to 12"

    @Test
    void a_player_moves_and_lands_on_the_bridge() {
        addPlayerDriver.addPlayer("Pippo");
        movePlayerDriver.moveOnPosition4("Pippo");
        diceRollerStub.onNextRollReturns(1, 1);

        String output = movePlayerDriver.movePlayer("Pippo");
        assertThat(output).isEqualTo("Pippo rolls 1, 1. Pippo moves from 4 to The Bridge. Pippo jumps to 12");
    }
}
