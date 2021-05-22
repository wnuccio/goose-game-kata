package _1_actions.player.move.rules.switchrule;

import _1_actions.player.move.rules.first.FirstMovement;
import _2_domain.movement.Movement;
import _2_domain.movement.PlayerTurn;
import _2_domain.player.Players;
import _2_domain.player.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static _1_actions.player.move.rules.first.FirstMovementRuleTest.turn;
import static org.assertj.core.api.Assertions.assertThat;

class SwitchPlayersRuleTest {
    Players players = new Players();
    SwitchPlayersRule rule = new SwitchPlayersRule(players);
    LinkedList<Movement> movements = new LinkedList<>();

    @Test
    void change_position_of_encountered_player() {
        players.setPositionOf("Pippo", Position.position(17));
        players.setPositionOf("Pluto", Position.position(17));
        PlayerTurn turn = turn("Pippo", 3, 4);
        turn.add(new FirstMovement(Position.position(10), Position.position(17)));

        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(Position.position(17));
        assertThat(players.positionOf("Pluto")).isEqualTo(Position.position(10));

        Movement movement = turn.movements().get(1);
        Assertions.assertThat(movement.startPosition()).isEqualTo(Position.position(17));
        Assertions.assertThat(movement.finalPosition()).isEqualTo(Position.position(10));
    }

    @Test
    void do_not_apply_any_switch_if_no_other_player_is_encountered() {
        players.setPositionOf("Pippo", Position.position(17));
        PlayerTurn turn = turn("Pippo", 3, 4);
        turn.add(new FirstMovement(Position.position(10), Position.position(17)));

        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(Position.position(17));

        assertThat(turn.movements().size()).isEqualTo(1);
    }
}