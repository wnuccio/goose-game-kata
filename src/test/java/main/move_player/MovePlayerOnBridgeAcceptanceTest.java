package main.move_player;

import main.BaseAcceptanceTest;
import main.add_player.AddPlayerDriver;
import main.test.ApplicationDriver;
import main.test.DiceRollerStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MovePlayerOnBridgeAcceptanceTest extends BaseAcceptanceTest {
    private AddPlayerDriver addPlayerDriver;
    private MovePlayerDriver movePlayerDriver;

    @BeforeEach
    void setUp() {
        ApplicationDriver driver = new ApplicationDriver();
        addPlayerDriver = new AddPlayerDriver(driver);
        movePlayerDriver = new MovePlayerDriver(driver);
    }

//    If there is one participant "Pippo" on space "4"
//    assuming that the dice get 1 and 1
//    when the user writes: "move Pippo"
//    the system responds: "Pippo rolls 1, 1. Pippo moves from 4 to The Bridge. Pippo jumps to 12"

    @Test
    void a_player_moves_and_lands_on_the_bridge() {
        addPlayerDriver.addPlayer("Pippo");
        movePlayerDriver.moveOnPosition4("Pippo");
        DiceRollerStub.onNextRollReturns(1, 1);

        String output = movePlayerDriver.movePlayer("Pippo");
        assertThat(output).isEqualTo("Pippo rolls 1, 1. Pippo moves from 4 to The Bridge. Pippo jumps to 12");
    }
}
