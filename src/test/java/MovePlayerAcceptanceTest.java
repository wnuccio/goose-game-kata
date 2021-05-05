import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MovePlayerAcceptanceTest extends BaseAcceptanceTest {

    @Test
    void a_player_moves_from_start_to_a_new_position() {
        addPlayer("Pippo");
        addPlayer("Pluto");
        String output = gooseGame.acceptInput("move Pippo 4, 2");

        assertThat(output).isEqualTo("Pippo rolls 4, 2. Pippo moves from Start to 6");
    }
}
