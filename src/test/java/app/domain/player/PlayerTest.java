package app.domain.player;

import app.domain.rules.bouncing.BouncingMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PlayerTest {

    private Board board;
    private Player pippo;
    private PlayerObserver pippoObserver;
    private ArgumentCaptor<Movement> movement;

    @BeforeEach
    void setUp() {
        board = new Board();
        pippoObserver = mock(PlayerObserver.class);

        movement = ArgumentCaptor.forClass(Movement.class);
    }

    @Test
    void on_apply_movement_move_to_the_final_position_and_notify_movement() {
        pippo = new Player("Pippo", board.position(3));
        pippo.addObserver(pippoObserver);

        Movement movement = mock(Movement.class);
        when(movement.finalPosition()).thenReturn(board.position(7));
        pippo.applyMovement(movement);

        assertThat(pippo.position()).isEqualTo(board.position(7));
        verify(pippoObserver).playerPositionChanged(movement);
    }

    @Test
    void move_to_given_position_and_notify_movement() {
        pippo = new Player("Pippo", board.position(3));
        pippo.addObserver(pippoObserver);

        pippo.moveByDiceConsideringBouncing(new Dice(2, 3));

        assertThat(pippo.position()).isEqualTo(board.position(8));

        verify(pippoObserver).playerPositionChanged(movement.capture());

        assertThat(movement.getValue().startPosition()).isEqualTo(board.position(3));
        assertThat(movement.getValue().finalPosition()).isEqualTo(board.position(8));
    }

    @Test
    void bounce_to_a_previous_position_when_over_win_and_notify_bouncing() {
        pippo = new Player("Pippo", board.position(61)); // max 63
        pippo.addObserver(pippoObserver);

        pippo.moveByDiceConsideringBouncing(new Dice(2, 3)); // 66

        assertThat(pippo.position()).isEqualTo(board.position(60));

        verify(pippoObserver, times(2)).playerPositionChanged(movement.capture());

        assertThat(movement.getAllValues().get(0).startPosition()).isEqualTo(board.position(61));
        assertThat(movement.getAllValues().get(0).finalPosition()).isEqualTo(board.position(66));
        assertThat(movement.getAllValues().get(1) instanceof BouncingMovement).isTrue();
        assertThat(movement.getAllValues().get(1).startPosition()).isEqualTo(board.position(63));
        assertThat(movement.getAllValues().get(1).finalPosition()).isEqualTo(board.position(60));
    }

    @Test
    void apply_rule_on_current_position() {
        Position position = mock(Position.class);
        pippo = new Player("Pippo", position); // max 63

        PlayerOnTurn playerOnTurn = mock(PlayerOnTurn.class);
        pippo.applyRuleOnCurrentPosition(playerOnTurn);

        verify(position).applyAttachedRule(playerOnTurn);
    }
}
