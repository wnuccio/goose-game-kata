package usecase.reset_game;

import domain.Players;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ResetGameServiceTest {
    @Test
    void clears_players_collection() {
        Players players = mock(Players.class);

        new ResetGameService(null, players).doReset();

        verify(players).clear();
    }

    @Test
    void stops_application() {
        ApplicationSwitch applicationSwitch = mock(ApplicationSwitch.class);
        ResetGameService stopGameService = new ResetGameService(applicationSwitch, null);

        stopGameService.doStop();

        verify(applicationSwitch).turnOff();
    }
}