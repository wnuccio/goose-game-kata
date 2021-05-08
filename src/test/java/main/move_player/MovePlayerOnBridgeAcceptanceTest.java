package main.move_player;

import main.BaseAcceptanceTest;
import main.add_player.AddPlayerDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MovePlayerOnBridgeAcceptanceTest extends BaseAcceptanceTest {
    private AddPlayerDriver addPlayerDriver;
    private MovePlayerDriver movePlayerDriver;
    private DiceDriver diceDriver;

    @BeforeEach
    void setUp() {
        addPlayerDriver = new AddPlayerDriver(game);
        movePlayerDriver = new MovePlayerDriver(game);
        diceDriver = new DiceDriver();
    }

//    If there is one participant "Pippo" on space "4"
//    assuming that the dice get 1 and 1
//    when the user writes: "move Pippo"
//    the system responds: "Pippo rolls 1, 1. Pippo moves from 4 to The Bridge. Pippo jumps to 12"

    @Test
    void a_player_moves_and_lands_on_the_bridge() {
        addPlayerDriver.addPlayer("Pippo");
        movePlayerDriver.moveOnPosition4("Pippo");
        diceDriver.onNextRollReturns(1, 1);

        String output = movePlayerDriver.movePlayer("Pippo");
        assertThat(output).isEqualTo("Pippo rolls 1, 1. Pippo moves from 4 to The Bridge. Pippo jumps to 12");
    }
}
