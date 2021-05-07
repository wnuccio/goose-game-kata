package usecase.reset_game;

import org.junit.jupiter.api.Test;
import usecase.add_player.Players;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ResetGameUseCaseTest {
    @Test
    void clears_players_collection() {
        Players players = mock(Players.class);

        new ResetGameUseCase(players).acceptCommand("reset game");

        verify(players).clear();
    }
}