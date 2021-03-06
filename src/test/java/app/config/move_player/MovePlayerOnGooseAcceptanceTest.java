package app.config.move_player;

import app.config.ApplicationDriver;
import app.config.BaseAcceptanceTest;
import app.config.DiceRollerStub;
import app.config.add_player.AddPlayerDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MovePlayerOnGooseAcceptanceTest extends BaseAcceptanceTest {
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
    void a_player_repeat_movement_when_lands_on_the_goose() {
        addPlayerDriver.addPlayer("Pippo");
        movePlayerDriver.moveOnPosition3("Pippo");
        diceRollerStub.onNextRollReturns(1, 1);

        String output = movePlayerDriver.movePlayer("Pippo");
        assertThat(output).isEqualTo(
                "Pippo rolls 1, 1. Pippo moves from 3 to 5, The Goose. Pippo moves again and goes to 7");
    }

    @Test
    void a_player_repeat_movement_more_times_when_lands_on_the_goose() {
        addPlayerDriver.addPlayer("Pippo");
        movePlayerDriver.moveOnPosition10("Pippo");
        diceRollerStub.onNextRollReturns(2, 2);

        String output = movePlayerDriver.movePlayer("Pippo");
        assertThat(output).isEqualTo(
                "Pippo rolls 2, 2. " +
                "Pippo moves from 10 to 14, The Goose. " +
                "Pippo moves again and goes to 18, The Goose. " +
                "Pippo moves again and goes to 22");
    }

}
