package app.domain.movement;

import app.domain.player.Board;
import app.domain.player.Player;
import app.domain.player.Players;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.LinkedList;
import java.util.List;

import static app.domain.movement.Dice.dice;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PlayerOnTurnTest {

    PlayerOnTurn playerOnTurn;
    StringBuilderPresenter presenter;
    Players players;
    Board board = new Board();
    Player pippo = new Player("Pippo", board.start());
    LinkedList<Movement> movements;

    @BeforeEach
    void setUp() {
        players = mock(Players.class);
        movements = mock(LinkedList.class);
        presenter = mock(StringBuilderPresenter.class);
    }

    @Test
    void return_position_of_player_in_turn() {
        Position position = board.position(10);
        pippo.position(position);
        playerOnTurn = new PlayerOnTurn(pippo, dice(3, 4), movements);
        when(players.find("Pippo")).thenReturn(pippo);

        Position actualPosition = playerOnTurn.position();

        assertThat(actualPosition).isEqualTo(position);
    }

    @Test
    void return_player_is_on_the_goose() {
        Position position = board.position(5);
        pippo.position(position);
        assertThat(position.hasTheGoose()).isTrue();
        when(players.find("Pippo")).thenReturn(pippo);

        playerOnTurn = new PlayerOnTurn(pippo, dice(3, 4), movements);

        assertThat(playerOnTurn.isOnTheGoose()).isTrue();
    }

    @Test
    void apply_a_movement_by_changing_player_position_and_registering_the_movement() {
        Position finalPosition = board.position(10);
        Movement movement = mock(Movement.class);
        when(movement.finalPosition()).thenReturn(finalPosition);
        Player pippo = new Player("Pippo", finalPosition);
        when(players.find("Pippo")).thenReturn(pippo);

        PlayerOnTurn playerOnTurn = new PlayerOnTurn(pippo, dice(3, 4), movements);
        playerOnTurn.applyMovement(movement);

        verify(movements).add(movement);
    }

    @Test
    void init_presenter_and_pass_it_to_all_movements() {
        playerOnTurn = new PlayerOnTurn(null, dice(3, 4), movements);

        playerOnTurn.present(presenter);

        InOrder inOrder = inOrder(presenter, movements);

        inOrder.verify(presenter).init();
        inOrder.verify(movements).forEach(any());
        inOrder.verify(presenter).writeOutput();
    }

    @Test
    void return_oppponents_on_same_position_of_player_on_turn() {
        playerOnTurn = new PlayerOnTurn(pippo, null, null);
        Player aPlayer = mock(Player.class);
        Player anotherPlayer = mock(Player.class);
        List<Player> returnedOpponents = asList(aPlayer, anotherPlayer);
        when(players.opponentsOnSamePositionOf(pippo)).thenReturn(returnedOpponents);

        List<Player> encounteredOpponents = playerOnTurn.encounteredOpponents(players);

        assertThat(encounteredOpponents).isEqualTo(returnedOpponents);
    }
}