package app.domain.movement;

import app.domain.player.Player;
import app.domain.player.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static app.domain.movement.MovePlayerTest.move;
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

        findPlayer.acceptCommand(move("Pippo", 3, 4));

        verifyNoInteractions(movePlayer);
    }

    @Test
    void pass_the_player_to_move_player_when_found() {
        when(players.contains("Pippo")).thenReturn(true);
        Player pippo = new Player("Pippo", null);
        when(players.find("Pippo")).thenReturn(pippo);
        Dice dice = new Dice(3, 4);

        findPlayer.acceptCommand(new MoveCommand("Pippo", dice));

        verify(movePlayer).doMove(pippo, dice);
    }

}