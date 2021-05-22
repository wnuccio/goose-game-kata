package _1_actions.game;

import _2_domain.game.Game;
import _2_domain.player.Players;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class StopOrResetGameTest {
    @Test
    void clears_players_collection() {
        Players players = mock(Players.class);

        new StopOrResetGame(null, players).doReset();

        verify(players).clear();
    }

    @Test
    void stops_application() {
        Game game = mock(Game.class);
        StopOrResetGame stopGameService = new StopOrResetGame(game, null);

        stopGameService.doStop();

        verify(game).turnOff();
    }
}