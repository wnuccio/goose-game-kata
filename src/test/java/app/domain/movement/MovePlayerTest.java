package app.domain.movement;

import app.domain.player.Board;
import app.domain.player.Player;
import app.domain.player.Players;
import app.domain.presenter.StringBuilderPresenter;
import app.domain.rules.RuleProcessor;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MovePlayerTest {
    Board board = new Board();
    Players players = mock(Players.class);
    RuleProcessor ruleProcessor = mock(RuleProcessor.class);
    ArgumentCaptor<PlayerOnTurn> playerOnTurn = ArgumentCaptor.forClass(PlayerOnTurn.class);
    StringBuilderPresenter presenter = mock(StringBuilderPresenter.class);
    MovePlayer movePlayer = new MovePlayer(players, ruleProcessor, presenter);

    @Test
    void computes_a_movement_list_and_present_them_all() {
        Player pippo = new Player("Pippo", board.start());
        when(players.contains("Pippo")).thenReturn(true);
        when(players.find("Pippo")).thenReturn(pippo);

        movePlayer.acceptCommand(move("Pippo", 4, 3));

        verify(ruleProcessor).computeMovementsFor(playerOnTurn.capture());

        assertThat(playerOnTurn.getValue().playerName()).isEqualTo("Pippo");

        verify(presenter).init();
        verify(presenter).writeOutput();
    }

    private MoveCommand move(String player, int first, int second) {
        return new MoveCommand(player, Dice.dice(first, second));
    }
}