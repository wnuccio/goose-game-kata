package goosegame.config.move_player;

import goosegame.config.ApplicationDriver;
import goosegame.config.BaseAcceptanceTest;
import goosegame.config.add_player.AddPlayerDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MovePlayerAcceptanceTest extends BaseAcceptanceTest {
    private AddPlayerDriver addPlayerDriver;
    private MovePlayerDriver movePlayerDriver;

    @BeforeEach
    void setUp() {
        ApplicationDriver driver = driver();
        addPlayerDriver = new AddPlayerDriver(driver);
        movePlayerDriver = new MovePlayerDriver(driver);
    }

    @Test
    void a_player_moves_from_start_to_a_new_position() {
        addPlayerDriver.addPlayers("Pippo", "Pluto");

        String output = movePlayerDriver.movePlayer("Pippo", 4, 4);
        assertThat(output).isEqualTo("Pippo rolls 4, 4. Pippo moves from Start to 8");

        String output2 = movePlayerDriver.movePlayer("Pluto", 2, 2);
        assertThat(output2).isEqualTo("Pluto rolls 2, 2. Pluto moves from Start to 4");

        String output3 = movePlayerDriver.movePlayer("Pippo", 2, 3);
        assertThat(output3).isEqualTo("Pippo rolls 2, 3. Pippo moves from 8 to 13");
    }

    @Test
    void a_player_wins_when_lands_on_position_63() {
        addPlayerDriver.addPlayer("Pippo");
        movePlayerDriver.moveOnPosition60("Pippo");

        String output = movePlayerDriver.movePlayer("Pippo", 1, 2);
        assertThat(output).isEqualTo("Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!!");
    }

    @Test
    void a_player_bounces_when_does_not_lands_exactly_on_the_last_position() {
        addPlayerDriver.addPlayer("Pippo");
        movePlayerDriver.moveOnPosition60("Pippo");

        String output = movePlayerDriver.movePlayer("Pippo", 3, 2);
        assertThat(output).isEqualTo("Pippo rolls 3, 2. Pippo moves from 60 to 63. Pippo bounces! Pippo returns to 61");
    }
}
