package app.domain.player;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static org.mockito.Mockito.*;

class AddPlayerTest {
    private final Players players = mock(Players.class);
    private final PlayerPresenter presenter = mock(PlayerPresenter.class);
    private final AddPlayer addPlayer = new AddPlayer(new Board(), players, presenter);

    @Test
    void add_a_new_player_when_an_add_player_command_is_provided() {
        when(players.all()).thenReturn(players("Pippo"));

        addPlayer.doAdd("Pippo");

        verify(presenter).presentPlayers(players("Pippo"));
    }

    @Test
    void add_two_new_players() {
        when(players.all())
                .thenReturn(players("Pippo"))
                .thenReturn(players("Pippo", "Pluto"));

        addPlayer.doAdd("Pippo");
        addPlayer.doAdd("Pluto");

        verify(presenter).presentPlayers(players("Pippo"));
        verify(presenter).presentPlayers(players("Pippo", "Pluto"));
    }

    @Test
    void do_not_add_the_same_player_more_times() {
        when(players.all()).thenReturn(players("Pippo"));
        when(players.contains("Pippo"))
                .thenReturn(false)
                .thenReturn(true);

        addPlayer.doAdd("Pippo");
        addPlayer.doAdd("Pippo");

        InOrder inOrder = Mockito.inOrder(presenter);
        inOrder.verify(presenter).presentPlayers(players("Pippo"));
        inOrder.verify(presenter).presentExistingPlayerError("Pippo");
    }

    private Set<String> players(String...players) {
        return Stream.of(players).collect(toSet());
    }
}