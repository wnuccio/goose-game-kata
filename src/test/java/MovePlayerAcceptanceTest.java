import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MovePlayerAcceptanceTest extends BaseAcceptanceTest {

    @Test
    void a_player_moves_from_start_to_a_new_position() {
        addPlayer("Pippo");
        addPlayer("Pluto");

        String output = gooseGame.acceptInput("move Pippo 4, 2");
        assertThat(output).isEqualTo("Pippo rolls 4, 2. Pippo moves from Start to 6");

        String output2 = gooseGame.acceptInput("move Pluto 2, 2");
        assertThat(output2).isEqualTo("Pluto rolls 2, 2. Pluto moves from Start to 4");

        String output3 = gooseGame.acceptInput("move Pippo 2, 3");
        assertThat(output3).isEqualTo("Pippo rolls 2, 3. Pippo moves from 6 to 11");
    }
}
