package main;

import main.helpers.BaseAcceptanceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResetGameAcceptanceTest extends BaseAcceptanceTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void after_the_previous_test_a_player_is_newly_on_start() {
        game.resetGame();

        String output = game.movePlayer("Pippo", 2, 1);

        assertThat(output).isEqualTo("Pippo: no such player");
    }
}
