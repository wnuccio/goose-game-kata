package app.domain.rules.switchrule;

import app.domain.movement.Movements;
import app.domain.player.Board;
import app.domain.player.Player;
import app.domain.player.PlayerOnTurn;
import app.domain.player.Players;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SwitchPlayersRuleTest {
    Board board = new Board();
    Players players = mock(Players.class);
    SwitchPlayersRule rule = new SwitchPlayersRule(players);

    PlayerOnTurn playerOnTurn = mock(PlayerOnTurn.class);

    ArgumentCaptor<SwitchMovement> movement = ArgumentCaptor.forClass(SwitchMovement.class);

    @Test
    void change_position_of_encountered_player() {
        Player pluto = new Player("Pluto", board.position(17));
        Player anotherOpponent = new Player("", null);
        Movements movements = mock(Movements.class);

        when(playerOnTurn.opponentsOnSamePosition(players)).thenReturn(asList(pluto, anotherOpponent));
        when(movements.penultimatePosition()).thenReturn(board.position(10));

        rule.apply(playerOnTurn, movements);

        verify(movements).add(movement.capture());
        assertThat(movement.getValue().switchedPlayer()).isEqualTo("Pluto");
        assertThat(movement.getValue().startPosition()).isEqualTo(board.position(17));
        assertThat(movement.getValue().finalPosition()).isEqualTo(board.position(10));
    }

    @Test
    void do_not_apply_any_switch_if_no_other_player_is_encountered() {
        when(playerOnTurn.opponentsOnSamePosition(players)).thenReturn(emptyList());

        Movements movements = mock(Movements.class);
        rule.apply(playerOnTurn, movements);

        verify(movements, never()).add(any());
    }
}