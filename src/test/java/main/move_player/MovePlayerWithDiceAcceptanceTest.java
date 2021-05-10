package main.move_player;

import main.BaseAcceptanceTest;
import main.add_player.AddPlayerDriver;
import main.test.DiceRollerStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MovePlayerWithDiceAcceptanceTest extends BaseAcceptanceTest {
    private AddPlayerDriver addPlayerDriver;
    private MovePlayerDriver movePlayerDriver;

    @BeforeEach
    void setUp() {
        addPlayerDriver = new AddPlayerDriver(game);
        movePlayerDriver = new MovePlayerDriver(game);
    }

//    If there is one participant "Pippo" on space "4"
//    assuming that the dice get 1 and 2
//    when the user writes: "move Pippo"
//    the system responds: "Pippo rolls 1, 2. Pippo moves from 4 to 7"

    @Test
    void a_player_moves_by_throwing_dice() {
        addPlayerDriver.addPlayer("Pippo");
        movePlayerDriver.moveOnPosition4("Pippo");
        DiceRollerStub.onNextRollReturns(1, 2);

        String output = movePlayerDriver.movePlayer("Pippo");
        assertThat(output).isEqualTo("Pippo rolls 1, 2. Pippo moves from 4 to 7");
    }
}
