package app.domain.rules;

import app.domain.movement.Movements;
import app.domain.movement.PlayerOnTurnFactory;
import app.domain.player.PlayerOnTurn;
import app.domain.rules.switchrule.SwitchPlayersRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class RuleProcessorTest {
    private final SwitchPlayersRule switchPlayersRule = mock(SwitchPlayersRule.class);
    private PlayerOnTurnFactory playerOnTurnFactory = mock(PlayerOnTurnFactory.class);
    RuleProcessor ruleProcessor = new RuleProcessor(switchPlayersRule, playerOnTurnFactory);
    private PlayerOnTurn playerOnTurn;

    @BeforeEach
    void setUp() {
        playerOnTurn = mock(PlayerOnTurn.class);
    }

    @Test
    void start_the_received_turn_before_any_operation() {
        Movements movements = new Movements(null);
        when(playerOnTurnFactory.createMovements()).thenReturn(movements);

        ruleProcessor.computeMovementsFor(playerOnTurn);

        verify(playerOnTurn).doTurn(movements, switchPlayersRule);
    }
}