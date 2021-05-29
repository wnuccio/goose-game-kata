package app.domain.movement;

import app.domain.player.*;
import app.domain.presenter.StringBuilderPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static app.domain.player.Dice.dice;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PlayerOnTurnTest {

    StringBuilderPresenter presenter;
    Board board = new Board();
    Player pippo = mock(Player.class);
    Movements movements;

    @BeforeEach
    void setUp() {
        movements = mock(Movements.class);
        presenter = mock(StringBuilderPresenter.class);
    }

    @Test
    void returns_position_of_player() {
        when(pippo.position()).thenReturn(board.position(10));

        PlayerOnTurn playerOnTurn = new PlayerOnTurn(pippo, null, null);

        Position actualPosition = playerOnTurn.position();

        assertThat(actualPosition).isEqualTo(board.position(10));
    }

    @Test
    void add_itself_as_player_observer_on_start() {
        PlayerOnTurn playerOnTurn = new PlayerOnTurn(pippo, null, null);

        playerOnTurn.start();

        verify(pippo).addObserver(playerOnTurn);
    }

    @Test
    void delegates_the_movement_to_the_player() {
        Dice dice = dice(3, 4);

        PlayerOnTurn playerOnTurn = new PlayerOnTurn(pippo, dice, null);

        playerOnTurn.moveByDice();

        verify(pippo).moveByDiceConsideringBouncing(dice);
        verify(pippo).applyRuleOnCurrentPosition(playerOnTurn);
    }

    @Test
    void delegates_apply_movement_to_the_player() {
        Movement movement = mock(Movement.class);
        Player pippo = mock(Player.class);

        PlayerOnTurn playerOnTurn = new PlayerOnTurn(pippo, null, null);
        playerOnTurn.applyMovement(movement);

        verify(pippo).applyMovement(movement);
        verify(pippo).applyRuleOnCurrentPosition(playerOnTurn);
    }

    @Test
    void init_presenter_and_pass_it_to_all_movements() {
        PlayerOnTurn playerOnTurn = new PlayerOnTurn(null, dice(3, 4), movements);

        playerOnTurn.present();

        verify(movements).present(playerOnTurn);
    }

    @Test
    void return_opponents_on_same_position_of_player_on_turn() {
        List<Player> returnedOpponents = new ArrayList<>();

        Players players = mock(Players.class);
        when(players.opponentsOnSamePositionOf(pippo)).thenReturn(returnedOpponents);

        PlayerOnTurn playerOnTurn = new PlayerOnTurn(pippo, null, null);
        List<Player> encounteredOpponents = playerOnTurn.encounteredOpponents(players);

        assertThat(encounteredOpponents).isEqualTo(returnedOpponents);
    }
}