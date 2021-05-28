package app.domain.movement;

import app.domain.player.Player;
import app.domain.player.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class FindPlayerTest {

    private Players players;
    private MovePlayer movePlayer;
    private FindPlayer findPlayer;

    @BeforeEach
    void setUp() {
        players = mock(Players.class);
        movePlayer = mock(MovePlayer.class);
        findPlayer = new FindPlayer(players, movePlayer);
    }

    @Test
    void do_nothing_if_no_player_with_given_name_is_found() {
        when(players.contains("Pippo")).thenReturn(false);

        findPlayer.acceptCommand(new MoveCommand("Pippo", new Dice(3, 4)));

        verifyNoInteractions(movePlayer);
    }

    @Test
    void pass_the_player_to_move_player_when_found() {
        Player pippo = new Player("Pippo", null);
        Dice dice = new Dice(3, 4);

        when(players.contains("Pippo")).thenReturn(true);
        when(players.find("Pippo")).thenReturn(pippo);

        findPlayer.acceptCommand(new MoveCommand("Pippo", dice));

        verify(movePlayer).doMove(pippo, dice);
    }
}