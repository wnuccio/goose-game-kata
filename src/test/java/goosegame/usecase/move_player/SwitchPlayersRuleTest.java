package goosegame.usecase.move_player;

import goosegame.domain.Players;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static goosegame.domain.Position.position;
import static goosegame.usecase.move_player.FirstMovementRuleTest.move;
import static org.assertj.core.api.Assertions.assertThat;

class SwitchPlayersRuleTest {
    Players players = new Players();
    SwitchPlayersRule rule = new SwitchPlayersRule(players);
    LinkedList<Movement> movements = new LinkedList<>();

    @Test
    void change_position_of_encountered_player() {
        movements.add(new FirstMovement(position(10), position(17)));
        players.setPositionOf("Pippo", position(17));
        players.setPositionOf("Pluto", position(17));

        rule.apply(move("Pippo", 3, 4), movements);

        assertThat(players.positionOf("Pippo")).isEqualTo(position(17));
        assertThat(players.positionOf("Pluto")).isEqualTo(position(10));

        Movement movement = movements.get(1);
        assertThat(movement.startPosition()).isEqualTo(position(17));
        assertThat(movement.finalPosition()).isEqualTo(position(10));
    }

    @Test
    void do_not_apply_any_switch_if_no_other_player_is_encountered() {
        movements.add(new FirstMovement(position(10), position(17)));
        players.setPositionOf("Pippo", position(17));

        rule.apply(move("Pippo", 3, 4), movements);

        assertThat(players.positionOf("Pippo")).isEqualTo(position(17));

        assertThat(movements.size()).isEqualTo(1);
    }
}