package app.domain.rules.switchrule;

import app.domain.movement.Movement;
import app.domain.movement.PlayerTurn;
import app.domain.player.Board;
import app.domain.player.Players;
import app.domain.rules.first.FirstMovement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static app.domain.rules.first.FirstMovementRuleTest.turn;
import static org.assertj.core.api.Assertions.assertThat;

class SwitchPlayersRuleTest {
    Board board = new Board();
    Players players = new Players();
    SwitchPlayersRule rule = new SwitchPlayersRule(players);
    LinkedList<Movement> movements = new LinkedList<>();

    @Test
    void change_position_of_encountered_player() {
        players.setPositionOf("Pippo", board.position(17));
        players.setPositionOf("Pluto", board.position(17));
        PlayerTurn turn = turn("Pippo", 3, 4);
        turn.add(new FirstMovement(board.position(10), board.position(17)));

        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(board.position(17));
        assertThat(players.positionOf("Pluto")).isEqualTo(board.position(10));

        Movement movement = turn.movements().get(1);
        Assertions.assertThat(movement.startPosition()).isEqualTo(board.position(17));
        Assertions.assertThat(movement.finalPosition()).isEqualTo(board.position(10));
    }

    @Test
    void do_not_apply_any_switch_if_no_other_player_is_encountered() {
        players.setPositionOf("Pippo", board.position(17));
        PlayerTurn turn = turn("Pippo", 3, 4);
        turn.add(new FirstMovement(board.position(10), board.position(17)));

        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(board.position(17));

        assertThat(turn.movements().size()).isEqualTo(1);
    }
}