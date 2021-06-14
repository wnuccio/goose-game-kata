package app.domain.rules.switchrule;

import app.domain.movement.MovementsRecorder;
import app.domain.player.Board;
import app.domain.player.Player;
import app.domain.player.PlayerOnTurn;
import app.domain.player.Players;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static app.domain.player.BoardForTests.standardBoard;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SwitchPlayersRuleTest {
    Board board = standardBoard();
    Players players = mock(Players.class);
    SwitchPlayersRule rule = new SwitchPlayersRule(players);

    PlayerOnTurn playerOnTurn = mock(PlayerOnTurn.class);

    ArgumentCaptor<SwitchMovement> movement = ArgumentCaptor.forClass(SwitchMovement.class);

    @Test
    void change_position_of_encountered_player() {
        Player pluto = mock(Player.class);
        Player anotherOpponent = new Player("", null);
        when(players.opponentsOnSamePositionOf(any())).thenReturn(asList(pluto, anotherOpponent));
        when(pluto.name()).thenReturn("Pluto");
        when(pluto.position()).thenReturn(board.position(17));

        Player pippo = mock(Player.class);
        MovementsRecorder movementsRecorder = mock(MovementsRecorder.class);
        when(movementsRecorder.penultimatePosition()).thenReturn(board.position(10));

        rule.apply(pippo, movementsRecorder);

        verify(pluto).addObserver(movementsRecorder);
        verify(pluto).applyMovement(movement.capture());
        assertThat(movement.getValue().switchedPlayer()).isEqualTo("Pluto");
        assertThat(movement.getValue().startPosition()).isEqualTo(board.position(17));
        assertThat(movement.getValue().finalPosition()).isEqualTo(board.position(10));
    }

    @Test
    void do_not_apply_any_switch_if_no_other_player_is_encountered() {
        when(players.opponentsOnSamePositionOf(any())).thenReturn(emptyList());

        MovementsRecorder movementsRecorder = mock(MovementsRecorder.class);
        rule.apply(null, movementsRecorder);

        verify(movementsRecorder, never()).playerPositionChanged(any());
    }
}