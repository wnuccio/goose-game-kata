package app.domain.rules;

import app.domain.player.PlayerOnTurn;
import app.domain.rules.switchrule.SwitchPlayersRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

class RuleProcessorTest {
    private final SwitchPlayersRule switchPlayersRule = mock(SwitchPlayersRule.class);
    RuleProcessor ruleProcessor = new RuleProcessor(switchPlayersRule);
    private PlayerOnTurn playerOnTurn;

    @BeforeEach
    void setUp() {
        playerOnTurn = mock(PlayerOnTurn.class);
    }

    @Test
    void start_the_received_turn_before_any_operation() {
        ruleProcessor.computeMovementsFor(playerOnTurn);

        InOrder inOrder = inOrder(playerOnTurn, switchPlayersRule);
        inOrder.verify(playerOnTurn).start();
        inOrder.verify(playerOnTurn).moveByDice();
        inOrder.verify(switchPlayersRule).apply(playerOnTurn);
        inOrder.verify(playerOnTurn).end();
    }
}