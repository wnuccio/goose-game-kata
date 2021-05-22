package _1_actions.player.move.rules.switchrule;

import _1_actions.player.move.rules.first.FirstMovement;
import _1_actions.player.move.rules.first.FirstMovementRuleTest;
import _2_domain.movement.Movement;
import _2_domain.player.Players;
import _2_domain.player.Position;
import _2_domain.presenter.PresentableMovement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

class SwitchPlayersRuleTest {
    Players players = new Players();
    SwitchPlayersRule rule = new SwitchPlayersRule(players);
    LinkedList<PresentableMovement> movements = new LinkedList<>();

    @Test
    void change_position_of_encountered_player() {
        movements.add(new FirstMovement(Position.position(10), Position.position(17)));
        players.setPositionOf("Pippo", Position.position(17));
        players.setPositionOf("Pluto", Position.position(17));

        rule.apply(FirstMovementRuleTest.move("Pippo", 3, 4), movements);

        assertThat(players.positionOf("Pippo")).isEqualTo(Position.position(17));
        assertThat(players.positionOf("Pluto")).isEqualTo(Position.position(10));

        Movement movement = movements.get(1);
        Assertions.assertThat(movement.startPosition()).isEqualTo(Position.position(17));
        Assertions.assertThat(movement.finalPosition()).isEqualTo(Position.position(10));
    }

    @Test
    void do_not_apply_any_switch_if_no_other_player_is_encountered() {
        movements.add(new FirstMovement(Position.position(10), Position.position(17)));
        players.setPositionOf("Pippo", Position.position(17));

        rule.apply(FirstMovementRuleTest.move("Pippo", 3, 4), movements);

        assertThat(players.positionOf("Pippo")).isEqualTo(Position.position(17));

        assertThat(movements.size()).isEqualTo(1);
    }
}