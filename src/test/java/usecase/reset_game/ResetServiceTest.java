package usecase.reset_game;

import domain.Players;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ResetServiceTest {
    @Test
    void clears_players_collection() {
        Players players = mock(Players.class);

        new ResetService(null, players).doReset();

        verify(players).clear();
    }

    @Test
    void stops_application() {
        ApplicationSwitch applicationSwitch = mock(ApplicationSwitch.class);
        ResetService stopGameService = new ResetService(applicationSwitch, null);

        stopGameService.doStop();

        verify(applicationSwitch).turnOff();
    }
}