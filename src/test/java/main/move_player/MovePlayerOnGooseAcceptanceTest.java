package main.move_player;

import main.BaseAcceptanceTest;
import main.add_player.AddPlayerDriver;
import main.test.DiceRollerStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MovePlayerOnGooseAcceptanceTest extends BaseAcceptanceTest {
    private AddPlayerDriver addPlayerDriver;
    private MovePlayerDriver movePlayerDriver;

    @BeforeEach
    void setUp() {
        addPlayerDriver = new AddPlayerDriver(game);
        movePlayerDriver = new MovePlayerDriver(game);
    }

//    If there is one participant "Pippo" on space "3"
//    assuming that the dice get 1 and 1
//    when the user writes: "move Pippo"
//    the system responds: "Pippo rolls 1, 1. Pippo moves from 3 to 5, The Goose. Pippo moves again and goes to 7"

    @Test
    void a_player_repeat_movement_when_lands_on_the_goose() {
        addPlayerDriver.addPlayer("Pippo");
        movePlayerDriver.moveOnPosition3("Pippo");
        DiceRollerStub.onNextRollReturns(1, 1);

        String output = movePlayerDriver.movePlayer("Pippo");
        assertThat(output).isEqualTo(
                "Pippo rolls 1, 1. Pippo moves from 3 to 5, The Goose. Pippo moves again and goes to 7");
    }
}
