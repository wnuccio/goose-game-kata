package goosegame.usecase.move_player;

import goosegame.domain.Players;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static goosegame.domain.Position.WIN;
import static goosegame.domain.Position.position;
import static goosegame.usecase.move_player.FirstMovementRuleTest.move;
import static org.assertj.core.api.Assertions.assertThat;

class BouncingRuleTest {
    Players players = new Players();
    BouncingRule rule = new BouncingRule(players);
    LinkedList<Movement> movements = new LinkedList<>();

    @Test
    void correct_position_over_63_with_bouncing() {
        players.setPositionOf("Pippo", position(69));

        rule.apply(move("Pippo", 3, 4), movements);

        assertThat(players.positionOf("Pippo")).isEqualTo(position(57));

        Movement movement = movements.get(0);
        assertThat(movement.startPosition()).isEqualTo(WIN);
        assertThat(movement.finalPosition()).isEqualTo(position(57));
    }
}