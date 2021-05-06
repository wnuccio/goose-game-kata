import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResetGameAcceptanceTest extends BaseAcceptanceTest {

    @Test
    void after_the_previous_test_a_player_is_newly_on_start() {
        resetGame();

        String output = gooseGame.acceptInput("move Pippo 2, 2");

        assertThat(output).isEqualTo("Pippo: no such player");
    }
}
