package app.domain.movement;

import app.domain.player.Board;
import app.domain.player.Dice;
import app.domain.player.Player;
import app.domain.player.PlayerOnTurn;
import app.domain.rules.RuleProcessor;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class MovePlayerTest {
    Board board = new Board();
    RuleProcessor ruleProcessor = mock(RuleProcessor.class);
    PlayerOnTurn playerOnTurn = mock(PlayerOnTurn.class);
    PlayerOnTurnFactory playerOnTurnFactory = mock(PlayerOnTurnFactory.class);
    MovePlayer movePlayer = new MovePlayer(ruleProcessor);

    @Test
    void create_a_new_player_on_turn_and_computes_movement_for_him() {
        Player pippo = new Player("Pippo", board.start());
        Dice dice = new Dice(3, 4);

        when(playerOnTurnFactory.createPlayerOnTurn(pippo, dice)).thenReturn(playerOnTurn);

        movePlayer.doMove(playerOnTurn);

        verify(ruleProcessor).computeMovementsFor(playerOnTurn);
        verify(playerOnTurn).present();
    }

}