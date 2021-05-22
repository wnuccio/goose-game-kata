package _1_actions.game;

import _2_domain.game.GameSwitch;
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
        GameSwitch gameSwitch = mock(GameSwitch.class);
        StopOrResetGame stopGameService = new StopOrResetGame(gameSwitch, null);

        stopGameService.doStop();

        verify(gameSwitch).turnOff();
    }
}