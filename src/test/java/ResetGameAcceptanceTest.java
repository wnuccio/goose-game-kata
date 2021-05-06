import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResetGameAcceptanceTest extends BaseAcceptanceTest {
    @Test
    void move_a_player_on_an_advanced_position() {
        addPlayer("Pippo");

        String output;

        output = gooseGame.acceptInput("move Pippo 2, 2");
        assertThat(output).isEqualTo("Pippo rolls 2, 2. Pippo moves from Start to 4");
    }

    @Test
    void after_the_previous_test_a_player_is_newly_on_start() {
        super.resetGame();

        String output = gooseGame.acceptInput("move Pippo 2, 2");
        assertThat(output).isEqualTo("Pippo rolls 2, 2. Pippo moves from Start to 4");
    }
}
