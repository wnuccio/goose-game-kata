package app.domain.movement;

import app.domain.player.Board;
import app.domain.player.Players;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static app.domain.rules.first.FirstMovementRuleTest.move;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PlayerTurnTest {

    private PlayerTurn turn;
    private Movement movement1;
    private Movement movement2;
    private StringBuilderPresenter presenter;
    private Players players;
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        players = mock(Players.class);

        movement1 = mock(Movement.class);
        movement2 = mock(Movement.class);

        presenter = mock(StringBuilderPresenter.class);
    }

    @Test
    void return_position_of_player_in_turn() {
        Position position = board.position(10);
        turn = new PlayerTurn(players, move("Pippo", 3, 4));
        when(players.positionOf("Pippo")).thenReturn(position);

        Position actualPosition = turn.positionOfPlayer();

        assertThat(actualPosition).isEqualTo(position);
    }

    @Test
    void change_position_of_player_in_turn() {
        Position position = board.position(10);
        turn = new PlayerTurn(players, move("Pippo", 3, 4));

        turn.setPositionOfPlayer(position);

        verify(players).setPositionOf("Pippo", position);
    }

    @Test
    void init_presenter_and_pass_it_to_all_movements() {
        turn = new PlayerTurn(null, move("Pippo", 3, 4));
        turn.add(movement1);
        turn.add(movement2);

        turn.present(presenter);

        InOrder inOrder = inOrder(presenter);

        inOrder.verify(presenter).init();
        verify(movement1).present(presenter, turn);
        verify(movement2).present(presenter, turn);
        inOrder.verify(presenter).writeOutput();
    }

    @Test
    void find_any_other_player_on_given_position() {
        Players players = new Players();
        players.setPositionOf("Pippo", position(15));
        players.setPositionOf("Pluto", position(15));
        players.setPositionOf("Topolino", position(15));
        players.setPositionOf("Paperino", position(10));

        turn = new PlayerTurn(players, move("Pippo", 3, 4));

        assertThat(turn.encounteredOpponents().size()).isEqualTo(2);
        assertThat(turn.encounteredOpponents().containsAll(asList("Pluto", "Topolino"))).isTrue();
    }

    private Position position(int i) {
        return board.position(i);
    }

}