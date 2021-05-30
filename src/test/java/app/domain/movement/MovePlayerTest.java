package app.domain.movement;

import app.domain.player.PlayerOnTurn;
import app.domain.rules.switchrule.SwitchPlayersRule;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MovePlayerTest {
    PlayerOnTurn playerOnTurn = mock(PlayerOnTurn.class);
    private MovementsFactory movementsFactory = mock(MovementsFactory.class);
    private SwitchPlayersRule switchPlayersRule = mock(SwitchPlayersRule.class);
    MovePlayer movePlayer = new MovePlayer(movementsFactory, switchPlayersRule);

    @Test
    void create_a_new_player_on_turn_and_computes_movement_for_him() {
        movePlayer.doMove(playerOnTurn);

        verify(playerOnTurn).performTurn(any(), eq(switchPlayersRule));
    }

}