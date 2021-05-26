package app.domain.rules;

import app.domain.movement.PlayerOnTurn;
import app.domain.player.Board;
import app.domain.player.Players;
import app.domain.rules.bouncing.BouncingMovement;
import app.domain.rules.bridge.JumpOnBridgeMovement;
import app.domain.rules.first.FirstMovement;
import app.domain.rules.goose.GooseMovement;
import app.domain.rules.switchrule.SwitchMovement;
import org.junit.jupiter.api.Test;

import static app.domain.rules.first.FirstMovementRuleTest.playerOnTurn;
import static org.assertj.core.api.Assertions.assertThat;

class RuleProcessorTest {
    Board board = new Board();
    Players players = new Players();
    RuleProcessor ruleProcessor = new RuleProcessor(board, players);

    @Test
    void repeat_movement_on_bouncing() {
        players.addNewPlayerOnPosition("Pippo", board.position(62));

        PlayerOnTurn player = playerOnTurn(players, "Pippo", 3, 4);
        ruleProcessor.computeMovementsFor(player);

        assertThat(player.movements().size()).isEqualTo(2);
        assertThat(player.movements().get(0) instanceof FirstMovement).isTrue();
        assertThat(player.movements().get(1) instanceof BouncingMovement).isTrue();
    }

    @Test
    void repeat_movement_on_the_bridge() {
        players.addNewPlayerOnPosition("Pippo", board.position(4));
        PlayerOnTurn turn = playerOnTurn(players, "Pippo", 1, 1);

        ruleProcessor.computeMovementsFor(turn);

        assertThat(turn.movements().size()).isEqualTo(2);
        assertThat(turn.movements().get(0) instanceof FirstMovement).isTrue();
        assertThat(turn.movements().get(1) instanceof JumpOnBridgeMovement).isTrue();
    }

    @Test
    void repeat_movement_on_the_goose() {
        players.addNewPlayerOnPosition("Pippo", board.position(3));

        PlayerOnTurn turn = playerOnTurn(players, "Pippo", 1, 1);
        ruleProcessor.computeMovementsFor(turn);

        assertThat(turn.movements().size()).isEqualTo(2);
        assertThat(turn.movements().get(0) instanceof FirstMovement).isTrue();
        assertThat(turn.movements().get(1) instanceof GooseMovement).isTrue();
    }

    @Test
    void switch_players_on_encounter() {
        players.addNewPlayerOnPosition("Pippo", board.position(15));
        players.addNewPlayerOnPosition("Pluto", board.position(17));

        PlayerOnTurn turn = playerOnTurn(players, "Pippo", 1, 1);
        ruleProcessor.computeMovementsFor(turn);

        assertThat(turn.movements().size()).isEqualTo(2);
        assertThat(turn.movements().get(0) instanceof FirstMovement).isTrue();
        assertThat(turn.movements().get(1) instanceof SwitchMovement).isTrue();
    }
}