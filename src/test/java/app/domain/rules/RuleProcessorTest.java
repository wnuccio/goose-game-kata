package app.domain.rules;

import app.domain.movement.PlayerOnTurn;
import app.domain.player.Board;
import app.domain.player.Player;
import app.domain.player.Players;
import app.domain.rules.bouncing.BouncingMovement;
import app.domain.rules.bridge.JumpOnBridgeMovement;
import app.domain.rules.first.FirstMovement;
import app.domain.rules.goose.GooseMovement;
import app.domain.rules.switchrule.SwitchMovement;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static app.domain.movement.Dice.dice;
import static org.assertj.core.api.Assertions.assertThat;

class RuleProcessorTest {
    Board board = new Board();
    Players players = new Players();
    RuleProcessor ruleProcessor = new RuleProcessor(board, players);

    @Test
    void repeat_movement_on_bouncing() {
        Player pippo = new Player("Pippo", board.position(62));
        players.add(pippo);

        PlayerOnTurn player = new PlayerOnTurn(pippo, dice(3, 4), new LinkedList<>());
        ruleProcessor.computeMovementsFor(player);

        assertThat(player.movements().size()).isEqualTo(2);
        assertThat(player.movements().get(0) instanceof FirstMovement).isTrue();
        assertThat(player.movements().get(1) instanceof BouncingMovement).isTrue();
    }

    @Test
    void repeat_movement_on_the_bridge() {
        Player pippo = new Player("Pippo", board.position(4));
        players.add(pippo);
        PlayerOnTurn turn = new PlayerOnTurn(pippo, dice(1, 1), new LinkedList<>());

        ruleProcessor.computeMovementsFor(turn);

        assertThat(turn.movements().size()).isEqualTo(2);
        assertThat(turn.movements().get(0) instanceof FirstMovement).isTrue();
        assertThat(turn.movements().get(1) instanceof JumpOnBridgeMovement).isTrue();
    }

    @Test
    void repeat_movement_on_the_goose() {
        Player pippo = new Player("Pippo", board.position(3));
        players.add(pippo);

        PlayerOnTurn turn = new PlayerOnTurn(pippo, dice(1, 1), new LinkedList<>());
        ruleProcessor.computeMovementsFor(turn);

        assertThat(turn.movements().size()).isEqualTo(2);
        assertThat(turn.movements().get(0) instanceof FirstMovement).isTrue();
        assertThat(turn.movements().get(1) instanceof GooseMovement).isTrue();
    }

    @Test
    void switch_players_on_encounter() {
        Player pippo = new Player("Pippo", board.position(15));
        players.add(pippo);
        players.add(new Player("Pluto", board.position(17)));

        PlayerOnTurn turn = new PlayerOnTurn(pippo, dice(1, 1), new LinkedList<>());
        ruleProcessor.computeMovementsFor(turn);

        assertThat(turn.movements().size()).isEqualTo(2);
        assertThat(turn.movements().get(0) instanceof FirstMovement).isTrue();
        assertThat(turn.movements().get(1) instanceof SwitchMovement).isTrue();
    }
}