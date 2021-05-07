package main;

import main.helpers.AddPlayerDriver;
import main.helpers.BaseAcceptanceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MovePlayerAcceptanceTest extends BaseAcceptanceTest {
    private AddPlayerDriver addPlayerDriver;

    @BeforeEach
    void setUp() {
        driver().resetGame();
        addPlayerDriver = new AddPlayerDriver(driver());
    }

    @Test
    void a_player_moves_from_start_to_a_new_position() {
        addPlayerDriver.addPlayer("Pippo");
        addPlayerDriver.addPlayer("Pluto");

        String output = game.movePlayer("Pippo", 4, 2);
        assertThat(output).isEqualTo("Pippo rolls 4, 2. Pippo moves from Start to 6");

        String output2 = game.movePlayer("Pluto", 2, 2);
        assertThat(output2).isEqualTo("Pluto rolls 2, 2. Pluto moves from Start to 4");

        String output3 = game.movePlayer("Pippo", 2, 3);
        assertThat(output3).isEqualTo("Pippo rolls 2, 3. Pippo moves from 6 to 11");
    }

    @Test
    void a_player_wins_when_lands_on_position_63() {
        addPlayerDriver.addPlayer("Pippo");
        moveOnPosition60("Pippo");

        String output = game.movePlayer("Pippo", 1, 2);
        assertThat(output).isEqualTo("Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!!");
    }

    @Test
    void a_player_bounces_when_does_not_lands_exactly_on_the_last_position() {
        addPlayerDriver.addPlayer("Pippo");
        moveOnPosition60("Pippo");

        String output = game.movePlayer("Pippo", 3, 2);
        assertThat(output).isEqualTo("Pippo rolls 3, 2. Pippo moves from 60 to 63. Pippo bounces! Pippo returns to 61");
    }

    private void moveOnPosition60(String pippo) {
        for (int i = 0; i < 6; i++) game.movePlayer(pippo, 5, 5);
    }
}
