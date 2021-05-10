package usecase.reset_game;

import domain.Players;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ResetGameServiceTest {
    @Test
    void clears_players_collection() {
        Players players = mock(Players.class);

        new ResetGameService(players).doReset();

        verify(players).clear();
    }
}