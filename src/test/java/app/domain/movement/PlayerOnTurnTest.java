package app.domain.movement;

import app.domain.player.Board;
import app.domain.player.Player;
import app.domain.player.Players;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;
import app.domain.rules.first.FirstMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

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
    Movements movements;

    @BeforeEach
    void setUp() {
        players = mock(Players.class);
        movements = mock(Movements.class);
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
    void move_the_player_on_position_reached_by_dice() {
        pippo.position(board.position(10));
        playerOnTurn = new PlayerOnTurn(pippo, dice(3, 4), movements);
        playerOnTurn.start();

        playerOnTurn.move();

        assertThat(playerOnTurn.position()).isEqualTo(board.position(17));

        ArgumentCaptor<FirstMovement> movement = ArgumentCaptor.forClass(FirstMovement.class);
        verify(movements).add(movement.capture());

        assertThat(movement.getValue().startPosition()).isEqualTo(board.position(10));
        assertThat(movement.getValue().finalPosition()).isEqualTo(board.position(17));
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

        playerOnTurn.present();

        verify(movements).present(playerOnTurn);
    }

    @Test
    void return_opponents_on_same_position_of_player_on_turn() {
        playerOnTurn = new PlayerOnTurn(pippo, null, null);
        Player aPlayer = mock(Player.class);
        Player anotherPlayer = mock(Player.class);
        List<Player> returnedOpponents = asList(aPlayer, anotherPlayer);
        when(players.opponentsOnSamePositionOf(pippo)).thenReturn(returnedOpponents);

        List<Player> encounteredOpponents = playerOnTurn.encounteredOpponents(players);

        assertThat(encounteredOpponents).isEqualTo(returnedOpponents);
    }
}