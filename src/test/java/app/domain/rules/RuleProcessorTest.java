package app.domain.rules;

import app.domain.movement.PlayerOnTurn;
import app.domain.rules.bridge.JumpOnBridgeRule;
import app.domain.rules.goose.GooseRule;
import app.domain.rules.switchrule.SwitchPlayersRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RuleProcessorTest {
    private final JumpOnBridgeRule jumpOnBridgeRule = mock(JumpOnBridgeRule.class);
    private final GooseRule gooseRule = mock(GooseRule.class);
    private final SwitchPlayersRule switchPlayersRule = mock(SwitchPlayersRule.class);
    RuleProcessor ruleProcessor = new RuleProcessor(jumpOnBridgeRule, gooseRule, switchPlayersRule);
    private PlayerOnTurn playerOnTurn;

    @BeforeEach
    void setUp() {
        playerOnTurn = mock(PlayerOnTurn.class);
    }

    @Test
    void start_the_received_turn_before_any_operation() {
        ruleProcessor.computeMovementsFor(playerOnTurn);

        verify(playerOnTurn).start();
    }

//    @Test
//    void repeat_movement_on_the_bridge() {
//        Player pippo = new Player("Pippo", board.position(4));
//        players.add(pippo);
//        PlayerOnTurn turn = new PlayerOnTurn(pippo, dice(1, 1), new LinkedList<>());
//
//        ruleProcessor.computeMovementsFor(turn);
//
//        assertThat(turn.movements().size()).isEqualTo(2);
//        assertThat(turn.movements().get(0) instanceof FirstMovement).isTrue();
//        assertThat(turn.movements().get(1) instanceof JumpOnBridgeMovement).isTrue();
//    }
//
//    @Test
//    void repeat_movement_on_the_goose() {
//        Player pippo = new Player("Pippo", board.position(3));
//        players.add(pippo);
//
//        PlayerOnTurn turn = new PlayerOnTurn(pippo, dice(1, 1), new LinkedList<>());
//        ruleProcessor.computeMovementsFor(turn);
//
//        assertThat(turn.movements().size()).isEqualTo(2);
//        assertThat(turn.movements().get(0) instanceof FirstMovement).isTrue();
//        assertThat(turn.movements().get(1) instanceof GooseMovement).isTrue();
//    }
//
//    @Test
//    void switch_players_on_encounter() {
//        Player pippo = new Player("Pippo", board.position(15));
//        players.add(pippo);
//        players.add(new Player("Pluto", board.position(17)));
//
//        PlayerOnTurn turn = new PlayerOnTurn(pippo, dice(1, 1), new LinkedList<>());
//        ruleProcessor.computeMovementsFor(turn);
//
//        assertThat(turn.movements().size()).isEqualTo(2);
//        assertThat(turn.movements().get(0) instanceof FirstMovement).isTrue();
//        assertThat(turn.movements().get(1) instanceof SwitchMovement).isTrue();
//    }
}