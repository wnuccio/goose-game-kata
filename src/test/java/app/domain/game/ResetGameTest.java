package app.domain.game;

import app.domain.player.Players;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ResetGameTest {
    @Test
    void clears_players_collection() {
        Players players = mock(Players.class);

        new ResetGame(null, players).doReset();

        verify(players).clear();
    }

    @Test
    void stops_application() {
        GameSwitch gameSwitch = mock(GameSwitch.class);
        ResetGame stopGameService = new ResetGame(gameSwitch, null);

        stopGameService.doStop();

        verify(gameSwitch).turnOff();
    }
}