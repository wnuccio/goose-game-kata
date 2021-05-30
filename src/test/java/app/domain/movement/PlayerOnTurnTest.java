package app.domain.movement;

import app.domain.player.*;
import app.domain.presenter.StringBuilderPresenter;
import app.domain.rules.switchrule.SwitchPlayersRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        PlayerOnTurn playerOnTurn = new PlayerOnTurn(pippo, null);

        Position actualPosition = playerOnTurn.position();

        assertThat(actualPosition).isEqualTo(board.position(10));
    }

    @Test
    void execute_whole_turn_by_registering_provided_movements() {
        Dice dice = new Dice(2, 3);
        PlayerOnTurn playerOnTurn = new PlayerOnTurn(pippo, dice);

        Movements movements = mock(Movements.class);
        SwitchPlayersRule switchPlayersRule = mock(SwitchPlayersRule.class);
        playerOnTurn.performTurn(movements, switchPlayersRule);

        verify(pippo).addObserver(movements);
        verify(pippo).moveByDiceConsideringBouncing(dice);
        verify(pippo).applyRuleOnCurrentPosition(playerOnTurn);
        verify(switchPlayersRule).apply(pippo, movements);
        verify(movements).present(playerOnTurn);
    }

    @Test
    void delegates_apply_movement_to_the_player() {
        Movement movement = mock(Movement.class);
        Player pippo = mock(Player.class);

        PlayerOnTurn playerOnTurn = new PlayerOnTurn(pippo, null);
        playerOnTurn.applyMovement(movement);

        verify(pippo).applyMovement(movement);
        verify(pippo).applyRuleOnCurrentPosition(playerOnTurn);
    }
}