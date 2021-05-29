package app.domain.movement;

import app.domain.player.PlayerOnTurn;
import app.domain.rules.RuleProcessor;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MovePlayerTest {
    RuleProcessor ruleProcessor = mock(RuleProcessor.class);
    PlayerOnTurn playerOnTurn = mock(PlayerOnTurn.class);
    MovePlayer movePlayer = new MovePlayer(ruleProcessor);

    @Test
    void create_a_new_player_on_turn_and_computes_movement_for_him() {
        movePlayer.doMove(playerOnTurn);

        verify(ruleProcessor).computeMovementsFor(playerOnTurn);
    }

}